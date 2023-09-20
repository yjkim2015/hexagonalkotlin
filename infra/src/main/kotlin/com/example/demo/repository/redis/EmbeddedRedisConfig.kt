package com.example.demo.repository.redis

import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import redis.embedded.RedisServer
import java.io.IOException
import java.time.Duration
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy


@Configuration
@EnableRedisRepositories
@EnableCaching
class EmbeddedRedisConfig {

    @Value("\${spring.redis.port}")
    private val redisPort: Int = 0

    @Value("\${spring.redis.host}")
    private val redisHost: String = ""

    private var redisServer: RedisServer? = null

    @PostConstruct
    @Throws(IOException::class)
    fun redisServer() {
        redisServer = RedisServer(redisPort)
        redisServer!!.start()
    }

    @PreDestroy
    fun stopRedis() {
        if (redisServer != null) {
            redisServer!!.stop()
        }
    }

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory(redisHost, redisPort)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<*, *> {
        return RedisTemplate<Any, Any>().apply {
            this.setConnectionFactory(redisConnectionFactory())

            // "\xac\xed\x00" 같은 불필요한 해시값을 보지 않기 위해 serializer 설정
            this.keySerializer = StringRedisSerializer()
            this.hashKeySerializer = StringRedisSerializer()
            this.valueSerializer = StringRedisSerializer()
        }
    }

    @Bean
    fun cacheManager(cf: RedisConnectionFactory?): CacheManager? {
        val redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer<String>(StringRedisSerializer()))
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer<Any>(
                    GenericJackson2JsonRedisSerializer()
                )
            )
            .entryTtl(Duration.ofMinutes(1L))
        return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(cf!!)
            .cacheDefaults(redisCacheConfiguration).build()
    }
}