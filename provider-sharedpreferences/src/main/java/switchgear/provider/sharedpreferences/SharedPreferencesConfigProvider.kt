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

    fun setBoolean(key: String, state: Boolean) = preferences.edit().putBoolean(key, state).apply()
}
