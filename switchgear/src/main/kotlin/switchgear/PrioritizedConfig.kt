package switchgear

/**
 * Loads a parameter from a list of providers before using the default.
 *
 * This will search through the providers in the order they are specified until
 * it finds one that can provide the requested configuration parameter. If no
 * provider gives a valid config, then this will use the parameter's default
 * value.
 *
 * @param providers an ordered list of configuration providers to use.
 */
class PrioritizedConfig(
    private vararg val providers: ConfigProvider
): AppConfig {
    override fun <T> getConfig(parameter: Parameter<T>): T  = when (parameter) {
        is Parameter.Switch -> findOrDefault(parameter, ConfigProvider::getBoolean)
    } as T

    private inline fun <T> findOrDefault(parameter: Parameter<T>, lookup: (ConfigProvider, String) -> T): T {
        return try {
            providers.tryAll { lookup(it, parameter.key) }
        } catch (missing: MissingConfigException) {
            parameter.default
        }
    }

    private inline fun <T> Array<out ConfigProvider>.tryAll(action: (ConfigProvider) -> T): T {
        for (config in this) {
            try {
                return action(config)
            } catch (missing: MissingConfigException) { /* Skip */ }
        }

        throw MissingConfigException("No results in $size configs returned a result")
    }
}
