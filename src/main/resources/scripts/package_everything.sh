outputDir=$HOME/ept-dist
rm -rf $outputDir
mkdir -p $outputDir #make the output dir if not exists
mkdir -p $outputDir/public #the folder for video files, or other static content
cd ~/renison-ept-backend
mvn clean package
cp target/renison-ept-1.0-SNAPSHOT.jar $outputDir
cp -r src/main/resources/scripts/windows ${outputDir}/windows
cp -r src/main/resources/scripts/mac ${outputDir}/mac
cp "src/main/resources/application.local-mysql.properties" ${outputDir}
cp src/main/resources/db/backup/* $outputDir #sql files

cd ~/frontend-admin
rm -rf dist/*
npm run build
cp -r dist ${outputDir}/admin-dist
cd ~/frontend-student
rm -rf dist/*
npm run build-prod
cp -r dist ${outputDir}/student-dist
