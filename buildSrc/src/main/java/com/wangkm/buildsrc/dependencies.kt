/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wangkm.buildsrc

object Versions {
    const val ktlint = "0.45.2"
}

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:7.2.1"
    const val jdkDesugar = "com.android.tools:desugar_jdk_libs:1.1.5"
    const val material3 = "com.google.android.material:material:1.6.0-alpha03"
    const val junit = "junit:junit:4.13"
    const val secretsGradlePlugin = "com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.1"

    const val coreLibraryDesugar = "com.android.tools:desugar_jdk_libs:1.1.5"

    object GoogleMaps {
        const val composeMaps = "com.google.maps.android:maps-compose:2.1.0"
        const val maps = "com.google.android.gms:play-services-maps:18.0.2"
    }

    object Robolectric {
        const val robolectric = "org.robolectric:robolectric:4.5.1"
    }

    object Volley {
        const val volley = "com.android.volley:volley:1.2.1"
    }


    object Accompanist {
        const val version = "0.24.12-rc"
        const val permissions = "com.google.accompanist:accompanist-permissions:$version"
        const val pager = "com.google.accompanist:accompanist-pager:$version"
        const val systemuicontroller = "com.google.accompanist:accompanist-systemuicontroller:$version"
        const val flowlayouts = "com.google.accompanist:accompanist-flowlayout:$version"
    }

    object Kotlin {
        private const val version = "1.6.21"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"

        object Coroutines {
            private const val version = "1.6.0"
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
            const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        }
    }

    object Coroutines {
        private const val version = "1.6.0"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object Hilt {
        private const val version = "2.42"

        const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
        const val android = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-compiler:$version"
        const val testing = "com.google.dagger:hilt-android-testing:$version"
        const val navigation = "androidx.hilt:hilt-navigation-compose:1.0.0"
    }

    object OkHttp {
        private const val version = "4.9.1"
        const val okhttp = "com.squareup.okhttp3:okhttp:$version"
        const val logging = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    object JUnit {
        private const val version = "4.13"
        const val junit = "junit:junit:$version"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.4.1"
        const val palette = "androidx.palette:palette:1.0.0"
        const val navigation = "androidx.navigation:navigation-compose:2.4.2"

        const val coreKtx = "androidx.core:core-ktx:1.7.0"

        object Activity {
            const val activityCompose = "androidx.activity:activity-compose:1.4.0"
        }

        object Constraint {
            const val constraintLayoutCompose = "androidx.constraintlayout:constraintlayout-compose:1.0.0"
        }

        object Compose {
            const val snapshot = ""
            const val version = "1.2.0-rc02"

            @get:JvmStatic
            val snapshotUrl: String
                get() = "https://androidx.dev/snapshots/builds/$snapshot/artifacts/repository/"

            const val runtime = "androidx.compose.runtime:runtime:$version"
            const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:$version"
            const val foundation = "androidx.compose.foundation:foundation:${version}"
            const val layout = "androidx.compose.foundation:foundation-layout:${version}"

            const val ui = "androidx.compose.ui:ui:${version}"
            const val uiUtil = "androidx.compose.ui:ui-util:${version}"
            const val uiText = "androidx.compose.ui:ui-text-google-fonts:${version}"
            const val materialWindow = "androidx.compose.material3:material3-window-size-class:1.0.0-alpha12"
            const val material = "androidx.compose.material:material:${version}"
            const val animation = "androidx.compose.animation:animation:${version}"
            const val materialIconsExtended = "androidx.compose.material:material-icons-extended:${version}"
            const val iconsExtended = "androidx.compose.material:material-icons-extended:$version"
            const val viewBinding = "androidx.compose.ui:ui-viewbinding:$version"
            const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:$version"

            object Material3 {
                const val snapshot = ""
                const val version = "1.0.0-alpha10"

                const val material3 = "androidx.compose.material3:material3:$version"
            }

//            object Material {
//                private const val version = "1.6.0-alpha03"
//                const val material = "com.google.android.material:material:$version"
//            }

            const val tooling = "androidx.compose.ui:ui-tooling:${version}"
            const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:${version}"
            const val uiTest = "androidx.compose.ui:ui-test-junit4:$version"
        }

        object Lifecycle {
            private const val version = "2.4.1"
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            const val extensions = "androidx.lifecycle:lifecycle-extensions:$version"
            const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
            const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
        }

        object Navigation {
            private const val version = "2.4.2"
            const val navigation = "androidx.navigation:navigation-compose:$version"
            const val navigationCompose = "androidx.navigation:navigation-compose:$version"
            const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
            const val uiKtx = "androidx.navigation:navigation-ui-ktx:$version"
        }


        object ConstraintLayout {
            const val constraintLayoutCompose =
                "androidx.constraintlayout:constraintlayout-compose:1.0.0"
        }

        object Test {
            private const val version = "1.4.0"
            const val core = "androidx.test:core:$version"
            const val rules = "androidx.test:rules:$version"

            object Ext {
                private const val version = "1.1.2"
                const val junit = "androidx.test.ext:junit-ktx:$version"
                const val truth = "androidx.test.ext:truth:1.4.0"
            }

            const val espressoCore = "androidx.test.espresso:espresso-core:3.2.0"
            const val runner = "androidx.test:runner:$version"
        }

        object Room {
            private const val version = "2.4.2"
            const val runtime = "androidx.room:room-runtime:${version}"
            const val ktx = "androidx.room:room-ktx:${version}"
            const val compiler = "androidx.room:room-compiler:${version}"
        }

        object Window {
            const val window = "androidx.window:window:1.0.0"
        }
    }

    object Rome {
        private const val version = "1.14.1"
        const val rome = "com.rometools:rome:$version"
        const val modules = "com.rometools:rome-modules:$version"
    }

    object Coil {
        const val coilCompose = "io.coil-kt:coil-compose:2.0.0"
    }
}

