val micronautVersion="4.10.12"

plugins {

    id("io.micronaut.library") version "4.6.2"
}

dependencies {
    implementation(project(":hm-grunndata-rapid-dto"))
    ksp("io.micronaut.serde:micronaut-serde-processor")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    testImplementation("io.github.classgraph:classgraph:4.8.179")
}

micronaut {
    version.set(micronautVersion)
    testRuntime("netty")
    testRuntime("junit5")
    processing {
        incremental(false)
        annotations("no.nav.hm.grunndata.rapid.dto.*")
        annotations("no.nav.hm.grunndata.rapid.dto.serde.*")

    }
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
                name.set("hm-grunndata-rapid-dto-serde")
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


