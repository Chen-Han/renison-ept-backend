sudo ipconfig set en1 DHCP #en0 is ethernet, en0 is airport
sudo ifconfig en1 down ; sudo ifconfig en1 up
outputDir=${HOME}/ept-dist
studentPid=`cat ${outputDir}/student.pid`
adminPid=`cat ${outputDir}/admin.pid`
serverPid=`cat ${outputDir}/server.pid`
kill ${studentPid}
kill ${adminPid}
kill ${serverPid}



