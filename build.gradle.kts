import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath(kotlin("gradle-plugin", version = "1.3.72"))
    }
}

plugins {
    kotlin("jvm") version "1.3.72"
}

repositories {
    mavenCentral()
    maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
}

dependencies {
    testImplementation("io.cucumber:cucumber-java8:6.2.2")
    testImplementation("io.cucumber:cucumber-junit:6.2.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
    testImplementation("de.monochromata.cucumber:reporting-plugin:4.0.68")
    testImplementation("com.squareup.retrofit2:retrofit:2.9.0")
    testImplementation("com.squareup.retrofit2:converter-gson:2.9.0")
    testImplementation(kotlin("stdlib-jdk8"))
    testImplementation("org.yaml:snakeyaml:1.26")
}

tasks.test {
    environment("ENV", "qa")
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}