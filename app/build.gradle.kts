plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android") // 第 1 行
    // id("kotlin-android")        // 第 2 行（可选，如果使用 Kotlin，当前为 Java 项目可移除）
}

android {
    namespace = "com.android.musicapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.android.musicapp" // 匹配包名
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    testImplementation("junit:junit:4.13.2")
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("androidx.activity:activity-ktx:1.11.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.9.3")
    implementation("androidx.lifecycle:lifecycle-livedata:2.9.3")
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")
    implementation("com.squareup.okhttp3:okhttp:5.1.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.1.0")
    implementation("com.github.bumptech.glide:glide:5.0.5")
    annotationProcessor("com.github.bumptech.glide:compiler:5.0.5")
    implementation("com.google.android.material:material:1.13.0") // 包含 TabLayout
    implementation("androidx.viewpager2:viewpager2:1.1.0") // ViewPager2
    implementation("androidx.fragment:fragment:1.8.9") // Fragment 支持
    implementation("androidx.recyclerview:recyclerview:1.4.0")
    implementation("androidx.core:core:1.17.0")
    // LeakCanary for debug builds
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.14") // 检查最新版本
    debugImplementation("com.squareup.leakcanary:leakcanary-android-instrumentation:2.14")
}