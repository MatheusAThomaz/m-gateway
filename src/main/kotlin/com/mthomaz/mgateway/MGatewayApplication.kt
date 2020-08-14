package com.mthomaz.mgateway

import com.mthomaz.mgateway.config.repository.RedisRouteDefinitionRepository
import org.springframework.boot.autoconfigure.AutoConfigureAfter
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication
class MGatewayApplication

fun main(args: Array<String>) {
	runApplication<MGatewayApplication>(*args)
}
