package com.kite.microservices.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient("dataset-service")
interface DataSetClient {
    @GetMapping("/status/name")
    fun getApplicationName(): String
}