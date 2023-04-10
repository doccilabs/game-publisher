val sqsVersion = "1.12.349"

dependencies {
    api(project(":commons:common"))

    api(project(":port:dto"))
    api(project(":port:service"))
    api(project(":port:messaging-interface"))

    api(project(":adapter:messaging-producer"))

    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("com.amazonaws:aws-java-sdk-sqs:${sqsVersion}")
}