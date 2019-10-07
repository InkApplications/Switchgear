package switchgear

import kotlin.reflect.KClass

/**
 * Defines a service for looking up configuration settings.
 *
 * If any configuration is unavailable or not provided by this service, it
 * should throw a `MissingConfigException`
 */
interface ConfigProvider {
    /**
     * Attempt to load a configuration from the provider.
     *
     * @param key a unique identifier for this configuration value
     * @return The configured value for the specified key
     * @throws MissingConfigException if the value is not configured
     */
    fun <T: Any> getConfig(key: String, type: KClass<T>): T
}
