
object Versions {

    object Artifacts {
        //        const val groupId = "nz.co.trademe.konfigure"
        const val groupId = "io.vithor.fork.konfigure"
        const val version = "0.0.5"
    }

    object Libs {
        const val kotlin = "1.3.50"
        const val coroutines = "1.3.0"

        object Firebase {
            const val config = "18.0.0"
        }
    }

    object Plugins {
        const val detekt = "1.0.1"
        const val kotlin = Versions.Libs.kotlin
        const val androidgradleplugin = Tools.buildGradle
        const val jacoco = "0.8.2"
        const val jacocoJunit = "0.15.0"
        const val dexCount = "0.8.6"
    }

    object Tools {
        const val compileSdk = 29
        const val targetSdk = 29
        const val minSdk = 21
        const val buildGradle = "3.5.0"
    }
}