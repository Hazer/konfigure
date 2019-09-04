@file:Suppress("NOTHING_TO_INLINE")

package configurators

import com.android.build.gradle.internal.dsl.BuildType
import com.android.build.gradle.internal.dsl.BaseFlavor
import escaped

enum class BuildConfigTypes(val java: kotlin.String) {
    String("String"),
    bool("boolean"),
    Bool("Boolean"),
    int("int"),
    Int("Integer"),
    long("long"),
    Long("Long"),
    char("char"),
    Char("Character")
}

inline fun BuildType.buildField(type: BuildConfigTypes, name: String, value: String) {
    val escapedValue = if (type == BuildConfigTypes.String) value.escaped() else value
    this.buildConfigField(type.java, name, escapedValue)
}

inline fun BuildType.buildUnescapedField(name: String, value: String) =
    this.buildConfigField(BuildConfigTypes.String.java, name, value)

inline fun BuildType.buildField(name: String, value: String) =
    buildField(BuildConfigTypes.String, name, value)

inline fun BuildType.buildField(name: String, value: Int) =
    buildField(BuildConfigTypes.int, name, value.toString())

inline fun BuildType.buildField(name: String, value: Int?) =
    buildField(BuildConfigTypes.Int, name, value.toString())

inline fun BuildType.buildField(name: String, value: Long) =
    buildField(BuildConfigTypes.long, name, value.toString())

inline fun BuildType.buildField(name: String, value: Long?) =
    buildField(BuildConfigTypes.Long, name, value.toString())

inline fun BuildType.buildField(name: String, value: Boolean) =
    buildField(BuildConfigTypes.bool, name, value.toString())

inline fun BuildType.buildField(name: String, value: Boolean?) =
    buildField(BuildConfigTypes.Bool, name, value.toString())

inline fun BuildType.buildField(name: String, value: Char) =
    buildField(BuildConfigTypes.char, name, value.toString())


inline fun BaseFlavor.buildField(type: BuildConfigTypes, name: String, value: String) {
    val escapedValue = if (type == BuildConfigTypes.String) value.escaped() else value
    this.buildConfigField(type.java, name, escapedValue)
}

inline fun BaseFlavor.buildUnescapedField(name: String, value: String) =
    this.buildConfigField(BuildConfigTypes.String.java, name, value)

inline fun BaseFlavor.buildField(name: String, value: String) =
    buildField(BuildConfigTypes.String, name, value)

inline fun BaseFlavor.buildField(name: String, value: Int) =
    buildField(BuildConfigTypes.int, name, value.toString())

inline fun BaseFlavor.buildField(name: String, value: Int?) =
    buildField(BuildConfigTypes.Int, name, value.toString())

inline fun BaseFlavor.buildField(name: String, value: Long) =
    buildField(BuildConfigTypes.long, name, value.toString())

inline fun BaseFlavor.buildField(name: String, value: Long?) =
    buildField(BuildConfigTypes.Long, name, value.toString())

inline fun BaseFlavor.buildField(name: String, value: Boolean) =
    buildField(BuildConfigTypes.bool, name, value.toString())

inline fun BaseFlavor.buildField(name: String, value: Boolean?) =
    buildField(BuildConfigTypes.Bool, name, value.toString())

inline fun BaseFlavor.buildField(name: String, value: Char) =
    buildField(BuildConfigTypes.char, name, value.toString())