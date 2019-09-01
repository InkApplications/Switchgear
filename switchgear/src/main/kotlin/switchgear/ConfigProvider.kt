package switchgear

/**
 * Defines a service for looking up configuration settings.
 *
 * If any configuration is unavailable or not provided by this service, it
 * should throw a `MissingConfigException`
 */
interface ConfigProvider {
    /**
     * Get a boolean configuration.
     *
     * @param key a unique identifier for this configuration value
     * @return The configured value for the specified key
     * @throws MissingConfigException if the value is not configured
     */
    fun getBoolean(key: String): Boolean
}
