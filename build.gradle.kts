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

    dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0-Beta")
    }

    tasks {
        named<JavaExec>("run") {
            standardInput = System.`in`
        }
    }
}