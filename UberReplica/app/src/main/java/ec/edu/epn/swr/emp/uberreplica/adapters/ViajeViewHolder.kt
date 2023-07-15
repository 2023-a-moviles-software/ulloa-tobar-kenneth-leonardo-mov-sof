package ec.edu.epn.swr.emp.uberreplica.adapters

import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ec.edu.epn.swr.emp.uberreplica.R
import ec.edu.epn.swr.emp.uberreplica.model.Viaje

class ViajeViewHolder
    (val view: View): ViewHolder(view) {
    fun render(viaje: Viaje, esUltimo: Boolean = false) {
        val nombre = view.findViewById<TextView>(R.id.tv_nombre)
        val direccion = view.findViewById<TextView>(R.id.tv_descripcion)
        nombre.setText(viaje.nombre)
        direccion.setText(viaje.direccion)
        if (esUltimo) {
            val separador = view.findViewById<FrameLayout>(R.id.bottom_bar)
            separador.isVisible = false
        }
    }

}