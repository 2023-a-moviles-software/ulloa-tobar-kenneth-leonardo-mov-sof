package ec.edu.epn.swr.emp.uberreplica.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ec.edu.epn.swr.emp.uberreplica.R
import ec.edu.epn.swr.emp.uberreplica.model.Sugerencia

class SugerenciaAdapter(
    private val listaSugerencias: ArrayList<Sugerencia>
): RecyclerView.Adapter<SugerenciaAdapter.SugerenciaViewHolder>()
{
    inner class SugerenciaViewHolder(val view: View): ViewHolder(view) {
        fun render(sugerencia: Sugerencia) {
            val texto = view.findViewById<TextView>(R.id.tv_texto)
            texto.setText(sugerencia.nombre)
            val imagen = view.findViewById<ImageView>(R.id.iv_icono)
            imagen.setImageResource(sugerencia.imagen)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SugerenciaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SugerenciaViewHolder(inflater.inflate(R.layout.box_sugerencia, parent, false))
    }

    override fun getItemCount(): Int = listaSugerencias.size

    override fun onBindViewHolder(holder: SugerenciaViewHolder, position: Int) {
        val elemento = listaSugerencias[position]
        holder.render(elemento)
    }
}