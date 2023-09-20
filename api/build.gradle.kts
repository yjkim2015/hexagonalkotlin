dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
}

val appMainClassName = "com.example.demo.ApiApplication"
tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    mainClass.set(appMainClassName)
}