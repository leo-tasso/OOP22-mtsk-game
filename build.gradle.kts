plugins {
    application
    java
    id("org.danilopianini.gradle-java-qa") version "0.40.0"
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("com.github.spotbugs:spotbugs-annotations:4.7.3")
}

val mainClass: String by project

application {
    // The following allows to run with: ./gradlew -PmainClass=it.unibo.oop.MyMainClass run
    //mainClass.set(project.properties["mainClass"].toString())
    mainClass.set("game.Game")
}