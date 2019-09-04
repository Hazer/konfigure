

plugins {
    id("com.android.library")
    kotlin("android")
//    id("digital.wup.android-maven-publish") version("3.6.2")
    maven
    `maven-publish`
    id("com.github.dcendents.android-maven") version "2.1"
    id("com.jfrog.bintray")
}

group = Versions.Artifacts.groupId
version = Versions.Artifacts.version
val archivesBaseName = "konfigure-firebase"


dependencies {
    api(project(":konfigure-core"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.Libs.kotlin}")
    implementation("com.google.firebase:firebase-config:${Versions.Libs.Firebase.config}")
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
//
//    publications.withType(MavenPublication::class.java).forEach { publication ->
//        with(publication.pom) {
//            withXml {
//                val root = asNode()
//                root.appendNode("name", archivesBaseName)
//                root.appendNode("description", archivesBaseName)
//                root.appendNode("url", "https://github.com/Hazer/konfigure")
//            }
//
//            licenses {
//                license {
//                    name.set("MIT License")
//                    distribution.set("repo")
//                }
//            }
//            developers {
//                developer {
//                    id.set("Hazer")
//                    name.set("Vithorio Polten")
//                    email.set("reach@vithor.io")
//                }
//            }
//            scm {
//                url.set("git@github.com:Hazer/konfigure.git")
//            }
//        }
//    }
//}
//
//task javadoc(type: Javadoc) {
//    source = android.sourceSets.main.java.srcDirs
//    classpath += project.files(android.getBootClasspath().join(File.pathSeparator))
//}

tasks {
    val sourcesJar by creating(Jar::class) {
        classifier = "sources"
        from(android.sourceSets["main"].java.srcDirs)
    }

//    val javadocJar by creating(Jar::class) {
//        dependsOn(JavaPlugin.JAVADOC_TASK_NAME)
//        classifier = "javadoc"
//        from(tasks["javadoc"])
//    }

    artifacts {
        add("archives", sourcesJar)
//        add("archives", javadocJar)
    }
}

//tasks.withType(Upload::class.java) {
tasks.named<Upload>("install" ) {
    repositories {
        withConvention(MavenRepositoryHandlerConvention::class) {
            mavenInstaller {
                pom {
                    project {
                        packaging = "aar"
                        groupId = project.group.toString()
                        artifactId = archivesBaseName
                        version = project.version.toString()
                    }
                }
            }
        }
    }
}

//val install = if (tasks.names.contains("install")) tasks.getByName("install") as Upload
//else tasks.create("install", Upload::class.java)
//with(install) {
//    DslObject(repositories).convention
//        .getPlugin<MavenRepositoryHandlerConvention>()
//        .mavenInstaller {
//            pom {
//                project {
//                    packaging = "aar"
//                    groupId = project.group.toString()
//                    artifactId = archivesBaseName
//                    version = project.version.toString()
//                }
//            }
//        }
//
//    tasks["bintrayUpload"].dependsOn(this)
//    tasks["artifactoryPublish"].dependsOn(this)
//}


bintray {
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

project.ext {
    set("mavSiteUrl", "https://github.com/Hazer/konfigure")
    set("mavGitUrl", "https://github.com/Hazer/konfigure.git")
    set("mavProjectName", archivesBaseName)
    set("mavLibraryDescription", archivesBaseName)
}

afterEvaluate {
//    apply(from = "https://raw.githubusercontent.com/sky-uk/gradle-maven-plugin/master/gradle-mavenizer.gradle")

}