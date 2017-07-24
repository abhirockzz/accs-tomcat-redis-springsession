package com.oracle.cloud.tomcat.redis.springsession;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

public class Init extends AbstractHttpSessionApplicationInitializer { 

        public Init() {
                super(RedisSessionStoreConfiguration.class); 
        }
}
