
plugins {

    application
    id("com.gradleup.shadow") version "9.2.2"
}


repositories {

    mavenCentral()
}

dependencies {

    implementation(libs.guava)
    implementation(platform("software.amazon.awssdk:bom:2.34.3"))
    implementation("software.amazon.awssdk:ssm")
    implementation("org.slf4j:slf4j-api:2.0.17")
    implementation("org.slf4j:slf4j-simple:2.0.17")

}

 testing {
     suites {

         val test by getting(JvmTestSuite::class) {

             useJUnit("4.13.2")
         }
     }
 }


java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    // Define the main class for the application.
    mainClass = "org.sandbox.App"
}

