package switchgear.panel.android.sharedpreferences

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import inkapplications.switchgear.panel.android.sharedpreferences.R
import switchgear.Parameter
import switchgear.panel.android.PanelFragment
import switchgear.provider.sharedpreferences.SharedPreferencesConfigProvider

/**
 * A list of overridable configuration parameters in SharedPreferences.
 *
 * This allows you to view and override all of the specified configuration
 * parameters using the SharedPreferences provider.
 */
class SharedPreferencesPanelFragment: PanelFragment {
    private val controlPanel: RecyclerView? get() = view?.findViewById(R.id.control_panel)
    private val preferenceProvider: SharedPreferencesConfigProvider by lazy { SharedPreferencesConfigProvider(context!!) }
    private val itemFactory: ItemFactory by lazy { ItemFactory(preferenceProvider, ::refreshItems) }
    private val adapter = GroupAdapter<ViewHolder>()

    /** Android Constructor. Do not call directly. */
    constructor(): super()

    /**
     * Create a fragment with parameters to display.
     *
     * You should always create the Fragment with this constructor.
     *
     * @param parameters The configuration parameters to be displayed.
     */
    constructor(parameters: Array<Parameter<Any>>): super(parameters)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.sharedpreference_panel, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        controlPanel!!.adapter = adapter
        refreshItems()
    }

    fun refreshItems() {
        val items = parameters.map { itemFactory.create(it) }

        adapter.update(items)
    }
}
