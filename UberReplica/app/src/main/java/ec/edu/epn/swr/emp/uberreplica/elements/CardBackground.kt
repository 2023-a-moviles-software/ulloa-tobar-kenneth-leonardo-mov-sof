package ec.edu.epn.swr.emp.uberreplica.elements

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.view.View
import androidx.core.content.ContextCompat
import ec.edu.epn.swr.emp.uberreplica.R

@SuppressLint("NewApi")
class CardBackground(
    val background: Int,
    val foreground: Int
) {

    fun getDrawable(context: Context): Drawable {
        val drawable = ContextCompat.getDrawable(context, R.drawable.banner) as LayerDrawable
        drawable.findDrawableByLayerId(R.id.background).setTint(background)
        drawable.findDrawableByLayerId(R.id.foregroud).setTint(foreground)
        return drawable
    }

    companion object {
        fun setBackgroundColors(origin: Drawable, background: Int, foreground: Int) {
            //view.background
            val drawable = origin as LayerDrawable
            drawable.findDrawableByLayerId(R.id.background).setTint(background)
            drawable.findDrawableByLayerId(R.id.foregroud).setTint(foreground)
        }
    }

}