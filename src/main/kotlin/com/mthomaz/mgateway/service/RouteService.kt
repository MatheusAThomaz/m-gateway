package com.mthomaz.mgateway.service

import com.mthomaz.mgateway.config.repository.RedisRouteDefinitionRepository
import org.springframework.cloud.gateway.filter.FilterDefinition
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition
import org.springframework.cloud.gateway.route.RouteDefinition
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.net.URI


@Service
class RouteService (private val routeDefinitionRepository: RedisRouteDefinitionRepository) {

    fun save () : Mono<Void>? {

        val routeDefinition = RouteDefinition()

        routeDefinition.id = "mock"
        routeDefinition.uri = URI.create("https://5f35c5115b91f60016ca50f5.mockapi.io")


        val prefixPathFilterDefinition = FilterDefinition(
                "GatewayLogFilter")

        routeDefinition.filters = listOf(prefixPathFilterDefinition)

        val methodRoutePredicateDefinition = PredicateDefinition(
                "Method=GET")

        val pathRoutePredicateDefinition = PredicateDefinition(
                "Path=/mock/route")

        routeDefinition.predicates = arrayListOf(methodRoutePredicateDefinition, pathRoutePredicateDefinition)


        return routeDefinitionRepository.save(Mono.just(routeDefinition)).map {
            return@map it
        }
    }

}