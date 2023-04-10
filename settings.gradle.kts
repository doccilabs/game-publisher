rootProject.name = "game-publisher"

pluginManagement {
    val kotlinVersion = "1.8.10"
    val springBootVersion = "2.7.9"
    val dependencyManagementVersion = "1.0.15.RELEASE"
    val protobufVersion = "0.8.15" // for gRPC
    val queryDslPluginVersion = "1.0.10"

    plugins {
        id("org.springframework.boot") version springBootVersion
        id("io.spring.dependency-management") version dependencyManagementVersion
        id("org.jetbrains.kotlin.plugin.allopen") version kotlinVersion
        id("org.jetbrains.kotlin.plugin.noarg") version kotlinVersion
        id("com.ewerk.gradle.plugins.querydsl") version queryDslPluginVersion

        kotlin("jvm") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion
        kotlin("plugin.jpa") version kotlinVersion

        kotlin("kapt") version kotlinVersion

        id("com.google.protobuf") version protobufVersion // gRPC
    }
}

include(
    "commons",
    "commons:common"
)

include("domain")

include(
    "port",
    "port:messaging-interface",
    "port:repository",
    "port:service",
    "port:dto"
)

include(
    "adapter",
    "adapter:dao",
    "adapter:application-admin",
    "adapter:application-user",
    "adapter:messaging-producer"
)