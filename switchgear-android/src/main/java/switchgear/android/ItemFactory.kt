package switchgear.android

import switchgear.MissingConfigException
import switchgear.Parameter
import switchgear.provider.sharedpreferences.SharedPreferencesConfigProvider

/**
 * Creates various groupie items for the different parameter types.
 */
internal class ItemFactory(
    private val preferenceProvider: SharedPreferencesConfigProvider,
    private val onUpdate: () -> Unit
) {
    fun create(parameter: Parameter<Any>) = when (parameter) {
        is Parameter.Switch -> createSwitch(parameter)
    }

    private fun createSwitch(parameter: Parameter.Switch) = try {
        SwitchItem(parameter, preferenceProvider.getBoolean(parameter.key), true, ::onOverride, ::onClear)
    } catch (e: MissingConfigException) {
        SwitchItem(parameter, parameter.default, false, ::onOverride, ::onClear)
    }

    private fun onOverride(key: String, value: Boolean) {
        preferenceProvider.setBoolean(key, value)
        onUpdate()
    }

    private fun onClear(key: String) {
        preferenceProvider.clear(key)
        onUpdate()
    }
}
