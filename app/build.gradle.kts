plugins {
    
    application
    java
    id("com.gradleup.shadow") version "9.3.0"
}

repositories {
    
    mavenCentral()
}

val javaFXModules = listOf("base", "controls", "fxml", "swing", "graphics", "media")
val supportedPlatforms = listOf("mac-aarch64", "linux", "mac", "win")

dependencies {
    
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    
    implementation(libs.guava)

    
    val log4jVersion = "2.25.3"
    implementation("org.apache.logging.log4j:log4j-api:$log4jVersion")
    implementation("org.apache.logging.log4j:log4j-core:$log4jVersion")

    val javaFxVersion = "23.0.2"
    implementation("org.openjfx:javafx:$javaFxVersion")
    for (platform in supportedPlatforms) {
        for (module in javaFXModules) {
            implementation("org.openjfx:javafx-$module:$javaFxVersion:$platform")
        }
    }

    
    implementation("org.postgresql:postgresql:42.7.3")
    
    
    implementation("org.hibernate.orm:hibernate-core:6.5.2.Final")

    val lombokVersion = "1.18.32"
    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")

}


java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    
    mainClass = "it.unibo.dungeonsql.Launcher"
}

tasks.named<Test>("test") {
    
    useJUnitPlatform()
}
