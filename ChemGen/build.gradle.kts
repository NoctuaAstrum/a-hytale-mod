plugins {
    id("java")
}

group = "com.github.NoctuaAstrum"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    /*ivy{
        url=uri("https://mvnrepository.com/artifact")
    }*/
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    //implementation("com.google.code.gson:gson:2.14.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.21.3")

}

tasks.test {
    useJUnitPlatform()
}