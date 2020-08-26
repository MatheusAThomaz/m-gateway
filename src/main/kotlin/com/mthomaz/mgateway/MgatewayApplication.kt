package com.mthomaz.mgateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MgatewayApplication

fun main(args: Array<String>) {
	runApplication<MgatewayApplication>(*args)
}
