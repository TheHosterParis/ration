// Import nécessaire pour JavaToolchainService
import org.gradle.jvm.toolchain.JavaLanguageVersion
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    //id("kotlin-kapt")
}

android {
    namespace = "com.hoster.ration"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.hoster.ration"
        minSdk = 29
        targetSdk = 34
        versionCode = 2
        versionName = "1.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true"
                )
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
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
val roomVersion = "2.6.1"
dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    // Room dependencies
    implementation("androidx.room:room-runtime:$roomVersion")
    //kapt("androidx.room:room-compiler:$roomVersion") // Utilisez kapt pour Room avec Kotlin
    implementation("androidx.room:room-ktx:$roomVersion")

    // Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    implementation("com.google.android.material:material:<version>")

    // KSP pour Room
    ksp("androidx.room:room-compiler:$roomVersion")
}

// Configuration de la toolchain JVM pour tout le projet
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11)) // Spécifiez la version de Java à utiliser
    }
}