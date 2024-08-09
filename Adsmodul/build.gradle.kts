plugins {
    id("com.android.library")
    id("maven-publish")
}

android {
    namespace = "com.adsmedia.adsmodul"
    compileSdk = 35
    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

//
    }

    ndkVersion = "26.2.11394342"

    buildTypes {
        release {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }



    }




    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {
    implementation(libs.play.services.ads)
    implementation(libs.facebook)
    implementation(libs.applovin)
    implementation(libs.unity)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.lifecycle.process)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.user.messaging.platform)
    implementation(libs.pangle)
    implementation(libs.masterads)
    implementation(libs.gson)
    implementation(kotlin("script-runtime"))
    testImplementation("org.mockito:mockito-core:5.11.0")
    testImplementation ("org.mockito:mockito-inline:5.2.0")
    testImplementation ("org.robolectric:robolectric:4.12.1")
    testImplementation ("androidx.test:runner:1.5.2")
    testImplementation ("androidx.test:core:1.5.0")
}

afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("release") {
                groupId = "com.github.codegith-hub"
                artifactId = "ModulAdsAdmob"
                version = "1.0.14"
                from(components["release"])
            }
        }
    }
}
