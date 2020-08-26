package com.mthomaz.mgateway.service

import com.mthomaz.mgateway.config.repository.RedisRouteDefinitionRepository
import mu.KLogger
import mu.KotlinLogging
import org.springframework.cloud.gateway.filter.FilterDefinition
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition
import org.springframework.cloud.gateway.route.RouteDefinition
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.net.URI


@Service
class RouteService(private val routeDefinitionRepository: RedisRouteDefinitionRepository,
                   private val log: KLogger = KotlinLogging.logger {}) {

    fun update() {
        try {
            routeDefinitionRepository.routeDefinitions
        } catch (e: Exception) {
            log.error { "c=RouteService m=update e=$e" }
        }
    }

}