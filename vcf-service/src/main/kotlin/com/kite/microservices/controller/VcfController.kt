package com.kite.microservices.controller

import com.kite.microservices.service.DataSetService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/vcf")
class VcfController(val dataSetService: DataSetService) {
    @GetMapping("get-name")
    fun getDataSetServiceName(): String {
        return dataSetService.getDataSetServiceName()
    }
}