#! /bin/bash
# setting up ip
sudo ipconfig set en1 INFORM 10.10.10.50
# starting server
outputDir=$HOME/ept-dist
cd ${outputDir}/student-dist && nohup python -m SimpleHTTPServer 8888 & echo $! > ${outputDir}/admin.pid
cd ${outputDir}/admin-dist && nohup python -m SimpleHTTPServer 8081 & echo $! > ${outputDir}/student.pid
cd ${outputDir}; nohup java -jar renison-ept-1.0-SNAPSHOT.jar --spring.config.location=application.local-mysql.properties --spring.application.json='{"dispatchOptionsRequest":true}' & echo $! > ${outputDir}/server.pid
