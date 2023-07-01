package ec.edu.epn.swr.emp.examenib.utils;

import android.content.Context
import android.view.View
import com.google.android.material.snackbar.Snackbar
import ec.edu.epn.swr.emp.examenib.R

public class GeneradorSnackbar(var view: View? = null
) {
    fun mostrar(texto: String) {
            Snackbar.make(
                    view!!,
                    texto,
                    Snackbar.LENGTH_LONG
            ).setAction("Action", null).show()
    }
}
