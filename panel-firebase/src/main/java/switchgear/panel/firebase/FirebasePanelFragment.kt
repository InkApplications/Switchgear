package switchgear.panel.firebase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import inkapplications.switchgear.panel.firebase.R
import switchgear.Switchgear
import switchgear.provider.firebase.RemoteConfigProvider

/**
 * Panel to display the current status of Firebase Remote config's flags.
 *
 * Pass parameters to load into the constructor of this class to display
 * properly.
 */
class FirebasePanelFragment: Fragment() {
    private val items: RecyclerView? get() = view?.findViewById(R.id.firebase_panel_items)
    private val adapter = GroupAdapter<ViewHolder>()
    private val preferenceProvider: RemoteConfigProvider by lazy { RemoteConfigProvider(FirebaseRemoteConfig.getInstance()) }
    private val itemFactory: ItemFactory by lazy { ItemFactory(preferenceProvider) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.firebase_panel, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        items!!.adapter = adapter
        Switchgear.parameters.mapNotNull(itemFactory::create).run(adapter::update)
    }
}
