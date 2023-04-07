dependencies {

}

// event-interface는 빌드 대상에서 제외한다
tasks {
    named<Jar>("jar") {
        enabled = true
    }

    named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
        enabled = false
    }
}