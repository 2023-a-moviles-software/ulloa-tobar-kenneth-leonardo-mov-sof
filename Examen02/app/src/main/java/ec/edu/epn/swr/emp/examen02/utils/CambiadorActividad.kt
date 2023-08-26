package ec.edu.epn.swr.emp.examen02.utils

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

    companion object {
        fun cambiarActividad(
            contexto: Context,
            clase: Class<*>,
            _callback: (intent: Intent) -> Unit = {}) {
            val intent = Intent(contexto, clase)
            _callback(intent)
            contexto.startActivity(intent)
        }
    }

}