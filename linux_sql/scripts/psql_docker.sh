#! /bin/bash

#function to check if docker container exists
check_if_exist () {
	if [ $2 == "create" ]; then
		if [ $1 -eq 2 ]; then
			echo "Error: Docker container already exists"
			exit 1
		fi
	else 
		if [ $1 -ne 2 ]; then
			echo "Error: Docker container doesn't exist"
			exit 1         
		fi
	fi
}

# Line arguments: command, username, password
cmd=$1
db_username=$2
db_password=$3

#Check if docker is running. If not - start docker
sudo systemctl status docker || systemctl start docker

#variable to check if docker container exists
docker_container=$(docker container ls -a -f name=jrvs-psql | wc -l)

#create docker container if the command is 'create'
case $cmd in 
"create")
	
	#check if container doesn't exist, if does - exit
	check_if_exist $docker_container $cmd

	#check if username and password were passed through CLI arguments
	if [ $# -ne 3 ]; then
		echo "Error: Please provide username and password" 
		exit 1
	fi

	#create docker `pgdate` volume
	docker volume create pgdata
	
	#create a psql container `jrvs-psql`
	docker run --name jrvs-psql -e POSTGRES_PASSWORD=${db_password} -e POSTGRES_USER=${db_username} -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres
	
	exit $?
	;;

#start the container
"start")	

	#check if the container was created. If not - exit
	check_if_exist $docker_container $cmd 	
	
	#start the container
	docker container start jrvs-psql
	echo "container started"
	exit $?
	;;

#stop the container
"stop")

	#check if the container was created. If not - exit                         
        check_if_exist $docker_container $cmd 

	#stop the container
	docker container stop jrvs-psql
	echo "container stopped"
	exit $?
	;;
	
#check if the command is invalid
*)
	echo "Invalid command"
	exit 1
	;;
esac
