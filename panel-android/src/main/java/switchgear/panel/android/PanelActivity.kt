package switchgear.panel.android

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import switchgear.Parameter
import kotlin.reflect.KClass

private const val PARAMETERS = "extra.parameters"

/**
 * Panel with parameter extras and a single displayed fragment.
 *
 * This is to make it easier to display panels as both fragments or activities.
 * Call [Context.startPanelActivity] to start this activity.
 */
abstract class PanelActivity: AppCompatActivity() {
    private val parameters get() = intent.getSerializableExtra(PARAMETERS) as Array<Parameter<Any>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, createPanel(parameters))
            .commit()
    }

    abstract fun createPanel(parameters: Array<Parameter<Any>>): Fragment
}

fun Context.startPanelActivity(
    activity: KClass<out PanelActivity>,
    parameters: Array<Parameter<Any>>,
    intentActions: Intent.() -> Unit
) {
    Intent(this, activity.java)
        .apply {
            putExtra(PARAMETERS, parameters)
            intentActions(this)
        }
        .run(::startActivity)
}
