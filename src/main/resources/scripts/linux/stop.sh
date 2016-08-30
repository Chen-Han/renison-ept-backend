#! /bin/bash
sudo ifconfig wlan0 down && sudo ifconfig wlan0 up # restarting wifi
outputDir=${HOME}/ept-dist
studentPid=`cat ${outputDir}/student.pid`
adminPid=`cat ${outputDir}/admin.pid`
serverPid=`cat ${outputDir}/server.pid`
kill ${studentPid}
kill ${adminPid}
kill ${serverPid}
