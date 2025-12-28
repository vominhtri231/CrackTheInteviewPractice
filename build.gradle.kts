plugins {
    java
}

repositories {
    mavenCentral()
    mavenLocal()
}



dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
}

tasks.compileJava {
    sourceCompatibility = "1.8"
}

tasks.test {
    useJUnitPlatform()
}