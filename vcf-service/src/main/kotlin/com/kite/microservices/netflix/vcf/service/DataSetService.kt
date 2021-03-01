package com.kite.microservices.netflix.vcf.service

import com.kite.microservices.netflix.vcf.client.DataSetClient
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class DataSetService(val dataSetClient: DataSetClient) {
    companion object {
        val logger = LoggerFactory.getLogger(DataSetService.javaClass)
    }

    @HystrixCommand(fallbackMethod = "getDataSetServiceUnavailable")
    fun getDataSetServiceName(): String {
        logger.info("Getting DataSet Service name")
        return dataSetClient.getApplicationName()
    }

    private fun getDataSetServiceUnavailable(): String {
        return "Data Set Service unavailable";
    }
}