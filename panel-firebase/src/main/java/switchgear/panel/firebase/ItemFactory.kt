package switchgear.panel.firebase

import switchgear.ConfigProvider
import switchgear.Parameter

/**
 * Creates various groupie items for the different parameter types.
 */
internal class ItemFactory(
    private val provider: ConfigProvider
) {
    fun create(parameter: Parameter<Any>) = when (parameter) {
        is Parameter.Switch -> createSwitch(parameter)
    }

    private fun createSwitch(parameter: Parameter.Switch) = SwitchItem(parameter, provider.getBoolean(parameter.key))
}
