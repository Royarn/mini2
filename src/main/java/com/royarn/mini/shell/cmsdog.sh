#!/bin/bash
basepath=$(cd `dirname $0`; pwd)
ulimit -n 40960

log_dir="/home/netposa/pvg/cms/cmslog"
backup_dir="/home/netposa/pvg/ds/repo/service/cms"

function get_fd_count()
{
    local pid=$1
	if [ ! -d /proc/$pid/fd ]; then
		echo "0"
	else
		echo `ls /proc/$pid/fd/ | wc -l`
	fi
    
}

function killtree_pid()
{
    local father=$1
    childs=`ps -ef | awk -v father=$father 'BEGIN{ ORS=" "; } $3==father{ print $2; }'`

    if [ ${#childs[@]} -ne 0 ]; then
        for child in ${childs[*]}; do
            killtree_pid $child
        done
    fi

    kill -9 $father 2> /dev/null
}

function release_all_pid()
{
	local pid_array
	pid_array=$@
	echo "release pid_array is $pid_array"
	if [ ${#pid_array[@]} -ne 0 ]; then
		for child in ${pid_array[*]}; do
			killtree_pid $child
		done
	fi
}

function cms_process_clean()
{
	local port=$1
	rm -rf /var/cms/$port
}

function run_process()
{
    local fd_count
    local pid
	local port
	local pvm_ip
	local pvm_port
	local multiip
	local current_time
	local sub_pids
	
	port=$1
	pvm_ip=$2
	pvm_port=$3
	multiip=$4
	
	mkdir -p /var/cms/$port
	touch /var/cms/$port/cms_run
	
    while true; do 
		if [ ! -f /var/cms/$port/cms_run ]; then
			break
		fi
		
		if [ ! -f /home/netposa/pvg/cms/cms ]; then
			echo "cms is not installed ,give up start!" >> "/usr/local/openresty/nginx/logs/daemon.log"
			sleep 5
			continue
		fi
		
		local occupy_pid=`netstat -nlp | grep $port | grep cms | grep -v grep | wc -l`
		if [ $occupy_pid -gt 0 ];then
			echo "the port $port is binded,cms may be started ,give up start!" >> "/usr/local/openresty/nginx/logs/daemon.log"
			sleep 5
			continue
		fi
		
		cd /home/netposa/pvg/cms 
		if [ x"$multiip" != x ]; then
			./cms -p $port --dc $pvm_ip --dcport $pvm_port --externdc $multiip  &
		else
			./cms -p $port --dc $pvm_ip --dcport $pvm_port  &
		fi

        pid=$!local= -dsfppppp
		#backup and log in file
		mkdir -p $backup_dir
		local log_file=`cd $log_dir && ls log*.log | tail -1`
		cd $log_dir && cp $log_file $backup_dir
		local param="-p $port --dc $pvm_ip --dcport $pvm_port "
		
		current_time=`date`
		echo "[$current_time][start] cms $param $log_file by cmsdog"  >> "/usr/local/openresty/nginx/logs/daemon.log"
  
		sub_pids=`ps -ef | awk -v father=$pid 'BEGIN{ ORS=" "; } $3==father{ print $2; }'`
		wait $pid
        release_all_pid $sub_pids $pid 
		current_time=`date`
		echo "[$current_time][stop] cms $param by cmsdog while break the circle"  >> "/usr/local/openresty/nginx/logs/daemon.log"
        wait $pid 2> /dev/null
		sleep 1-------------------------
    done
	cms_process_clean $port
}

run_process $1 $2 $3 $4
