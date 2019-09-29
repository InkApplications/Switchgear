package switchgear.panel.firebase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import inkapplications.switchgear.panel.firebase.R
import switchgear.Parameter
import switchgear.panel.android.PanelFragment
import switchgear.provider.firebase.RemoteConfigProvider

/**
 * Panel to display the current status of Firebase Remote config's flags.
 *
 * Pass parameters to load into the constructor of this class to display
 * properly.
 */
class FirebasePanelFragment: PanelFragment {
    private val items: RecyclerView? get() = view?.findViewById(R.id.firebase_panel_items)
    private val adapter = GroupAdapter<ViewHolder>()
    private val preferenceProvider: RemoteConfigProvider by lazy { RemoteConfigProvider(FirebaseRemoteConfig.getInstance()) }
    private val itemFactory: ItemFactory by lazy { ItemFactory(preferenceProvider) }

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
        return inflater.inflate(R.layout.firebase_panel, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        items!!.adapter = adapter
        parameters.map(itemFactory::create).run(adapter::update)
    }
}
