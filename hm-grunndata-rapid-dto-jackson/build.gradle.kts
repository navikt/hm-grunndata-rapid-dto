val jacksonVersion = "3.1.3"

dependencies {
    implementation(project(":hm-grunndata-rapid-dto"))
    testImplementation("tools.jackson.module:jackson-module-kotlin:${jacksonVersion}")
    testImplementation("tools.jackson.core:jackson-core:${jacksonVersion}")
    testImplementation("tools.jackson.core:jackson-databind:${jacksonVersion}")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("io.github.classgraph:classgraph:4.8.179")
}

val githubUser: String? by project
val githubPassword: String? by project

tasks.test {
    useJUnitPlatform()
}

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
                name.set("hm-grunndata-rapid-dto-jackson")
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


