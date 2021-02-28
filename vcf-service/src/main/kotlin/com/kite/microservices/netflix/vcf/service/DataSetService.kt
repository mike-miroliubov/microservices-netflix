package com.kite.microservices.netflix.vcf.service

import com.kite.microservices.netflix.vcf.client.DataSetClient
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import org.springframework.stereotype.Service

@Service
class DataSetService(val dataSetClient: DataSetClient) {
    @HystrixCommand(fallbackMethod = "getDataSetServiceUnavailable")
    fun getDataSetServiceName(): String {
        return dataSetClient.getApplicationName()
    }

    private fun getDataSetServiceUnavailable(): String {
        return "Data Set Service unavailable";
    }
}