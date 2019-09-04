import org.gradle.api.internal.plugins.DslObject

plugins {
    kotlin("jvm")
    maven
    `maven-publish`
//    id("com.github.dcendents.android-maven") version "2.1"
    id("com.jfrog.bintray")
}

group = Versions.Artifacts.groupId
version = Versions.Artifacts.version
val archivesBaseName = "konfigure-core"


dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.Libs.kotlin}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Libs.coroutines}")
}


//val sourcesJar by tasks.registering(Jar::class) {
//    classifier = "sources"
//    from(sourceSets.main.get().allSource)
//}

publishing {
    repositories {
        maven {
            mavenLocal()
            maven(url = "https://dl.bintray.com/hazer/maven/")
        }
    }
    (publications) {
        this.withType<MavenPublication> {
            from(components["java"])
            artifactId = archivesBaseName
            groupId = "${project.group}"
            version = "${project.version}"
        }
    }
}

tasks {
    val sourcesJar by creating(Jar::class) {
        archiveClassifier.set("sources")
        from(sourceSets["main"].allSource)
    }

    artifacts {
        add("archives", sourcesJar)
    }
}
tasks.named<Upload>("install" ) {
    repositories {
        withConvention(MavenRepositoryHandlerConvention::class) {
            mavenInstaller {
                pom {
                    project {
                        packaging = "jar"
                        groupId = project.group.toString()
                        artifactId = archivesBaseName
                        version = project.version.toString()
                    }
                }
            }
        }
    }
}

bintray {
    user = System.getenv("BINTRAY_USER")
    key = System.getenv("BINTRAY_KEY")
    setConfigurations("archives")
    pkg.apply {
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

