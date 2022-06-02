# Linux Cluster Monitoring Agent

## Introduction
Linux cluster monitoring agent is an implementation of this project. It allows users monitor clusters connected internally through network switches.  It collects hardware information from host and tracks it's memory usage. The information collected is stored in database so that system admins can adjust computer specifications according to CPU usage and utilization over time. **Bash, Git, Docker and PostgreSQL** are main technologies used for this project. 

## Quick Start
A. Provision and start a PostgreSQL database
``` 
./scripts/psql_docker.sh start | stop | create [ db_username ] [ db_password ]
```

B. Initialize  tables in host_agent database

```
psql -h localhost -U postgres -d host_agent -f sql/ddl.sql
```
C. Inserting hardware specification into host_agent

```
 ./scripts/host_info.sh localhost localhost 5432 host_agent db_username db_password
```

D. Inserting memory usage into host_agent 

```
 ./scripts/host_usage.sh localhost 5432 host_agent db_username db_password
```

E.  Setting up Crontab to run host_usage.sh automatically after every minute. 

```
  ***** bash ~/dev/jarvis_data_eng_SukhreetRai/linux_sql/scripts/host_usage.sh localhost 5432 host_agent postgres password > /tmp/host_usage.log
```

## Implementation

PostgreSQL image is used to establish an instance of database with help of Docker container. The bash file psql_docker.sh is used to create | start | stop the docker container. Now the turn is to create database within docker container. The Bash file ddl.sql comes handy after creation of database, to create tables properly. The bash file host_info.sh collects hardware information of host on which it is operating. The bash file host_usage.sh collects the CPU/memory usage information. The crontab helps to run host_usage.sh script automatically after every minute. 

## Architecture
![architecture.drawio](/assets/architecture.drawio.png)


 ## Scripts
 Scripts in this project are written in bash language.
1. Psql_docker.sh: 
The purpose of this script is to create, start or stop PostgreSQL docker container which will be operating on port 5432 on the database named host_agent. 

```
/linux_sql/scripts/psql_docker.sh start|stop|create [db_username] [db_password]
```
2. ddl.sql 
This script will create host_info and host_usage table within host_agent database which is created in PostgreSQL docker container. 
```
psql -h localhost -U postgres -d host_agent -f sql/ddl.sql
```
3. host_info.sh
runs to collect host specifications mainly hostname, CPU number, CPU architecture, CPU mhz, l2 cache, total memory, and timestamp when data is being collected. 
```
./scripts/host_info.sh psql_host psql_port db_name psql_user psql_password
```
4. host_usage.sh
used to collect CPU/memory usage dynamically such as free memory, idle CPU percentage, kernel CPU usage, disk space used, and disk IO. 

```
./scripts/host_usage.sh psql_host psql_port db_name psql_user psql_password
``` 
5. crontab
used to execute memory usage data which will be collected by host_usage.sh repeatedly after every minute of time interval and store values in psql host_agent database. 
```
***** bash ~/dev/jarvis_data_eng_SukhreetRai/linux_sql/scripts/host_usage.sh localhost 5432 host_agent postgres password > /tmp/host_usage.log
```
6. queries.sql
This script is used to group hosts by CPU number and sort them by total memory size at first. Secondly, it will display average CPU usage percentage over 5-minute intervals of every host. Lastly, it also has queries to detect any data entry failures which might happen when crontab is operating due to server issues. 
```
psql -h localhost -U postgres -d host_agent -f sql/queries.sql
```

## Database Modeling

A. Host_info

| Variable         |   Data Type         |  
|------------------|:-------------------:|
| id               | INT                 | 
| hostname         | VAR(255)            |  
| cpu_number       | INT                 |
| cpu_architecture | VAR(255)            |
| cpu_model        | DOUBLE PRECISION    |
| cpu_mhz          | INT                 |
| l2_cache         | INT                 |
| total_memory     | INT                 |
| timestamp        | TIMESTAMP           |



B. host_usage
| Variable      |   Data Type |  
|---------------|:-----------:|
| timestamp     | TIMESTAMP   | 
| hostid        | INT         |  
| memory_free   | INT         |
| cpu_idle      | VAR(255)    |
| cpu_kernel    | VAR(255)    |
| disk_io       | INT         |
| disk_available| INT         |

## Test

This project has been tested over bash terminal with numerous inputs. To test scripts properly, arguments were changed many times to see if scripts are throwing exceptions for invalid inputs. SQL queries needed little bit data into the tables, so demo data was inserted manually to test SQL queries for desired output. Bash -x was used to debug any errors occurred while executing. 

## Deployment

This project has been deployed with help of technologies like GitHub, Docker, and crontab. PostgreSQL image was installed with docker container to manage database. GitHub has provided a platform to store our code. 

## Improvements

Few improvements can be made to improve functionality of this project:

- This project could take few more parameters for hardware information to get holistic idea of overall performance of system.
- The password requirement to implement PostgreSQL instance can be managed automatically without entering it every time connecting to PostgreSQL database. 
- Script can be created to send any host failures of crontab function, which will send output to certain file. It can be useful for admin to look at downsides of servers. 
