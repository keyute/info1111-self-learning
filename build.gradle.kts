plugins {
    kotlin("jvm") version "1.8.10"
    application
}

repositories {
    mavenCentral()
}

group = "io.keyute"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("Main.kt")
}