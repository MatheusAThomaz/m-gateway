package com.mthomaz.mgateway.controller

import com.mthomaz.mgateway.service.RouteService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono


@RestController
class RouteController (
        private val routeService: RouteService) {

    @PostMapping(path = ["/save"])
    fun toggles() : Mono<ResponseEntity<String>> {

        return routeService.save()!!.map {
            return@map ResponseEntity.ok("Ola")
        }


    }


}