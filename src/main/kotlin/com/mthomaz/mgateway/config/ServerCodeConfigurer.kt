package com.mthomaz.mgateway.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.codec.ServerCodecConfigurer

@Configuration
class ServerConfig {

    @Bean
    fun serverCodecConfigurer(): ServerCodecConfigurer? {
        return ServerCodecConfigurer.create()
    }
}
