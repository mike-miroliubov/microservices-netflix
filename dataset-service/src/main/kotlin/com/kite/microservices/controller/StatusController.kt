package com.kite.microservices.controller

import com.netflix.discovery.EurekaClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/status")
class StatusController(
        val registryClient: EurekaClient,
        @Value("\${spring.application.name}") val appName: String) {

    @GetMapping("/name")
    fun getApplicationName(): String {
        return registryClient.getApplication(appName).name
    }
}