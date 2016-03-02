package com.renison;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import com.renison.util.Util;

@Configuration
public class RestRepoConfig extends RepositoryRestMvcConfiguration {
    Logger logger = Logger.getLogger(RestRepoConfig.class);

    @Override
    protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        try {
            Util.forEachModel((modelClass) -> config.exposeIdsFor(modelClass));
        } catch (ClassNotFoundException | IOException e) {
            logger.fatal("Cannot configure rest repo for model classes");
            logger.fatal(e);
        }
    }
}