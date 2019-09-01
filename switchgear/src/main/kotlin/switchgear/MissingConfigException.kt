package switchgear

/**
 * Thrown when a provider cannot find a value for a configuration parameter.
 */
class MissingConfigException(
    message: String? = null,
    cause: Throwable? = null
): IllegalArgumentException(message, cause)
