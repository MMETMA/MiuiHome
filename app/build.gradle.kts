import com.android.build.api.component.analytics.AnalyticsEnabledApplicationVariant
import com.android.build.api.variant.impl.ApplicationVariantImpl

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 31
    buildToolsVersion = "31.0.0"

    val verCode = 4136
    val verName = "4.1.3"

    defaultConfig {
        applicationId = "com.yuk.miuihome"
        minSdk = 28
        targetSdk = 31
        versionCode = verCode
        versionName = verName
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            setProguardFiles(
                listOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro",
                    "proguard-log.pro"
                )
            )
        }
    }

    androidComponents.onVariants { appVariant ->
        val variant: ApplicationVariantImpl =
            if (appVariant is ApplicationVariantImpl) appVariant
            else (appVariant as AnalyticsEnabledApplicationVariant).delegate as ApplicationVariantImpl
        variant.outputs.forEach {
            it.outputFileName.set("MiuiHome_${verName}(${verCode}).apk")
        }
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.0-beta02"
    }

    packagingOptions {
        resources {
            excludes += "META-INF/**"
            excludes += "kotlin/**"
            excludes += "**.bin"
        }
    }
    dependenciesInfo {
        includeInApk = false
    }
}

dependencies {
    val appCenterSdkVersion = "4.3.1"
    val composeVersion = "1.1.0-beta02"

    compileOnly("de.robv.android.xposed:api:82")
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("androidx.compose.material3:material3:1.0.0-alpha01")
    implementation("androidx.compose.ui:ui:${composeVersion}")
    implementation("androidx.compose.ui:ui-tooling:${composeVersion}")
    implementation("androidx.compose.animation:animation:${composeVersion}")
    implementation("com.microsoft.appcenter:appcenter-analytics:${appCenterSdkVersion}")
    implementation("com.microsoft.appcenter:appcenter-crashes:${appCenterSdkVersion}")
}