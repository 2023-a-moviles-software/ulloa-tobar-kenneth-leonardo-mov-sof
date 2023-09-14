package ec.edu.epn.swr.emp.chaucheritamovil.utils

import android.content.Context
import android.content.Intent
import android.view.View
import com.google.android.material.snackbar.Snackbar

class MultiHandler {
    companion object {
        fun cambiarActividad(
            context: Context,
            clase: Class<*>,
            callback: (intent: Intent) -> Unit = {}) {
            val intent = Intent(context, clase)
            callback(intent)
            context.startActivity(intent)
        }

        fun mostrar(view: View, texto: String) {
            Snackbar.make(
                view,
                texto,
                Snackbar.LENGTH_LONG
            ).setAction("Action", null).show()
        }
    }
}