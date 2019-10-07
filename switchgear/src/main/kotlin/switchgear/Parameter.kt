package switchgear

import kotlin.reflect.KClass

/**
 * A configurable property of the application.
 *
 * @property key A Unique Identifier for this config.
 * @property default The default value if no provider has configured it.
 * @property type Class reference used when looking up the value.
 */
sealed class Parameter<T: Any> {
    abstract val key: String
    abstract val type: KClass<T>
    abstract val default: T?

    /**
     * A Configuration that is allowed to return null as its default.
     */
    data class Optional<T: Any>(
        override val key: String,
        override val default: T?,
        override val type: KClass<T>
    ): Parameter<T>()

    /**
     * A Configuration that is required.
     */
    data class Required<T: Any>(
        override val key: String,
        override val default: T,
        override val type: KClass<T>
    ): Parameter<T>()
}

/**
 * Create a configuration parameter.
 *
 * @param key A Unique key to use when looking up the config in different services.
 * @param default The default configuration to use when the value is not overridden by any config provider.
 */
inline fun <reified T: Any> requiredParameter(key: String, default: T): Parameter.Required<T> {
    return Parameter.Required(key, default, T::class)
}

/**
 * Create configuration parameter with a nullable default.
 *
 * @param key A Unique key to use when looking up the config in different services.
 * @param default The default configuration to use when the value is not overridden by any config provider. (optional)
 */
inline fun <reified T: Any> optionalParameter(key: String, default: T? = null): Parameter.Optional<T> {
    return Parameter.Optional(key, default, T::class)
}
