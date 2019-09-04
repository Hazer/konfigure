plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "nz.co.trademe.konfigure.sample"
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

androidExtensions {
    isExperimental = true
}

repositories {
    maven(url="https://dl.bintray.com/hazer/maven/")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.Libs.kotlin}")

    implementation("androidx.appcompat:appcompat:1.0.2")
    implementation("com.google.android.material:material:1.0.0")
    
    implementation("androidx.core:core-ktx:1.0.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.2.0-alpha03")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")

//    implementation("io.vithor.fork.konfigure:konfigure-android:${Versions.Artifacts.version}")
    implementation(project(":konfigure-android"))
}
