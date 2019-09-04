import com.android.build.gradle.TestedExtension

buildscript {
    repositories {
        google()
        jcenter()
        
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.Plugins.androidgradleplugin}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Plugins.kotlin}")
        classpath("com.jfrog.bintray.gradle:gradle-bintray-plugin:1.8.4")

    }
}

//apply(plugin = "com.jfrog.bintray")
group = Versions.Artifacts.groupId
version = Versions.Artifacts.version

//
//configurations {
//    this.create("implementation")
//}
//
//fun DependencyHandler.`implementation`(dependencyNotation: Any): Dependency? =
//    add("implementation", dependencyNotation)
//
//dependencies {
//    implementation("com.android.tools.build:gradle:${Versions.Plugins.androidgradleplugin}")
//}

allprojects {
    repositories {
        google()
        jcenter()
    }

    afterEvaluate {
        // Common library module configurations
        if (plugins.hasPlugin("com.android.library") || plugins.hasPlugin("com.android.application")) {

            configure<TestedExtension> {
                compileSdkVersion(Versions.Tools.compileSdk)

                defaultConfig {
                    minSdkVersion(Versions.Tools.minSdk)
                    targetSdkVersion(Versions.Tools.targetSdk)
                    vectorDrawables.useSupportLibrary = true
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_1_8
                    targetCompatibility = JavaVersion.VERSION_1_8
                }
            }
            
        }
    }
}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

