// Top-level import org.gradle.kotlin.dsl.implementation is not usually needed here

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt) // Applies the Hilt Android Gradle plugin for Hilt support
    alias(libs.plugins.kotlin.serialization) // Keep if you are using Kotlinx Serialization in this module
    id("com.google.devtools.ksp") // Enable KSP
    // Removed: id("kotlin-kapt") - Prefer KSP for Hilt
}

android {
    namespace = "com.mudit20.remote"
    compileSdk = 35 // Consider using a version catalog alias like libs.versions.compileSdk

    defaultConfig {
        minSdk = 24 // Consider using a version catalog alias like libs.versions.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false // Default for libraries is false, so this is explicit
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    // Consider removing appcompat and material if this is a pure data/remote module
    // implementation(libs.androidx.appcompat)
    // implementation(libs.material)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Hilt - Using KSP
    implementation(libs.hilt.android) // Hilt Android Runtime (e.g., com.google.dagger:hilt-android)
    ksp(libs.hilt.compiler)      // Hilt Compiler for KSP (e.g., com.google.dagger:hilt-compiler)
    // Ensure libs.hilt.compiler in your libs.versions.toml
    // correctly points to the Hilt KSP compiler artifact.

    // Networking libraries
    implementation("com.squareup.okhttp3:okhttp:4.12.0") // Or use version catalog: libs.okhttp
    implementation("com.squareup.retrofit2:retrofit:2.9.0") // Or use version catalog: libs.retrofit.core
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") // Or use version catalog: libs.retrofit.converter.gson
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0") // Or use version catalog: libs.okhttp.logging.interceptor
}