package switchgear.panel.firebase

import switchgear.Parameter
import switchgear.provider.firebase.RemoteConfigProvider

/**
 * Creates various groupie items for the different parameter types.
 */
internal class ItemFactory(
    private val provider: RemoteConfigProvider
) {
    fun create(parameter: Parameter<out Any>) = when (parameter.type) {
        Boolean::class -> createSwitch(parameter as Parameter<out Boolean>)
        else -> null
    }

    private fun createSwitch(parameter: Parameter<out Boolean>) = SwitchItem(parameter, provider.getConfig(parameter.key, parameter.type))
}
