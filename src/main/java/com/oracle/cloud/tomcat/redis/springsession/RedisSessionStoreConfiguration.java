package com.oracle.cloud.tomcat.redis.springsession;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
public class RedisSessionStoreConfiguration {
    
    @Bean
    public LettuceConnectionFactory connectionFactory() {
        String redisHost = System.getenv().getOrDefault("REDIS_HOST_ENV", "192.168.99.100");
        String redisPort = System.getenv().getOrDefault("REDIS_PORT_ENV", "6379");
        System.out.println("Redis @ "+ redisHost + ":" + redisPort);
        return new LettuceConnectionFactory(redisHost, Integer.valueOf(redisPort));        
    }
}
