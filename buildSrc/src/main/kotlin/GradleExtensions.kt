@file:Suppress("NOTHING_TO_INLINE")

import org.gradle.api.Action
import org.gradle.api.DomainObjectCollection

@Suppress("ObjectLiteralToLambda")
inline fun <reified T> DomainObjectCollection<T>.every(crossinline onEach: (T) -> Unit) {
    this.all(object : Action<T> {
        override fun execute(t: T) {
            onEach(t)
        }
    })
}

inline fun String.escaped() = """"$this"""" // Yeah, weird but works

@Suppress("ObjectLiteralToLambda")
inline fun <reified T> ((T) -> Unit).toAction(): Action<T> {
    return object : Action<T> {
        override fun execute(t: T) {
            this@toAction(t)
        }
    }
}