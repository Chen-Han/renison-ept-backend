package hibernate.util;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static Logger logger = Logger.getLogger(HibernateUtil.class);
    // Path to source code root
    private static final String SRC_PATH = "src/main/java/";
    // Path to package under which to scan for @Entity classes to export
    private static final String MODEL_PACKAGE_PATH = "com/renison/model/";

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            //            Configuration configuration = new Configuration();
            // this makes sure hibernate scans for @Table,@Column annotation embedded in the source code 
            //            registerAllModels(configuration, SRC_PATH, MODEL_PACKAGE_PATH);
            //            return configuration.configure().buildSessionFactory();
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
            SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
            return sessionFactory;
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Deprecated
    // currently not needed since hibernate autodetects all annotated classes in src/main/java
    private static void registerAllModels(Configuration configuration, String srcPath, String modelPackagePath) {
        // Registering model classes
        Path moduleDir = Paths.get(srcPath, modelPackagePath);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(moduleDir)) {
            for (Path file : stream) {
                if (Files.isDirectory(file) || !file.toString().endsWith(".java")) {
                    continue;
                }

                String className = file.toString().substring(srcPath.length());
                // Remove ".java" at the end of the file name to get the class name
                className = className.substring(0, className.length() - 5).replaceAll("/", ".");

                Class<?> modelClass = Class.forName(className);
                if (modelClass.getAnnotation(Entity.class) != null) {
                    configuration.addAnnotatedClass(modelClass);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            logger.fatal("Could not load model classes.");
            logger.fatal(e);
            System.exit(1);
        }
    }

    //    public static void generateSQL(String operationMode) {
    //        logger.debug("about to export schema");
    //
    //        if (operationMode.equals("create")) {
    //            EnversSchemaGenerator enversSchemaExport = new EnversSchemaGenerator(cfg);
    //            org.hibernate.tool.hbm2ddl.SchemaExport export = enversSchemaExport.export();
    //            export.setOutputFile("create.sql").setDelimiter(";").create(false, true);
    //            export.execute(true, false, false, false);
    //        } else {
    //            SchemaUpdate update = new SchemaUpdate(cfg);
    //            update.setOutputFile("update.sql");
    //            update.setDelimiter(";");
    //            update.execute(true, false);
    //        }
    //    }
}
