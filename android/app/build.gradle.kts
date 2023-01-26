@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.gradle)
    alias(libs.plugins.ksp)
    alias(libs.plugins.react.gradle)
}

android {
    namespace = "com.tonkeeper.research"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.tonkeeper.research"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.tonkeeper.research.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        // Enable room auto-migrations
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
        aidl = false
        buildConfig = true
        renderScript = false
        shaders = false

    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Core Android dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Hilt Dependency Injection
    implementation(libs.hilt.android)

    // Arch Components
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)

    // Compose
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // React
    implementation(libs.react.android)
    implementation(libs.react.hermes)

    // ksp & kapt
    ksp(libs.androidx.room.compiler)
    kapt(libs.hilt.compiler)
    kaptTest(libs.hilt.android.compiler)
    kaptAndroidTest(libs.hilt.android.compiler)

    // Debug
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // Local tests: jUnit, coroutines, Android runner
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.hilt.android.testing)

    // Instrumented tests: jUnit rules and runners
    androidTestImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
}

apply(from = file("../../node_modules/@react-native-community/cli-platform-android/native_modules.gradle"))
val applyNativeModules: groovy.lang.Closure<Any> = extra.get("applyNativeModulesAppBuildGradle") as groovy.lang.Closure<Any>
applyNativeModules(project)



