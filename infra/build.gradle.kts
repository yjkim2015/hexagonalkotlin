dependencies {
    implementation("org.projectlombok:lombok:1.18.22")
    runtimeOnly("com.h2database:h2")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-redis") // 추가
    implementation("it.ozimov:embedded-redis:0.7.2")
}