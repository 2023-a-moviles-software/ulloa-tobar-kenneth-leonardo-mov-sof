package ec.edu.epn.swr.emp.examenib.utils

import android.content.Context
import android.content.Intent

class CambiadorActividad(
    val context: Context,
    var callback: (intent: Intent) -> Unit = { }
) {
    fun cambiarActividad(clase: Class<*>) {
        val intent = Intent(context, clase)
        callback(intent)
        context.startActivity(intent)
    }

}