plugins {
    id("java")
    application
}

group = "org.example"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    implementation(libs.guava)

    // https://mvnrepository.com/artifact/org.fusesource.jansi/jansi
    implementation("org.fusesource.jansi:jansi:2.4.2")
    implementation("com.microsoft.sqlserver:mssql-jdbc:13.2.1.jre11")
    // Зависимость от модуля module-core
    implementation(project(":module-core"))
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}
application {
    mainClass = "by.gonol.project.App"
}