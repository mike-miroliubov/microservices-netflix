package com.kite.microservices.netflix.vcf.client

import com.kite.microservices.netflix.vcf.config.RibbonConfig
import org.springframework.cloud.netflix.ribbon.RibbonClient
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient("dataset-service")
@RibbonClient(name = "dataset-service", configuration = [RibbonConfig::class])
interface DataSetClient {
    @GetMapping("/status/name")
    fun getApplicationName(): String
}