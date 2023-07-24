package ec.edu.epn.swr.emp.uberreplica.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ec.edu.epn.swr.emp.uberreplica.R
import ec.edu.epn.swr.emp.uberreplica.model.PanelCarta

class PanelCartaAdapter(
    private val listaPlanificacion: ArrayList<PanelCarta>
): RecyclerView.Adapter<PanelCartaAdapter.PanelCartaViewHolder>()
{
    inner class PanelCartaViewHolder(val view: View): ViewHolder(view) {
        fun render(planificacion: PanelCarta, esUltimo: Boolean = false) {
            val titulo = view.findViewById<TextView>(R.id.titulo)
            val descripcion = view.findViewById<TextView>(R.id.descripcion)
            val imagen = view.findViewById<ImageView>(R.id.imagen)
            titulo.text = planificacion.titulo
            descripcion.text = planificacion.descripcion
            imagen.setImageResource(planificacion.imagen)
            if(esUltimo) {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PanelCartaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PanelCartaViewHolder(inflater.inflate(R.layout.planificacion, parent, false))
    }

    override fun getItemCount(): Int = listaPlanificacion.size

    override fun onBindViewHolder(holder: PanelCartaViewHolder, position: Int) {
        val planificacion = listaPlanificacion[position]
        holder.render(planificacion, position == listaPlanificacion.size -1)
    }


}