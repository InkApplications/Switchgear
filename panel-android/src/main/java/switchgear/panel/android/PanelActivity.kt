package switchgear.panel.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * Activity that just shows a single fragment intended for a panel.
 */
abstract class PanelActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, createPanel())
            .commit()
    }

    abstract fun createPanel(): Fragment
}
