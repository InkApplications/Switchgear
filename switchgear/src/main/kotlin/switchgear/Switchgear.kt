package switchgear

import kotlinx.atomicfu.*

/**
 * A Singleton container for the AppConfig.
 */
object Switchgear: AppConfig {
    private val parameterList = atomic<List<Parameter<out Any>>>(emptyList())
    private val config = atomic<AppConfig>(DefaultConfig)

    /**
     * List of parameters configured.
     */
    val parameters: List<Parameter<out Any>> get() = parameterList.value

    /**
     * Create a Prioritized list of Parameter Providers.
     */
    fun initialize(providers: List<ConfigProvider>, parameters: List<Parameter<out Any>>) {
        config.value = PrioritizedAppConfig(providers)
        parameterList.value = parameters
    }

    override fun <T : Any> get(argument: Parameter.Optional<T>): T? = config.value[argument]
    override fun <T : Any> get(argument: Parameter.Required<T>): T = config.value[argument]
}
