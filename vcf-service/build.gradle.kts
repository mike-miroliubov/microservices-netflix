plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
}

val springCloudVersion = "Hoxton.SR8"

dependencies {
    implementation(platform("org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-ribbon")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-hystrix")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}