dependencies {
    api(project(":commons:common"))

    api(project(":port:dto"))
    api(project(":port:service"))

    implementation("org.springframework.boot:spring-boot-starter-aop")
}