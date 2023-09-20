tasks {
    val bootJarTask = named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
        enabled = false
    }

    val jarTask = named<Jar>("jar") {
        enabled = true
    }
}