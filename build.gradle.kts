plugins {
    application
    java
    id("org.danilopianini.gradle-java-qa") version "0.40.0"
    id("com.github.johnrengelman.shadow") version "7.0.0"

}

repositories {
    mavenCentral()
}

val javaFXModules = listOf(
    "base",
    "controls",
    "fxml",
    "swing",
    "graphics"
)

val supportedPlatforms = listOf("linux", "mac", "win") // All required for OOP

dependencies {

    val javaFxVersion = 15
    for (platform in supportedPlatforms) {
        for (module in javaFXModules) {
            implementation("org.openjfx:javafx-$module:$javaFxVersion:$platform")
        }
    }

    compileOnly("com.github.spotbugs:spotbugs-annotations:4.7.3")
    val jUnitVersion = "5.9.1"
    testImplementation("org.junit.jupiter:junit-jupiter-api:$jUnitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$jUnitVersion")
}

//val mainClass: String by project

application {
    // The following allows to run with: ./gradlew -PmainClass=it.unibo.oop.MyMainClass run
    //mainClass.set(project.properties["mainClass"].toString())
    mainClass.set("game.ControllerImpl")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events(*org.gradle.api.tasks.testing.logging.TestLogEvent.values())
        showStandardStreams = true
    }
}