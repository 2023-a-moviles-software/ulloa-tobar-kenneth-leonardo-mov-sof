package ec.edu.epn.swr.emp.uberreplica.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ec.edu.epn.swr.emp.uberreplica.R
import ec.edu.epn.swr.emp.uberreplica.model.Viaje

class ViajeViewHolder
    (val view: View): ViewHolder(view) {
    fun render(viaje: Viaje) {
        val nombre = view.findViewById<TextView>(R.id.tv_nombre)
        val direccion = view.findViewById<TextView>(R.id.tv_descripcion)
        nombre.setText(viaje.nombre)
        direccion.setText(viaje.direccion)
    }

}