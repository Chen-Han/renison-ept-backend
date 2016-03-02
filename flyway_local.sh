for arg in $@ ; do
  mvn -Dflyway.configFile=src/main/resources/flyway_local.properties flyway:$arg
done
