package switchgear

/**
 * A configurable property of the application.
 *
 * @property key A Unique Identifier for this config.
 * @property default The default value if no provider has configured it.
 */
sealed class Parameter<out T> {
    abstract val key: String
    abstract val default: T

    /**
     * A boolean on/off configuration.
     */
    data class Switch(
        override val key: String,
        override val default: Boolean
    ): Parameter<Boolean>()
}
