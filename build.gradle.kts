import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.6" apply false
    id("io.spring.dependency-management") version "1.1.3" apply false
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
}


allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")

    group = "com.kakaobank"
    version = "0.0.1-SNAPSHOT"


    dependencies {
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        testImplementation("org.springframework.boot:spring-boot-starter-test")

        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.6.4")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.4.1")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
        implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
        implementation("io.github.resilience4j:resilience4j-kotlin:1.7.1")

        testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")

        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.apache.commons:commons-lang3:3.12.0")


        implementation("com.playtika.reactivefeign:feign-reactor-webclient:3.2.11")
        implementation("com.playtika.reactivefeign:feign-reactor-cloud:3.2.11")
        implementation("com.playtika.reactivefeign:feign-reactor-spring-configuration:3.2.11")

        tasks.withType<KotlinCompile> {
            kotlinOptions {
                freeCompilerArgs += "-Xjsr305=strict"
                jvmTarget = "11"
            }
        }

        tasks.withType<Test> {
            useJUnitPlatform()
        }

        tasks.register<Wrapper>("wrapper") {
            gradleVersion = "8.2.1"
        }
        tasks.register("prepareKotlinBuildScriptModel"){}
    }
}

project(":api") {
    dependencies {
        implementation(project(":core"))
        implementation(project(":infra"))
    }
}

project(":infra"){
    dependencies {
        implementation(project(":core"))
    }
}