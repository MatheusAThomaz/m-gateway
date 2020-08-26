package com.mthomaz.mgateway.controller

import com.mthomaz.mgateway.service.RouteService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono


@RestController
class RouteController (
        private val routeService: RouteService) {



    @PostMapping(path = ["/update"])
    fun update() : Mono<ResponseEntity<String>> {

        return try {
            routeService.update()
            Mono.just(ResponseEntity.ok("Updated"))

        } catch (e: Exception) {
            Mono.just(ResponseEntity.ok(e.toString()))
        }

    }


}