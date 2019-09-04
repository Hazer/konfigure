import org.gradle.api.Action
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.accessors.runtime.addDependencyTo

fun DependencyHandler.`api`(dependencyNotation: Any): Dependency? =
    add("api", dependencyNotation)

fun DependencyHandler.`implementation`(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

fun DependencyHandler.`implementation`(
    dependencyNotation: String,
    dependencyConfiguration: Action<ExternalModuleDependency>
): ExternalModuleDependency = addDependencyTo(
    this, "implementation", dependencyNotation, dependencyConfiguration
)

fun DependencyHandler.`api`(
    dependencyNotation: String,
    dependencyConfiguration: Action<ExternalModuleDependency>
): ExternalModuleDependency = addDependencyTo(
    this, "api", dependencyNotation, dependencyConfiguration
)

inline fun DependencyHandler.api(
    notation: String,
    config: ExternalModuleDependency.() -> Unit
): ExternalModuleDependency? = api(notation, config.toAction())

inline fun DependencyHandler.implementation(
    notation: String,
    config: ExternalModuleDependency.() -> Unit
): ExternalModuleDependency? = implementation(notation, config.toAction())

fun DependencyHandler.`kapt`(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)

fun DependencyHandler.`testImplementation`(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

fun DependencyHandler.`androidTestImplementation`(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)

fun DependencyHandler.`testRuntimeOnly`(dependencyNotation: Any): Dependency? =
    add("testRuntimeOnly", dependencyNotation)

fun DependencyHandler.`androidTestRuntimeOnly`(dependencyNotation: Any): Dependency? =
    add("androidTestRuntimeOnly", dependencyNotation)

fun DependencyHandler.`compileOnly`(dependencyNotation: Any): Dependency? =
    add("compileOnly", dependencyNotation)

fun DependencyHandler.project(
    path: String,
    configuration: String? = null
): ProjectDependency = project(
    if (configuration != null) mapOf("path" to path, "configuration" to configuration)
    else mapOf("path" to path)
) as ProjectDependency