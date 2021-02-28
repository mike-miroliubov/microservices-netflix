package com.kite.microservices.netflix.dataset.controller

import com.netflix.discovery.EurekaClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/status")
class StatusController(
        val registryClient: EurekaClient,
        @Value("\${spring.application.name}") val appName: String) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(StatusController::class.java)
    }

    @GetMapping("/name")
    fun getApplicationName(httpRequest: HttpServletRequest): String {
        logger.info("Got hit from ${httpRequest.remoteAddr}")
        return registryClient.getApplication(appName).name
    }
}