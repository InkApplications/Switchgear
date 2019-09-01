package switchgear.provider.sharedpreferences

import android.content.Context
import inkapplications.switchgear.provider.sharedpreferences.BuildConfig
import switchgear.ConfigProvider
import switchgear.MissingConfigException

/**
 * Fetches settings from SharedPreferences.
 *
 * This checks for the preferences saved in a private preferences package.
 * If a preference is missing, this will not use a default.
 */
class SharedPreferencesConfigProvider(context: Context): ConfigProvider {
    private val preferences = context.getSharedPreferences(BuildConfig.LIBRARY_PACKAGE_NAME, Context.MODE_PRIVATE)

    override fun getBoolean(key: String): Boolean {
        if (!preferences.contains(key)) throw MissingConfigException("Preference `$key` not saved in SharedPreferences")

        return preferences.getBoolean(key, false)
    }

    /**
     * Override the value using shared preferences.
     *
     * Note: Setting this back to the default is not the same as clearing it.
     * To remove the override, call `clear(key)` otherwise this will still
     * override other potential providers.
     *
     * @param key the parameter ID to override
     * @param state the value to override the parameter with
     */
    fun setBoolean(key: String, state: Boolean) = preferences.edit().putBoolean(key, state).apply()

    /**
     * Resets an overridden config parameter.
     *
     * After a clear is done, this provider will no longer effect the state of
     * the parameter.
     *
     * @param key the parameter ID to reset
     */
    fun clear(key: String) = preferences.edit().remove(key).apply()
}
