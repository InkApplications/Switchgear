package switchgear.panel.firebase

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import inkapplications.switchgear.panel.firebase.R
import switchgear.Parameter
import switchgear.provider.firebase.RemoteConfigProvider

private const val PARAMETERS = "extra.parameters"

class FirebasePanelActivity: AppCompatActivity() {
    private val parameters get() = intent.getSerializableExtra(PARAMETERS) as Array<Parameter<Any>>

    private lateinit var items: RecyclerView
    private val adapter = GroupAdapter<ViewHolder>()
    private lateinit var preferenceProvider: RemoteConfigProvider
    private lateinit var itemFactory: ItemFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.firebase_panel)
        items = findViewById(R.id.firebase_panel_items)
        items.layoutManager = LinearLayoutManager(this)
        items.adapter = adapter
        preferenceProvider = RemoteConfigProvider(FirebaseRemoteConfig.getInstance())
        itemFactory = ItemFactory(preferenceProvider)
    }

    override fun onStart() {
        super.onStart()

        val items = parameters.map { itemFactory.create(it) }.run(adapter::update)
    }
}

fun Context.startFirebasePanel(parameters: List<Parameter<Any>>) {
    Intent(this, FirebasePanelActivity::class.java)
        .apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            putExtra(PARAMETERS, parameters.toTypedArray())
        }
        .run(::startActivity)
}
