package switchgear.panel.android.sharedpreferences

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import inkapplications.switchgear.panel.android.sharedpreferences.R
import switchgear.Switchgear
import switchgear.provider.sharedpreferences.SharedPreferencesConfigProvider

/**
 * A list of overridable configuration parameters in SharedPreferences.
 *
 * This allows you to view and override all of the specified configuration
 * parameters using the SharedPreferences provider.
 */
class SharedPreferencesPanelFragment: Fragment() {
    private val controlPanel: RecyclerView? get() = view?.findViewById(R.id.control_panel)
    private val preferenceProvider: SharedPreferencesConfigProvider by lazy { SharedPreferencesConfigProvider(context!!) }
    private val itemFactory: ItemFactory by lazy { ItemFactory(preferenceProvider, ::refreshItems) }
    private val adapter = GroupAdapter<ViewHolder>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.sharedpreference_panel, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        controlPanel!!.adapter = adapter
        refreshItems()
    }

    /**
     * Re-loads the list of configurations.
     *
     * This is automatically called when the activity is created, but may
     * be called again anytime thereafter.
     */
    fun refreshItems() {
        val items = Switchgear.parameters.mapNotNull { itemFactory.create(it) }

        adapter.update(items)
    }
}
