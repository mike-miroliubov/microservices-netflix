package com.kite.microservices

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class DataSetServiceApplication

fun main(args: Array<String>) {
	runApplication<DataSetServiceApplication>(*args)
}
