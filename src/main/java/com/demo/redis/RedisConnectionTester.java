package com.demo.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisConnectionTester implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(RedisConnectionTester.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Testing Redis connection...");

        try (RedisConnection connection = redisTemplate.getConnectionFactory().getConnection()) {
            logger.info("Connected to Redis: {}", connection.ping());
        } catch (Exception e) {
            logger.error("Redis connection test failed: {}", e.getMessage(), e);
        }
    }
}
