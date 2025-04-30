plugins {
    id("java")
    id("application") // necesario para usar el bloque `application`
}

group = "org.example"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("org.inici.JocDeRol")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.google.code.gson:gson:2.11.0")
}

tasks.test {
    useJUnitPlatform()
}