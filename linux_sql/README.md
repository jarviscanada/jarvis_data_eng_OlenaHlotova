#Linux Cluster Monitoring Agent
- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Implementation](#implementation)
    - [Architecture](#architecture)
    - [Database Modeling](#database-modeling)
- [Test](#test)
- [Deployment](#deployment)
- [Improvements](#improvements)

# Introduction

---
The Linux Cluster Monitoring Agent (LCMA) was created as a tool for Linux Cluster Administrator(LCA) team to record information about servers(computers) in the network.
These servers are connected in a Linux cluster through internal IPv4 addresses. The cluster is running on Centos7. The LCMA will collect the host's hardware specifications as well as resource usage statically and dynamically.
The collected data will be used to generate useful reports for future purposes (add/remove servers, check for hardware failures, data analysis).
The script will record the host's number of CPUs, its architecture and model, clock speed, the amount of total and free memory number of disk I/O and more.
The data will be stored in a Postgresql Docker database (RDBMS) every minute using crontab. This script is using PSQL, Docker, Bash and other supporting technologies (Git, Google Cloud Platform, Linux CentOS 7).
# Quick Start

---
Currently, LCMA quick start was tested on Linux CentOS 7 (running on GCP's virtual machine) with pre-installed Docker Postgres image.
[Install Docker on CentOS 7](https://docs.docker.com/engine/install/centos/). [Download Postgres image on Docker](https://hub.docker.com/_/postgres).
1. Start a psql instance using `psql_docker.sh` script.
```bash
# You have to make sure that the PSQL Docker container was created. If not:
./scripts/psql_docker.sh create psql_user psql_password

# Start the container:
./scripts/psql_docker.sh start
  
# In case you will want to stop the container:
./scripts/psql_docker.sh stop
```
2. Create host_info and host_usage tables that will store the data with `ddl.sql` script.
```bash
# Run the script specifying -h hostname, -U username and -d name of the database. -f for the filename.
psql -h psql_host -U psql_user -d db_name -f sql/ddl.sql
```
3. Insert host's hardware specs into the database, created in the previous step with `host_info.sh`. 
   It will collect the hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, l2_cache, total_memory, and timestamp.
```bash
# Make sure to provide proper psql hostname, psql port (5432 by default), name of the database, psql username and psql password
./scripts/host_info.sh psql_host psql_port db_name psql_user psql_password
```
4. Insert host's usage data into the database, created in step 2 with `host_usage.sh`.
   It will collect the timestamp, hostname (is a foreign key to host_info), memory_free, cpu_idle, cpu_kernel, disk_io and disk_available.
```bash
# Make sure to provide proper input variables (the same as for host_info in step 4)
./scripts/host_usage.sh psql_host psql_port db_name psql_user psql_password
```
5. Set up the automation of `host_usage.sh` with crontab. The script will collect the data and add it to the database every minute.
```bash
# Open crontab editor from the command line
crontab -e
  
# Add the crontab instructions to the editor and save it to the crontab logs
* * * * * ./<PATH>/host_usage.sh psql_host psql_port db_name psql_user psql_password > /tmp/host_usage.log
```
6. You are set and can check the collected specifications. 
   You can run `queries.sql` to start your data analysis (group hosts by hardware info, find out average memory usage for each host or detect some host failures) 
```bash
psql -h psql_host -U psql_user -d db_name -f sql/queries.sql
```
# Implementation

---
## Architecture
## Scripts
## Database Modeling
# Test
# Deployment
# Improvements