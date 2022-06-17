plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")

    implementation("com.google.guava:guava:30.1.1-jre")
}

application {
    mainClass.set("org.hg.tg.Main")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}