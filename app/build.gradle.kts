plugins {
    alias(libs.plugins.android.application)

}

android {
    namespace = "com.example.welcomeprojectapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.welcomeprojectapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    //val prod_url = "https://newapi.mcdonalds.co.il" // Production URL

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }



    flavorDimensions.add("app")

    productFlavors {
        create("prod") {
            applicationId = "com.example.welcomeprojectapp"
            buildConfigField("String", "URL", "\"https://newapi.mcdonalds.co.il\"") // prod_url
            buildConfigField("boolean", "SHOULD_REPORT_ANALYTICS", "true")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(project(":inmanage"))
    implementation(libs.lottie)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)




}