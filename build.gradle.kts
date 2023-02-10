import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


val jvmTarget = "17"
val micronautVersion="3.8.4"
val junitJupiterVersion = "5.9.0"
val jacksonVersion = "2.13.4"
val tcVersion= "1.16.3"
val mockkVersion = "1.13.2"
val kotestVersion = "5.5.0"

group = "no.nav.hm"
version = properties["version"] ?: "local-build"

plugins {
    kotlin("jvm") version "1.7.0"
    kotlin("kapt") version "1.7.0"
    kotlin("plugin.allopen") version "1.7.0"
    id("java")
    id("io.micronaut.application") version "3.6.6"
}

configurations.all {
    resolutionStrategy {
        failOnChangingVersions()
    }
}

dependencies {
    // coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactive")


    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("io.micronaut:micronaut-runtime")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")

    testImplementation("io.mockk:mockk:$mockkVersion")
    testImplementation("io.micronaut.test:micronaut-test-kotest5")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestVersion")

    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.0")
    testImplementation("org.testcontainers:postgresql:${tcVersion}")
}

micronaut {
    version.set(micronautVersion)
    testRuntime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
    }
}

java {
    sourceCompatibility = JavaVersion.toVersion(jvmTarget)
    targetCompatibility = JavaVersion.toVersion(jvmTarget)
    withSourcesJar()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = jvmTarget
}

tasks.named<KotlinCompile>("compileTestKotlin") {
    kotlinOptions.jvmTarget = jvmTarget
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("skipped", "failed")
        showExceptions = true
        showStackTraces = true
        showCauses = true
        exceptionFormat = TestExceptionFormat.FULL
        showStandardStreams = true
    }
}

tasks.withType<Wrapper> {
    gradleVersion = "7.5.1"
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://packages.confluent.io/maven/")
    maven("https://github-package-registry-mirror.gc.nav.no/cached/maven-release")

}

