package ec.edu.epn.swr.emp.uberreplica.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ec.edu.epn.swr.emp.uberreplica.R
import ec.edu.epn.swr.emp.uberreplica.model.Viaje

class ListaViajesAdapter(
    private val listaViajes: ArrayList<Viaje>
): RecyclerView.Adapter<ViajeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViajeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViajeViewHolder(inflater.inflate(R.layout.viajes_recientes, parent, false))
    }

    override fun getItemCount(): Int = listaViajes.size

    override fun onBindViewHolder(holder: ViajeViewHolder, position: Int) {
        val viaje = listaViajes[position]
        holder.render(viaje, position == listaViajes.size - 1)
    }

}