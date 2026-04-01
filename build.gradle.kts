import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.jetbrains.kotlin.gradle.dsl.JvmDefaultMode
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

buildscript {
    dependencies {
        classpath("org.postgresql:postgresql:42.7.10")
        classpath("org.flywaydb:flyway-database-postgresql:12.2.0")
    }
    repositories {
        mavenCentral()
    }
}

plugins {
    id("org.jetbrains.kotlin.jvm") version "2.3.20"
    application
    id("org.flywaydb.flyway") version "12.2.0"
    id("org.jlleitschuh.gradle.ktlint") version "14.1.0"
    id("co.uzzu.dotenv.gradle") version "4.0.0"
    id("com.github.ben-manes.versions") version "0.53.0"
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/staircase-ai/metrics-datadog")
        credentials {
            username = env.fetch("GPR_USERNAME", "")
            password = env.fetch("GPR_PASSWORD", "")
        }
    }
}

val kotlinVersion = "2.3.20"

dependencies {
    implementation(platform("io.dropwizard:dropwizard-bom:5.0.1"))
    implementation(platform("software.amazon.awssdk:bom:2.42.23"))
    implementation(platform("com.fasterxml.jackson:jackson-bom:2.21.2"))
    implementation("ch.qos.logback:logback-core:1.5.32")

    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-slf4j:1.10.2")

    implementation("io.dropwizard:dropwizard-core")

    implementation("io.dropwizard:dropwizard-db")

    implementation("io.dropwizard:dropwizard-auth")

    implementation("io.dropwizard:dropwizard-json-logging")

    implementation("io.dropwizard:dropwizard-client")

    implementation("io.dropwizard:dropwizard-forms")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-csv")

    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    implementation(platform("ru.vyarus:dropwizard-guicey:8.0.2"))

    implementation("ru.vyarus:dropwizard-guicey")

    implementation("ru.vyarus.guicey:guicey-eventbus:8.0.2")

    implementation("org.postgresql:postgresql:42.7.10")

    implementation("com.pgvector:pgvector:0.1.6")

    implementation("com.github.scribejava:scribejava-apis:8.3.3")

    implementation("com.google.api-client:google-api-client:2.9.0")

    implementation("com.google.auth:google-auth-library-oauth2-http:1.43.0")

    implementation("com.google.apis:google-api-services-gmail:v1-rev20260112-2.0.0")

    implementation("com.google.apis:google-api-services-calendar:v3-rev20260225-2.0.0")

    implementation("com.google.apis:google-api-services-admin-directory:directory_v1-rev20260227-2.0.0")

    implementation("org.jooq:jooq:3.21.1")

    implementation("org.jooq:jooq-postgres-extensions:3.21.1")

    implementation("io.jsonwebtoken:jjwt-api:0.13.0")

    implementation("io.jsonwebtoken:jjwt-impl:0.13.0")

    implementation("com.nimbusds:nimbus-jose-jwt:10.8")

    implementation("org.bouncycastle:bcpkix-jdk18on:1.83")

    implementation("commons-io:commons-io:2.21.0")

    implementation("org.apache.commons:commons-text:1.15.0")

    implementation("org.apache.commons:commons-csv:1.14.1")

    implementation("org.flywaydb:flyway-core:12.2.0")

    implementation("org.flywaydb:flyway-database-postgresql:12.2.0")

    implementation("software.amazon.awssdk:s3")

    implementation("software.amazon.awssdk:sts")

    implementation("software.amazon.awssdk:sns")

    implementation("software.amazon.awssdk:sqs")

    implementation("software.amazon.awssdk:kms")

    implementation("software.amazon.awssdk:lambda")

    implementation("org.apache.httpcomponents:httpclient:4.5.14")

    implementation("org.jsoup:jsoup:1.22.1")

    implementation("jakarta.mail:jakarta.mail-api:2.1.5")

    implementation("org.quartz-scheduler:quartz:2.5.2")
    runtimeOnly("com.mchange:c3p0:0.12.0")

    implementation("net.datafaker:datafaker:2.5.4")

    implementation("com.microsoft.graph:microsoft-graph:5.80.0")

    implementation("com.azure:azure-identity:1.18.2")

    implementation("com.azure:azure-ai-openai:1.0.0-beta.16")

    implementation("com.slack.api:slack-api-client:1.48.0")

    implementation("dev.failsafe:failsafe:3.3.2")

    implementation("org.apache.opennlp:opennlp-tools:2.5.7")

    implementation("commons-validator:commons-validator:1.10.1")

    implementation("com.opencsv:opencsv:5.12.0")

    implementation("org.staircase:metrics-datadog:3.1.2") {
        exclude("com.fasterxml.jackson.core", "jackson-databind")
        exclude("io.dropwizard.metrics", "metrics-core")
        exclude("org.apache.httpcomponents", "httpclient")
    }

    implementation("org.reflections:reflections:0.10.2")

    implementation("jakarta.ws.rs:jakarta.ws.rs-api:4.0.0")

    implementation("org.commonmark:commonmark:0.27.1")

    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.13.0")

    implementation("com.knuddels:jtokkit:1.1.0")

    implementation("com.github.seancfoley:ipaddress:5.6.2")

    implementation("com.google.re2j:re2j:1.8")

    implementation("org.staircase:msgraph-v6-shaded:6.55.3")

    // Use the Kotlin JUnit integration.
    testImplementation(platform("org.junit:junit-bom:6.0.2"))
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.assertj:assertj-core:3.27.7")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("io.mockk:mockk:1.14.9")
    testImplementation("io.dropwizard:dropwizard-testing")
    testImplementation("io.github.cdimascio:dotenv-kotlin:6.5.1")
    testImplementation("net.javacrumbs.json-unit:json-unit-assertj:5.1.0")
}

application {
    mainClass.set("com.staircase.AppKt")
}

tasks.withType<Test> {
    useJUnitPlatform()
    reports {
        junitXml.required.set(true)
    }
    testLogging {
        events("passed", "skipped", "failed")
        showExceptions = true
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
    environment = env.allVariables()
    maxHeapSize = "2g"
}

tasks.register<Test>("testIntegration") {
    useJUnitPlatform {
        includeTags("testIntegration")
    }
    group = "verification"
}

tasks.register<Test>("testUnit") {
    useJUnitPlatform {
        excludeTags("testIntegration")
    }
    group = "verification"
}

tasks.withType<KotlinJvmCompile>().configureEach {
    compilerOptions {
        compilerOptions.allWarningsAsErrors = true
        compilerOptions.jvmDefault = JvmDefaultMode.NO_COMPATIBILITY
        jvmTarget.set(JvmTarget.JVM_21)
        freeCompilerArgs = listOf("-Xannotation-default-target=param-property")
    }
}
tasks.withType<org.jlleitschuh.gradle.ktlint.tasks.BaseKtLintCheckTask> {
    workerMaxHeapSize.set("512m")
}

configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
    version.set("1.2.1")
    verbose.set(true)
    android.set(false)
    outputToConsole.set(true)
    outputColorName.set("RED")
    ignoreFailures.set(false)
}

flyway {
    url = "jdbc:postgresql://localhost:5432/staircase"
    user = "postgres"
    password = "postgres"
    outOfOrder = true
    cleanDisabled = false
    validateMigrationNaming = true
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

tasks.withType<DependencyUpdatesTask> {
    resolutionStrategy {
        componentSelection {
            all(
                Action {
                    if (isNonStable(candidate.version) && !isNonStable(currentVersion)) {
                        reject("Release candidate")
                    }
                }
            )
        }
    }

    checkForGradleUpdate = true
    outputFormatter = "html"
    outputDir = "build/dependencyUpdates"
    gradleReleaseChannel = "current"
    revision = "release"
}
