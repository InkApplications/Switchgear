package switchgear.panel.android.sharedpreferences

import android.view.View
import android.widget.CompoundButton
import android.widget.ImageButton
import android.widget.Switch
import android.widget.TextView
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import inkapplications.switchgear.panel.android.sharedpreferences.R
import switchgear.Parameter

/**
 * Item displayed for a switch on/off toggle.
 */
internal class SwitchItem(
    val parameter: Parameter.Switch,
    val currentValue: Boolean,
    val isOverridden: Boolean,
    val onOverride: (String, Boolean) -> Unit,
    val onClear: (String) -> Unit
): Item(parameter.key.hashCode().toLong()) {
    private val onClearListener = View.OnClickListener {
        onClear(parameter.key)
    }
    private val onOverrideListener = CompoundButton.OnCheckedChangeListener { _, value ->
        onOverride(parameter.key, value)
    }

    override fun getLayout(): Int = R.layout.item_switch

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.containerView.apply {
            findViewById<TextView>(R.id.item_switch_title).text = parameter.key
            findViewById<TextView>(R.id.item_switch_default).text = resources.getString(R.string.parameter_default, parameter.default.toString())

            findViewById<Switch>(R.id.item_switch_switch).isChecked = currentValue
            findViewById<Switch>(R.id.item_switch_switch).setOnCheckedChangeListener(onOverrideListener)
            findViewById<ImageButton>(R.id.item_switch_reset).visibility = if (isOverridden) View.VISIBLE else View.INVISIBLE
            findViewById<ImageButton>(R.id.item_switch_reset).setOnClickListener(onClearListener)
        }
    }

    override fun unbind(viewHolder: ViewHolder) {
        super.unbind(viewHolder)
        viewHolder.containerView.apply {
            findViewById<Switch>(R.id.item_switch_switch).setOnCheckedChangeListener(null)
            findViewById<ImageButton>(R.id.item_switch_reset).setOnClickListener(null)
        }
    }
}
