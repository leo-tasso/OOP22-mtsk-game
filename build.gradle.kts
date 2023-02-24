plugins {
    application
    java
    id("org.danilopianini.gradle-java-qa") version "0.40.0"
    id("com.github.johnrengelman.shadow") version "7.0.0"

}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("com.github.spotbugs:spotbugs-annotations:4.7.3")
    val jUnitVersion = "5.9.1"
    testImplementation("org.junit.jupiter:junit-jupiter-api:$jUnitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$jUnitVersion")
}

//val mainClass: String by project

application {
    // The following allows to run with: ./gradlew -PmainClass=it.unibo.oop.MyMainClass run
    //mainClass.set(project.properties["mainClass"].toString())
    mainClass.set("game.Engine")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events(*org.gradle.api.tasks.testing.logging.TestLogEvent.values())
        showStandardStreams = true
    }
}