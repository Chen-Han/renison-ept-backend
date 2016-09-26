cd ~/renison-ept-backend;
nohup java -jar target/renison-ept-1.0-SNAPSHOT.jar --spring.application.json='{"dispatchOptionsRequest":true}' --spring.config.location=src/main/resources/application.local-mysql.properties &
cd ~/frontend-admin;
nohup npm run dev &
subl .
cd ~/frontend-student;
nohup npm run dev &
subl .
