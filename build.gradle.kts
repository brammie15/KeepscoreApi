plugins {
    id("java")
}

group = "dev.brammie15"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.sparkjava:spark-core:2.9.4")
    implementation("mysql:mysql-connector-java:8.0.32")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("ch.qos.logback:logback-classic:1.2.9")
    implementation("org.slf4j:slf4j-api:1.7.32")
    implementation("org.hibernate.orm:hibernate-core:6.2.0.Final")

}

tasks.test {
    useJUnitPlatform()
}