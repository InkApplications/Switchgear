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
class PrioritizedAppConfig(
    private vararg val providers: ConfigProvider
): AppConfig {
    constructor(providers: List<ConfigProvider>): this(*providers.toTypedArray())

    override fun <T : Any> get(argument: Parameter.Optional<T>): T? {
        return providers.tryAll {
            try {
                it.getConfig(argument.key, argument.type)
            } catch (missing: MissingConfigException) {
                argument.default
            }
        }
    }

    override fun <T : Any> get(argument: Parameter.Required<T>): T {
        return providers.tryAll {
            try {
                it.getConfig(argument.key, argument.type)
            } catch (missing: MissingConfigException) {
                argument.default
            }
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
