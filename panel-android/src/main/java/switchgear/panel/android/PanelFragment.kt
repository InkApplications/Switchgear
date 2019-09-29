package switchgear.panel.android

import android.os.Bundle
import androidx.fragment.app.Fragment
import switchgear.Parameter

private const val PARAMETERS = "arguments.parameters"

/**
 * A Fragment constructed with parameters.
 */
abstract class PanelFragment(): Fragment() {
    protected val parameters get() = (arguments?.get(PARAMETERS) as? Array<Parameter<Any>>?) ?: arrayOf()

    constructor(parameters: Array<Parameter<Any>>): this() {
        arguments = Bundle().apply {
            putSerializable(PARAMETERS, parameters)
        }
    }
}
