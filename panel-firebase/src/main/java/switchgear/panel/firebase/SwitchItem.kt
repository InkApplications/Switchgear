package switchgear.panel.firebase

import android.widget.TextView
import androidx.core.content.ContextCompat
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import inkapplications.switchgear.panel.firebase.R
import switchgear.Parameter

/**
 * Item displayed for a switch on/off toggle.
 */
internal class SwitchItem(
    val parameter: Parameter.Switch,
    val currentValue: Boolean
): Item(parameter.key.hashCode().toLong()) {
    override fun getLayout(): Int = R.layout.firebase_item

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.containerView.apply {
            findViewById<TextView>(R.id.firebase_item_title).text = parameter.key
            findViewById<TextView>(R.id.firebase_item_default).text = resources.getString(R.string.firebase_item_parameter_default, parameter.default.toString())
            findViewById<TextView>(R.id.firebase_item_value).text = currentValue.toString()

            val colorRes = if (currentValue) R.color.switchgear_positive else R.color.switchgear_negative
            findViewById<TextView>(R.id.firebase_item_value).setTextColor(ContextCompat.getColor(context, colorRes))
        }
    }

}
