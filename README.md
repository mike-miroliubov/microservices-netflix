# Description
A sample application to illustrate a microservice setup using Spring Cloud and Netflix OSS.

# Technology stack
* Kotlin
* Spring Boot 2+
* Spring Cloud (Hoxton.SR8)
* Netflix OSS (Eureka, Zuul, Ribbon, Hystrix, Feign)
* Gradle

# Architecture

```
            +--------------------------------------------------------------------------------------------------------------+
            |                                                                                                              | ------------------|
            |                                             Registry (Eureka)                                                |                   |
            |                                                                                                              |------------       |
            +--------------------------------------------------------------------------------------------------------------+           |       |
                      |                                       |                                                  |                     |       |
                      |                                       |                                                  |                     |       |
                      |                                       |                                                  |                     |       |
                      |                                       |                                                  |                     |       |
                      |                               +------------------+           REST API           +-----------------+            |       |
                      |                               |                  |         (with Feign)         |                 |            |       |
                      |                            ---| DataSet Service  |-------------------------------   Vcf Service   |            |       |
                      |                       ----/   |                  |-\                       --/ |                 |            |       |
 Incoming   +-------------------+        ----/        +------------------+  ---\     Ribbon    ---/    +-----------------+            |       |
 requests   |                   |   ----/                                       ---\        --/                                       |       |
  --------- |   Gateway (Zuul)  |-\/                                                --\  --/                                         |       |
            |                   |  ---\                                               --/-\                                          |       |
            +-------------------+      ---\                                        --/     --\                                       |       |
                                           ---\      +------------------+     ---/           ---\     +-----------------+            |       |
                                               ---\  |                  |  --/                   ---\ |                 |            |       |
                                                   -- | DataSet Service  |------------------------------|   Vcf Service   |------------+       |
                                                      |                  |        load balancing        |                 |                    |
                                                      +------------------+                              +-----------------+                    |
                                                              |                                                                                |
                                                              |                                                                                |
                                                              ---------------------------------------------------------------------------------+
```                                                                                                                                                                                        

Application consistes of the following microservices
* **Registry** (powered by Netflix Eureka) - a service registry for Service Discovery. 
  All microservices should be registered with it.
  
* **Gateway** (powered by Netflix Zuul) - a gateway (proxy) for external clients to interact with the application. 
  Exposes REST endpoints of all microservices, that should be reachable from outside, in a single REST API. 
  Performs server-side load balancing if multiple instances of a microservice is available.
  
* **DataSet Service** - a sample microservice that provides some functionality for other microservices in the ecosystem.
* **Vcf Service** - a sample microservice that acts as a client for DataSet Service, sending requests through REST. 
  Uses:
  * Feign as a REST client
  * Ribbon as a client-side load balancer: reaches out to Eureka to fetch available DataSet Service's instances and 
    queries them in a round-robin (for illustration purposes).
  * Hystrix as a Circuit Breaker implementation: in case DataSet Service is not available, default logic is invocated 
    (displaying error message in this case, but could possibly return a cached value).   


# How to run locally
0. Build everything with gradle
   `gradlew clean build`
1. Run Registry
   ```
   java -jar registry/build/libs/registry-1.0.0.jar
   ```
2. Run Gateway
   ```
   java -jar gateway/build/libs/gateway-1.0.0.jar
   ```
3. Run DataSet Service in 2 instances:
   ```
   java -Dserver.port=8082 -jar dataset-service/build/libs/dataset-service-1.0.0.jar
   java -Dserver.port=8083 -jar dataset-service/build/libs/dataset-service-1.0.0.jar
   ```
4. Run Vcf Service in 2 instances:
   ```
   java -Dserver.port=8080 -jar vcf-service/build/libs/vcf-service-1.0.0.jar
   java -Dserver.port=8081 -jar vcf-service/build/libs/vcf-service-1.0.0.jar
   ```
   
# How to verify everything is working:
1. Go to Eureka Dashboard: http://localhost:8761/
2. Go to http://localhost:8762/actuator/routes, see
   ```
   {"/vcf/**":"vcf-service"}
   ```
   Meaning: calls to http://localhost:8762/vcf/** are forwarded to Vcf Service. DataSet Service won't be exposed
   to external clients.
3. Go to Gateway's endpoint http://localhost:8762/vcf/get-name refresh a couple of times. You should see:
   3.1. `DATASET-SERVICE` in the output
   3.2. `Getting DataSet Service name` in logs of both Vcf Service instances
   3.3. `ServerListLoadBalancer` logs in logs of both Vcf Service instances
   3.4. `Got hit from <your IP>` in logs of both DataSet Services