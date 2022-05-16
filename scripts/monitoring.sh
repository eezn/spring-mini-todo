#!/bin/bash

# CPU physical
printf "#CPU physical: "
grep ^processor /proc/cpuinfo | wc -l

# vCPU
printf "#vCPU: "
grep -c processor /proc/cpuinfo

# Memory Usage
printf "#Memory Usage: "
free | grep Mem | awk '{total = $2/1024} END {used = $3/1024} END {rate = ($3/$2)*100} END {printf "%d/%dMB (%.2f%)", used, total, rate}'
printf "\n"

# Disk Usage
printf "#Disk Usage: "
df -m | grep -v ^tmpfs | grep -v ^devtmpfs | grep -v ^Filesystem | awk '{used += $3} END {printf "%d/", used}'
df -m | grep -v ^tmpfs | grep -v ^devtmpfs | grep -v ^Filesystem | awk '{total += $2} END {used += $3} END {rate = used/total} END {printf "%dGb (%.2f%)", total/1024, rate*100}'
printf "\n"

# CPU load
CPU_LOAD=`top -b -n1 | grep -Po '[0-9.]+ id' | awk '{print 100-$1}'`
printf "#CPU load: ${CPU_LOAD}%%\n"

# Last boot
LAST_BOOT=`uptime -s | rev | cut -c 4- | rev`
printf "#Last boot: ${LAST_BOOT}\n"

# LVM use
LVM_COUNT=`/usr/sbin/lvs | sed -n '2,$p' | wc -l`
if [ ${LVM_COUNT} -gt 0 ]; then
printf "#LVM use: yes\n"
else
printf "#LVM use: no\n"
fi

# Connections TCP
TCP_CNCT=`/usr/sbin/ss -t | grep -v ^State | wc -l`
printf "#Connections TCP: ${TCP_CNCT} ESTABLISED\n"

# User log
USERS=`who | wc -l`
printf "#User log: ${USERS}\n"

# Network
IP=`hostname -I | awk '{print $1}'`
MAC=`/usr/sbin/ip addr | sed -n '/ether/p' | cut -c 16- | rev | cut -c 23- | rev | sed -n '1p'`
printf "#Network: IP ${IP} (${MAC})\n"
