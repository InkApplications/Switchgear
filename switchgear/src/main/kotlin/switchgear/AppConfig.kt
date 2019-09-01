package switchgear

/**
 * Provides access to application configuration.
 */
interface AppConfig {
    /**
     * Retrieve a configuration value.
     */
    fun <T> getConfig(parameter: Parameter<T>): T
}
