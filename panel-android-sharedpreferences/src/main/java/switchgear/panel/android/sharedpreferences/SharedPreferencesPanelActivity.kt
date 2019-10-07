package switchgear.panel.android.sharedpreferences

import androidx.fragment.app.Fragment
import switchgear.panel.android.PanelActivity

/**
 * A list of overridable configuration parameters in SharedPreferences.
 *
 * This wraps the [SharedPreferencesPanelFragment] in an activity for ease of use.
 */
open class SharedPreferencesPanelActivity: PanelActivity() {
    override fun createPanel(): Fragment = SharedPreferencesPanelFragment()
}
