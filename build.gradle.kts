import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.Properties
import java.io.FileOutputStream


val jvmTarget = "17"
val junitJupiterVersion = "5.12.0"
val mockkVersion = "1.13.2"
val kotestVersion = "5.5.5"
val logbackClassicVersion = "1.5.16"
val jacksonVersion = "2.18.2"

group = "no.nav.hm.grunndata"
version = properties["version"] ?: "local-build"

plugins {
    kotlin("jvm") version "1.9.21"
    kotlin("kapt") version "1.9.21"
    id("com.github.ben-manes.versions") version "0.47.0"
    id("java")
    id("maven-publish")
}

configurations.all {
    resolutionStrategy {
        failOnChangingVersions()
    }
}

dependencies {
    api("ch.qos.logback:logback-classic:$logbackClassicVersion")
    implementation("com.fasterxml.jackson.core:jackson-annotations:$jacksonVersion")
    testImplementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    testImplementation("com.fasterxml.jackson.core:jackson-core:$jacksonVersion")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
    testImplementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitJupiterVersion")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
}

val githubUser: String? by project
val githubPassword: String? by project

publishing {
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/navikt/hm-grunndata-rapid-dto")
            credentials {
                username = githubUser
                password = githubPassword
            }
        }
    }
    publications {
        create<MavenPublication>("mavenJava") {

            pom {
                name.set("hm-grunndata-rapid-dto")
                description.set("hm grunndata rapid dto lib")
                url.set("https://github.com/navikt/hm-grunndata-rapid-dto")

                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }

                scm {
                    connection.set("scm:git:https://github.com/navikt/hm-grunndata-rapid-dto.git")
                    developerConnection.set("scm:git:https://github.com/navikt/hm-grunndata-rapid-dto.git")
                    url.set("https://github.com/navikt/hm-grunndata-rapid-dto")
                }
            }
            from(components["java"])
        }
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
    gradleVersion = "8.5"
}



tasks.register("generateVersionProp") {
    doLast {
        val props = Properties()
        props["version"] = project.version.toString()
        val ous = FileOutputStream(File("$buildDir/resources/main/version.properties"))
        props.store(ous, null)
        ous.close()
    }
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://packages.confluent.io/maven/")
    maven("https://github-package-registry-mirror.gc.nav.no/cached/maven-release")
}

