plugins {
    java
}

dependencies {
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}
// В этой секции можно указать общие параметры для всех модулей