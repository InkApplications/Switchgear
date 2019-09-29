package switchgear.panel.firebase

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import switchgear.Parameter
import switchgear.panel.android.PanelActivity
import switchgear.panel.android.startPanelActivity

/**
 * Panel to display the current status of Firebase Remote config's flags.
 *
 * This wraps the [FirebasePanelFragment] in an activity for ease of use.
 * Call [Context.startFirebasePanel] to start this activity correctly.
 */
class FirebasePanelActivity: PanelActivity() {
    override fun createPanel(parameters: Array<Parameter<Any>>): Fragment {
        return FirebasePanelFragment(parameters)
    }
}

/**
 * Start the Firebase Panel as an Activity.
 */
fun Context.startFirebasePanel(parameters: Array<Parameter<Any>>, intentActions: Intent.() -> Unit = {}) {
    startPanelActivity(FirebasePanelActivity::class, parameters, intentActions)
}
