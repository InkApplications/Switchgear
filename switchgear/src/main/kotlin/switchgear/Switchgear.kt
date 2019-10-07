package switchgear

import java.util.concurrent.atomic.AtomicReference

/**
 * A Singleton container for the AppConfig.
 */
object Switchgear: AppConfig {
    private val parameterList = AtomicReference<List<Parameter<out Any>>>(emptyList())
    private val config = AtomicReference<AppConfig>(DefaultConfig)

    /**
     * List of parameters configured.
     */
    val parameters: List<Parameter<out Any>> get() = parameterList.get()

    /**
     * Create a Prioritized list of Parameter Providers.
     */
    fun initialize(providers: List<ConfigProvider>, parameters: List<Parameter<out Any>>) {
        config.set(PrioritizedAppConfig(*providers.toTypedArray()))
        parameterList.set(parameters)
    }

    override fun <T : Any> get(argument: Parameter.Optional<T>): T? = config.get()[argument]
    override fun <T : Any> get(argument: Parameter.Required<T>): T = config.get()[argument]
}
