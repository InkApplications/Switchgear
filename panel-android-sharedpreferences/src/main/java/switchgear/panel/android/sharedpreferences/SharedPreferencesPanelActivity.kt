package switchgear.panel.android.sharedpreferences

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import switchgear.Parameter
import switchgear.panel.android.PanelActivity
import switchgear.panel.android.startPanelActivity

/**
 * A list of overridable configuration parameters in SharedPreferences.
 *
 * This wraps the [SharedPreferencesPanelFragment] in an activity for ease of use.
 * Call [Context.startSharedPreferencePanel] to start this activity correctly.
 */
open class SharedPreferencesPanelActivity: PanelActivity() {
    override fun createPanel(parameters: Array<Parameter<Any>>): Fragment {
        return SharedPreferencesPanelFragment(parameters)
    }
}


/**
 * Start the Shared Preference Panel as an Activity.
 */
fun Context.startSharedPreferencePanel(parameters: Array<Parameter<Any>>, intentActions: Intent.() -> Unit = {}) {
    startPanelActivity(SharedPreferencesPanelActivity::class, parameters, intentActions)
}
