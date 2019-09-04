// https://git.kuschku.de/justJanne/QuasselDroid-ng/blob/d379a7495360e09799ab9a0c1bfee9b6e5f9c37f/buildSrc/src/main/kotlin/VersionContext.kt
data class VersionContext(val version: String)

inline fun withVersion(version: String, f: VersionContext.() -> Unit) {
    VersionContext(version).f()
}
