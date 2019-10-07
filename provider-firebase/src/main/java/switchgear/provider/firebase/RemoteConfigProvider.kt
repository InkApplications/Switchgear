package switchgear.provider.firebase

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import switchgear.ConfigProvider
import switchgear.MissingConfigException
import kotlin.reflect.KClass

/**
 * Bridges the Firebase Remote Parameter to as a Switchgear provider.
 */
class RemoteConfigProvider(
    private val remoteConfig: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
): ConfigProvider {
    override fun <T : Any> getConfig(key: String, type: KClass<T>): T = when(type) {
        Boolean::class -> remoteConfig.getBoolean(key)
        else -> throw MissingConfigException("Type ${type.java.simpleName} not supported by RemoteConfig")
    } as T
}
