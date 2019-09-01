package switchgear.provider.firebase

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import switchgear.ConfigProvider

/**
 * Bridges the Firebase Remote Config to as a Switchgear provider.
 */
class RemoteConfigProvider(
    private val remoteConfig: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
): ConfigProvider {
    override fun getBoolean(key: String): Boolean = remoteConfig.getBoolean(key)
}
