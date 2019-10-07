package switchgear.panel.android.sharedpreferences

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
    fun create(parameter: Parameter<out Any>) = when (parameter.type) {
        Boolean::class -> createSwitch(parameter as Parameter<out Boolean>)
        else -> null
    }

    private fun createSwitch(parameter: Parameter<out Boolean>) = try {
        SwitchItem(parameter, preferenceProvider.getConfig(parameter.key, parameter.type), true,  ::onOverride, ::onClear)
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
