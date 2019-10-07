package switchgear.panel.firebase

import androidx.fragment.app.Fragment
import switchgear.panel.android.PanelActivity

/**
 * Panel to display the current status of Firebase Remote config's flags.
 *
 * This wraps the [FirebasePanelFragment] in an activity for ease of use.
 */
class FirebasePanelActivity: PanelActivity() {
    override fun createPanel(): Fragment = FirebasePanelFragment()
}
