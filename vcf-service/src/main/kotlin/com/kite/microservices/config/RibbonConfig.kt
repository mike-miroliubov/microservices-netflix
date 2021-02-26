package com.kite.microservices.config

import com.netflix.loadbalancer.IPing
import com.netflix.loadbalancer.IRule
import com.netflix.loadbalancer.RoundRobinRule
import com.netflix.niws.loadbalancer.NIWSDiscoveryPing
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RibbonConfig {
    @Bean
    fun ping(): IPing = NIWSDiscoveryPing()

    @Bean
    fun rule(): IRule = RoundRobinRule()

}