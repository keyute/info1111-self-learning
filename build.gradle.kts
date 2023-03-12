plugins {
    kotlin("jvm") version "1.8.10" apply false
    application
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "application")

    group = "io.keyute"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    application {
        mainClass.set("io.keyute.${project.name.replace("-", "_")}.MainKt")
    }

    tasks {
        named<JavaExec>("run") {
            standardInput = System.`in`
        }
    }
}