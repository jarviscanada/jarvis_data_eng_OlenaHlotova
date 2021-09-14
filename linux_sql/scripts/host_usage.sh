#! /bin/bash 

# CLI arguments to variables 
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

#usage variables
hostname=$(hostname -f)
timestamp=$(date +'%Y-%m-%d %H:%M:%S') #UTC time zone
memory_free=$(vmstat -s | grep -oP '\d+' | sed '5!d') #in MB
cpu_idle=$(mpstat | awk '{print $NF}' | tail -1) #in percentage
cpu_kernel=$(mpstat | tail -1 | awk '{print $6}') #in percentage
disk_io=$(vmstat -d | tail -1 | awk '{print $10}') #number of disk I/O
disk_available=$(df -BM / | tail -1 | awk '{print $4}' | grep -oP '\d+') #in MB

#receive host_id from host_info
host_id="SELECT id FROM host_info WHERE hostname='$hostname'"

#insert all usage variables and host_id into the database 
insert_stmt="INSERT INTO host_usage (timestamp, host_id, memory_free, cpu_idle, cpu_kernel, disk_io, disk_available)
	VALUES ('$timestamp', ($host_id),'$memory_free','$cpu_idle','$cpu_kernel','$disk_io','$disk_available')"

#connect to db with provided args and insert all host usage info into the db
psql -h "$psql_host" -p "$psql_port" -d "$db_name" -U "$psql_user" -c "$insert_stmt"
exit $?
