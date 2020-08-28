package com.mthomaz.mgateway.component

import org.springframework.cloud.gateway.event.RefreshRoutesEvent

import org.springframework.context.ApplicationEventPublisher

import org.springframework.context.ApplicationEventPublisherAware
import org.springframework.stereotype.Component


@Component
class GatewayRoutesRefresher : ApplicationEventPublisherAware {

    var publisher: ApplicationEventPublisher? = null

    override fun setApplicationEventPublisher(applicationEventPublisher: ApplicationEventPublisher) {
        publisher = applicationEventPublisher
    }

    fun refreshRoutes() {
        publisher!!.publishEvent(RefreshRoutesEvent(this))
    }
}