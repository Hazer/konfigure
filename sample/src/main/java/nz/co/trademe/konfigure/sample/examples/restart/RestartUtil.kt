package nz.co.trademe.konfigure.sample.examples.restart

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

/**
 * Extension function for displaying a Snackbar with a restart app action.
 */
@JvmOverloads
fun AppCompatActivity.showRestartSnackbar(view: View = findViewById(android.R.id.content)): Snackbar =
    Snackbar.make(view,
        "You have pending config changes which require a restart",
        Snackbar.LENGTH_INDEFINITE)
        .setAction("Restart") {
            restartApp(this)
        }.also {
            it.show()
        }


/**
 * Function for restarting a given application and returning to the activity passed in as an argument
 */
private fun restartApp(activity: AppCompatActivity) {
    val packageManager = activity.applicationContext.packageManager
    val intent = packageManager.getLaunchIntentForPackage(activity.applicationContext.packageName)
    val componentName = intent!!.component
    val mainIntent = Intent.makeRestartActivityTask(componentName)
    activity.startActivity(mainIntent)
    Runtime.getRuntime().exit(0)
}