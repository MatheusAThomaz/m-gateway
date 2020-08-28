package com.mthomaz.mgateway.service

import com.mthomaz.mgateway.component.GatewayRoutesRefresher
import com.mthomaz.mgateway.config.RedisConfig
import com.mthomaz.mgateway.config.repository.RedisRouteDefinitionRepository
import mu.KLogger
import mu.KotlinLogging
import org.springframework.cloud.gateway.filter.FilterDefinition
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition
import org.springframework.cloud.gateway.route.CachingRouteDefinitionLocator
import org.springframework.cloud.gateway.route.CachingRouteLocator
import org.springframework.cloud.gateway.route.RouteDefinition
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.context.ApplicationContext
import org.springframework.context.support.AbstractApplicationContext
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.net.URI


@Service
class RouteService(private val applicationContext: ApplicationContext,
                   private val log: KLogger = KotlinLogging.logger {},
                   private val gatewayRoutesRefresher: GatewayRoutesRefresher) {

    fun update() {
        try {
            gatewayRoutesRefresher.refreshRoutes()

        } catch (e: Exception) {
            log.error { "c=RouteService m=update e=$e" }
        }
    }
}