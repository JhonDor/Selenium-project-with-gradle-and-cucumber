plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.seleniumhq.selenium:selenium-java:4.32.0")
    testImplementation("io.cucumber:cucumber-java:7.22.1")
    testImplementation("io.cucumber:cucumber-junit:7.22.1")
    testImplementation("io.github.bonigarcia:webdrivermanager:6.1.0")


    testImplementation(platform("org.junit:junit-bom:5.12.2"))
    testImplementation(platform("io.cucumber:cucumber-bom:7.22.2"))
    testImplementation(platform("org.assertj:assertj-bom:3.27.3"))

    testImplementation("io.cucumber:cucumber-java")
    testImplementation("io.cucumber:cucumber-junit-platform-engine")
    testImplementation("org.junit.platform:junit-platform-suite")
    testImplementation("org.assertj:assertj-core")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
    // Work around. Gradle does not include enough information to disambiguate
    // between different examples and scenarios.
    systemProperty("cucumber.junit-platform.naming-strategy", "long")
}

tasks.register<JavaExec>("cucumber") {
    mainClass.set("io.cucumber.core.cli.Main")
    classpath = sourceSets["test"].runtimeClasspath
    val tag = if (project.hasProperty("cucumberTag")) project.property("cucumberTag") else "@defaultTag"
    args = listOf(
        "--plugin", "pretty",
        "--plugin", "html:build/reports/cucumber",
        "--glue", "stepDefinitions",
        "--tags", tag.toString(),
        "src/test/resources"
    )
}

