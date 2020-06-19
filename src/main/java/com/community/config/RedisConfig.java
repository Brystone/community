package com.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @ClassName RedisConfig
 * @Description
 */

@Configuration
public class RedisConfig {
    public RedisConfig() {
    }

    @Bean                                              //声明连接工厂
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate();
        template.setConnectionFactory(factory);
        //java 数据存到redis里去，设置Java序列化的方式
        //key
        template.setKeySerializer(RedisSerializer.string());
        //value
        template.setValueSerializer(RedisSerializer.json());
        //hash 的 key
        template.setHashKeySerializer(RedisSerializer.string());
        //hash 的 value
        template.setHashValueSerializer(RedisSerializer.json());

        template.afterPropertiesSet();
        return template;
    }
}
