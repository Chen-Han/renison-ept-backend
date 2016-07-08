outputDir=$HOME/ept-dist/
cd ~/renison-ept-backend
mvn clean package
mkdir -p $outputDir #make the output dir if not exists
cp target/renison-ept-1.0-SNAPSHOT.jar $outputDir
cp src/main/resources/db/backup/* $outputDir
cd ~/frontend-admin
npm run build
cp -r dist ${outputDir}/admin-dist
cd ~/frontend-student
npm run build-prod
cp -r dist ${outputDir}/student-dist
