package switchgear.panel.android.sharedpreferences

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import inkapplications.switchgear.panel.android.sharedpreferences.R
import switchgear.Parameter
import switchgear.provider.sharedpreferences.SharedPreferencesConfigProvider

private const val PARAMETERS = "extra.parameters"

/**
 * A list of overridable configuration parameters in SharedPreferences.
 *
 * This allows you to view and override all of the specified configuration
 * parameters using the SharedPreferences provider.
 */
open class SharedPreferencesPanelActivity: AppCompatActivity() {
    private val parameters get() = intent.getSerializableExtra(PARAMETERS) as Array<Parameter<Any>>

    private lateinit var controlPanel: RecyclerView
    private lateinit var preferenceProvider: SharedPreferencesConfigProvider
    private lateinit var itemFactory: ItemFactory
    private val adapter = GroupAdapter<ViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.control_panel)
        controlPanel = findViewById(R.id.control_panel)
        controlPanel.layoutManager = LinearLayoutManager(this)
        controlPanel.adapter = adapter
        preferenceProvider = SharedPreferencesConfigProvider(this)
        itemFactory = ItemFactory(preferenceProvider, ::refreshItems)
    }

    override fun onStart() {
        super.onStart()
        refreshItems()
    }

    fun refreshItems() {
        val items = parameters.map { itemFactory.create(it) }

        adapter.update(items)
    }
}

fun Context.startControlPanel(parameters: List<Parameter<Any>>) {
    Intent(this, SharedPreferencesPanelActivity::class.java)
        .apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            putExtra(PARAMETERS, parameters.toTypedArray())
        }
        .run(::startActivity)
}
