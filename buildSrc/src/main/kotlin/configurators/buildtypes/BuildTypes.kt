@file:Suppress("NOTHING_TO_INLINE")

package configurators.buildtypes

import com.android.build.gradle.internal.dsl.BuildType
import org.gradle.api.NamedDomainObjectContainer


inline fun NamedDomainObjectContainer<BuildType>.getOrCreateByName(
    typeName: String,
    noinline configuration: BuildType.() -> Unit
) {
    if (findByName(typeName) != null) {
        getByName(typeName) {
            configuration()
        }
    } else {
        create(typeName) {
            configuration()
        }
    }
}

inline fun NamedDomainObjectContainer<BuildType>.set(
    typeName: String,
    noinline configuration: BuildType.() -> Unit
) = getOrCreateByName(typeName, configuration)

inline fun NamedDomainObjectContainer<BuildType>.ifExists(
    typeName: String,
    crossinline configuration: BuildType.() -> Unit
) {
    findByName(typeName)?.apply(configuration)
}

inline fun NamedDomainObjectContainer<BuildType>.config(
    typeName: String,
    required: Boolean = true,
    crossinline configuration: @BuildTypesMarker BuildType.() -> Unit
) {
    if (required) {
        getOrCreateByName(typeName) {
            configuration()
        }
    } else {
        ifExists(typeName) {
            configuration()
        }
    }
}

@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
annotation class BuildTypesMarker


inline fun NamedDomainObjectContainer<BuildType>.debug(
    required: Boolean = true,
    crossinline configuration: @BuildTypesMarker BuildType.() -> Unit
) = config("debug", required) {
    isTestCoverageEnabled = true
    isMinifyEnabled = false
    configuration()
}

inline fun NamedDomainObjectContainer<BuildType>.release(
    required: Boolean = true,
    noinline configuration: @BuildTypesMarker BuildType.() -> Unit
) = config("release", required, configuration)

inline fun NamedDomainObjectContainer<BuildType>.dev(
    required: Boolean = true,
    noinline configuration: @BuildTypesMarker BuildType.() -> Unit
) = config("dev", required, configuration)

inline fun NamedDomainObjectContainer<BuildType>.homolog(
    required: Boolean = true,
    noinline configuration: @BuildTypesMarker BuildType.() -> Unit
) = config("homolog", required, configuration)