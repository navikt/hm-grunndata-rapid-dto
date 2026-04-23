import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.FileOutputStream
import java.util.Properties

plugins {
    id("org.jetbrains.kotlin.jvm") version "2.1.21"
    id("org.jetbrains.kotlin.plugin.allopen") version "2.1.21"
    id("com.google.devtools.ksp") version "2.1.21-2.0.1"
    id("com.gradleup.shadow") version "9.3.1"
    id ("com.github.ben-manes.versions") version "0.51.0"
    id("java")
    id("maven-publish")
}

subprojects {
    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("com.google.devtools.ksp")
        plugin("java")
        plugin("maven-publish")
    }

    group = "no.nav.hm.grunndata"
    version = properties["version"] ?: "local-build"
    val jvmTarget = "17"
    val junitJupiterVersion = "5.12.0"
    val kotestVersion = "5.5.5"
    val logbackClassicVersion = "1.5.16"

    dependencies {
        api("ch.qos.logback:logback-classic:${logbackClassicVersion}")
        testImplementation("org.junit.jupiter:junit-jupiter-params:${junitJupiterVersion}")
        testImplementation("io.kotest:kotest-runner-junit5-jvm:${kotestVersion}")
    }

    java {
        sourceCompatibility = JavaVersion.toVersion(jvmTarget)
        targetCompatibility = JavaVersion.toVersion(jvmTarget)

        withSourcesJar()
    }

    tasks.withType<KotlinCompile> {
        compilerOptions.jvmTarget.set(JvmTarget.fromTarget(jvmTarget))
    }

    tasks.named<KotlinCompile>("compileTestKotlin") {
        compilerOptions.jvmTarget.set(JvmTarget.fromTarget(jvmTarget))
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
        gradleVersion = "8.11"
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
    }
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://github-package-registry-mirror.gc.nav.no/cached/maven-release")
}

