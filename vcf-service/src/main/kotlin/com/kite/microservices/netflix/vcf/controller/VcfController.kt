package com.kite.microservices.netflix.vcf.controller

import com.kite.microservices.netflix.vcf.service.DataSetService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class VcfController(val dataSetService: DataSetService) {
    @GetMapping("/get-name")
    fun getDataSetServiceName(): String {
        return dataSetService.getDataSetServiceName()
    }
}