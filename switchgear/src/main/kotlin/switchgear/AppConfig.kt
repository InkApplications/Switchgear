package switchgear

/**
 * Provides access to application configuration.
 *
 * You can use this using the array access operators rather than calling the
 * `get` methods directly.
 *
 * e.g.
 *
 *     SwitchGear[myConfig]
 *
 */
interface AppConfig {
    operator fun <T: Any> get(argument: Parameter.Optional<T>): T?
    operator fun <T: Any> get(argument: Parameter.Required<T>): T
}

/**
 * An App config that always returns the defaults.
 *
 * This is the default Config when no custom config is specified.
 */
object DefaultConfig: AppConfig {
    override fun <T : Any> get(argument: Parameter.Optional<T>): T? = argument.default
    override fun <T : Any> get(argument: Parameter.Required<T>): T = argument.default
}
