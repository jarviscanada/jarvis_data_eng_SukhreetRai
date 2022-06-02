#!/bin/bash

# Declaring variables

psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

# Validating number of arguments
	if [ $# -ne 5 ]
	then 
		echo "Inncorrect number of arguments"
	exit 1
	fi

# Collecting host information and storing it in Variables
hostname=$(hostname -f)
lscpu_out=`lscpu`
cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out" | egrep "Architecture" | awk '{print $2}'| xargs)
cpu_model=$(echo "$lscpu_out" | egrep "Model name:" | awk '{print $3,$4,$5,$6,$7}'| xargs)
cpu_mhz=$(echo "$lscpu_out" | egrep "CPU MHz:" | awk '{print $3}'| xargs)
l2_cache=$(echo "$lscpu_out" | egrep "L2 cache:" | awk '{print $3}'| xargs | sed 's/K//g')
mem=$(cat /proc/meminfo)
total_mem=$(echo "$mem" | egrep "MemTotal:" | awk '{print $2}'| xargs)
timestamp=$(vmstat -t | awk '{print $18,$19}' | tail -n1)

#Inserting the collected info into table
insert_stmt="INSERT INTO 
			host_info
			(hostname,cpu_number,cpu_architecture,cpu_model,cpu_mhz,L2_cache,total_mem,timestamp) 
			VALUES('$hostname',$cpu_number,'$cpu_architecture','$cpu_model',$cpu_mhz,$l2_cache,$total_mem,'$timestamp');"

#connecting to psql instance
export PGPASSWORD=$psql_password
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?


