#!/bin/bash

#The script collects hardware specification data and then insert the data to the psql instance and executes once.

# Line arguments: hostname, db port, db name, db user name, db password
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
export PGPASSWORD=$5

#check if all agrs were passed through CLI
if [ $# -ne 5 ]; then
    echo Error: Please provide all arguments
    exit 1
fi

#reusable variable
lscpu_out=$(lscpu)

#save hostname to a variable
hostname=$(hostname -f)

#hardware variables
cpu_number=$(echo "$lscpu_out" | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out" | grep "Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out" | grep "Model name:" | awk -F ':[[:space:]]*' '{print $2}')
cpu_mhz=$(echo "$lscpu_out" | grep "CPU MHz:" | awk -F ':[[:space:]]*' '{print $2}')
l2_cache=$(echo "$lscpu_out" | grep 'L2 cache:' | awk '{print $3}' | xargs | sed 's/.$//') #kB
total_mem=$(free -t | grep -oP '\d+' | sed '10!d') #kB
timestamp=$(date +'%Y-%m-%d %H:%M:%S') #UTC time zone

#insert all hardware variables into the database
insert_stmt="INSERT INTO host_info (hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, l2_cache, total_mem, timestamp) 
	VALUES ('$hostname', '$cpu_number', '$cpu_architecture', '$cpu_model', '$cpu_mhz', '$l2_cache', '$total_mem', '$timestamp')"

#connect to db with provided args and insert all host hardware info into the db
psql -h "$psql_host" -p "$psql_port" -d "$db_name" -U "$psql_user" -c "$insert_stmt"
exit $?

