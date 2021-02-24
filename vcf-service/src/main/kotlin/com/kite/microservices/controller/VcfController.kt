package com.kite.microservices.controller

import com.kite.microservices.client.DataSetClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/vcf")
class VcfController(val dataSetClient: DataSetClient) {
    @GetMapping("get-name")
    fun getDataSetServiceName(): String {
        return dataSetClient.getApplicationName()
    }
}