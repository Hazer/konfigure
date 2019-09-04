@file:Suppress("NOTHING_TO_INLINE")

package configurators

import Versions
import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.api.ApplicationVariant
import every
import org.gradle.api.Project

/**
 * Access the `android` extension of this project. If the project is not an
 * Android app or library module, this method will throw.
 */
val Project.android: BaseExtension
    get() = extensions.findByName("android") as? BaseExtension
        ?: error("Project '$name' is not an Android module. Can't " +
                "access 'android' extension.")

/**
 * Accesses the app module-specific extensions of an Android module.
 */
val BaseExtension.app: AppExtension
    get() = this as? AppExtension
        ?: error("Android module is not an app module. Can't " +
                "access 'android' app extension.")

/**
 * Accesses the library module-specific extensions of an Android module.
 */
val BaseExtension.lib: LibraryExtension
    get() = this as? LibraryExtension
        ?: error("Android module is not an library module. Can't " +
                "access 'android' library extension.")

inline fun AppExtension.allAppVariants(crossinline eachBlock: (variant: ApplicationVariant) -> Unit) {
    applicationVariants.every(eachBlock)
}

//inline fun Project.libModule(crossinline config: LibraryExtension.() -> Unit = {}) {
//    configure<LibraryExtension> {
//        commonInternal(this@libModule)
//        config()
//    }
//}

//inline fun Project.appModule(crossinline config: BaseAppModuleExtension.() -> Unit = {}) {
//    configure<BaseAppModuleExtension> {
//        commonInternal(this@appModule)
//        config()
//    }
//}

//inline fun TestedExtension.common() {
//    this.commonInternal(HackyGlobalConfigurationHelper.project)
//}
