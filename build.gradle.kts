plugins {
//    id("org.jetbrains.kotlin.jvm") version "1.5.31" // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    application
    idea
    kotlin("jvm") version "1.5.31"
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom")) // Align versions of all Kotlin components
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8") // Use the Kotlin JDK 8 standard library.
    implementation("io.github.hindigarv:shabdkosh:0.2.0")
    implementation("org.telegram:telegrambots:6.0.1")
    implementation("org.slf4j:slf4j-simple:1.7.36")
    testImplementation("org.jetbrains.kotlin:kotlin-test") // Use the Kotlin test library.
    testImplementation("org.assertj:assertj-core:3.23.1")
}

application {
    mainClass.set("org.hindigarv.telegram.AppKt")
}

tasks.jar {
    manifest.attributes["Main-Class"] = "org.hindigarv.telegram.AppKt"
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}