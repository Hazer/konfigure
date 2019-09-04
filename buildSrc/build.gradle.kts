import org.gradle.kotlin.dsl.`kotlin-dsl`
import org.gradle.kotlin.dsl.`kotlin-dsl-precompiled-script-plugins`

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}
// Required since Gradle 4.10+.
repositories {
    jcenter()
    google()
}

dependencies {
    gradleApi()
    implementation("com.android.tools.build:gradle:3.5.0") //TODO: find solution to use Versions
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.50") //TODO: find solution to use Versions
}

configure<KotlinDslPluginOptions> {
    experimentalWarning.set(false)
}