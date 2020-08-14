package com.mthomaz.mgateway.filters

import mu.KLogger
import mu.KotlinLogging
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory
import org.springframework.cloud.gateway.route.Route
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClientResponse

@Component
class GatewayLogFilter(
        private val log: KLogger = KotlinLogging.logger {}
) : GatewayFilterFactory<GatewayLogFilter.Config> {

    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            logGatewayRequest(exchange)
            chain.filter(exchange).then(logGatewayResponse(exchange))
        }
    }

    private fun logGatewayRequest(exchange: ServerWebExchange) {
        log.info {
            val route = (exchange.attributes[ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR] as Route)
            "c=GatewayLogFilter, m=logGatewayRequest, " +
                    "method=${exchange.request.method}, " +
                    "to=${exchange.request.uri}, " +
                    "route=${route.uri}${exchange.request.path}, " +
                    "headers=${filterLog(exchange.request.headers)}"
        }
    }

    private fun logGatewayResponse(exchange: ServerWebExchange): Mono<Void> {
        return Mono.fromRunnable {
            if (isHttpError(exchange)) {
                log.error {
                    val route = (exchange.attributes[ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR] as Route)
                    val clientResponse = (exchange.attributes[ServerWebExchangeUtils.CLIENT_RESPONSE_ATTR] as HttpClientResponse)
                    "c=GatewayLogFilter, m=logGatewayResponse, " +
                            "status=${exchange.response.statusCode}, " +
                            "to=${route.uri}${clientResponse.uri()}, " +
                            "route=${exchange.request.uri}, " +
                            "headers=${filterLog(exchange.response.headers)}"
                }
            }
        }
    }

    private fun isHttpError(exchange: ServerWebExchange) =
            exchange.response.statusCode?.let { it.series() } != HttpStatus.Series.SUCCESSFUL

    private fun filterLog(header: HttpHeaders): Map<String, MutableList<String>> {
        val byPass = header.getFirst("By-Pass");
        if (byPass != null) {
            return header
        }

        return header
    }

    override fun newConfig(): Config {
        return Config(GatewayLogFilter::class.simpleName)
    }

    data class Config(
            private val name: String?
    )

}