import org.jetbrains.kotlin.gradle.targets.js.npm.includedRange

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.assessment.weatherapplication"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.assessment.weatherapplication"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "apiKey", "\"${project.findProperty("apiKey")}\"")

    }

    buildTypes {

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    packaging {
        resources {
            excludes += ("/META-INF/gradle/incremental.annotation.processors")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.datastore)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    kapt (libs.google.dagger.hilt.compiler)
    kapt (libs.androidx.hilt.lifecycle.viewmodel)


    implementation(libs.coil.compose)
    implementation(libs.android.tools.desugar)
    implementation(libs.androidx.navigation)
    implementation(libs.google.dagger.hilt)
    implementation(libs.dagger.hilt.core)
    implementation(libs.squareup.retrofit2)
    implementation(libs.squareup.converter.gson)

    testImplementation(libs.junit)
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.engine)

    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inline)

    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.jetbrains.kotlin)
    testImplementation(libs.arch.core.testing)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.hilt.navigation.compose)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(kotlin("reflect"))
}

tasks.register<JavaExec>("runApp") {
    mainClass.set("com.assessment.weatherapplication.di") // Replace with your main class
    classpath = sourceSets["main"].runtimeClasspath
    args = listOf("-DapiKey=${project.findProperty("apiKey")}")
}
tasks.register<JavaExec>("runDebug") {
    mainClass.set("com.assessment.weatherapplication.di") // Replace with your main class
    classpath = sourceSets["main"].runtimeClasspath
    args = listOf("-DapiKey=${project.findProperty("apiKey")}")
    jvmArgs = listOf("-Xdebug", "-Xrunjdwp:server=y,transport=dt_socket,suspend=n,address=5005")  // Enable debugging

}