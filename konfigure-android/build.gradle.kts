import com.jfrog.bintray.gradle.BintrayExtension

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    maven
    `maven-publish`
    id("com.github.dcendents.android-maven") version "2.1"
    id("com.jfrog.bintray")

}

group = Versions.Artifacts.groupId
version = Versions.Artifacts.version
val archivesBaseName = "konfigure-android"


androidExtensions {
    isExperimental = true
}

dependencies {
    api(project(":konfigure-core"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.Libs.kotlin}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Libs.coroutines}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Libs.coroutines}")

    implementation("androidx.appcompat:appcompat:1.0.2")
    implementation("com.google.android.material:material:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("androidx.lifecycle:lifecycle-extensions:2.0.0")
}


tasks {
    val sourcesJar by creating(Jar::class) {
        archiveClassifier.set("sources")
        from(android.sourceSets["main"].java.srcDirs)
    }

    artifacts {
        add("archives", sourcesJar)
    }
}

publishing {
    repositories {
        maven {
            mavenLocal()
            maven(url = "https://dl.bintray.com/hazer/maven/")
        }
    }
    (publications) {
        this.register<MavenPublication>("aarPublication") {
            //            from(components["java"])
            artifactId = archivesBaseName
            groupId = "${project.group}"
            version = "${project.version}"

            artifact("$buildDir/outputs/aar/${archivesBaseName}-release.aar")
//            artifact(bundleReleaseAar)

            pom.withXml {
                val dependenciesNode = asNode().appendNode("dependencies")
                configurations.getByName("archives").allDependencies.forEach {
                    if (it.group != null && (it.name != null || "unspecified".equals(it.name)) && it.version != null) {
                        val dependencyNode = dependenciesNode.appendNode("dependency")
                        dependencyNode.appendNode("groupId", it.group)
                        dependencyNode.appendNode("artifactId", it.name)
                        dependencyNode.appendNode("version", it.version)
                    }
                }

                project.configurations.getByName("compile").allDependencies.forEach {
                    if (it.group != null && (it.name != null || "unspecified".equals(it.name)) && it.version != null) {
                        val dependencyNode = dependenciesNode.appendNode("dependency")
                        dependencyNode.appendNode("groupId", it.group)
                        dependencyNode.appendNode("artifactId", it.name)
                        dependencyNode.appendNode("version", it.version)
                    }
                }
            }
        }
//        this.withType<MavenPublication> {
//            from(components["android"])
//            artifactId = archivesBaseName
//            groupId = "${project.group}"
//            version = "${project.version}"
//        }
    }
}

//tasks.named<Upload>("install" ) {
//    repositories {
//        withConvention(MavenRepositoryHandlerConvention::class) {
//            mavenInstaller {
//                pom {
//                    project {
//                        packaging = "aar"
//                        groupId = project.group.toString()
//                        artifactId = archivesBaseName
//                        version = project.version.toString()
//                    }
//                }
//            }
//        }
//    }
//}

configure<BintrayExtension> {
    user = System.getenv("BINTRAY_USER")
    key = System.getenv("BINTRAY_KEY")
    setConfigurations("archives")
    with(pkg) {
        repo = "maven"
        name = archivesBaseName
        websiteUrl = "https://github.com/Hazer/konfigure"
        vcsUrl = "https://github.com/Hazer/konfigure.git"
        setLicenses("MIT")
        publish = true
        override = true

        publicDownloadNumbers = true
    }
}