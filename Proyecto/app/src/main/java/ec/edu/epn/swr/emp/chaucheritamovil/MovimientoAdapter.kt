package ec.edu.epn.swr.emp.chaucheritamovil

import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ec.edu.epn.swr.emp.chaucheritamovil.data.Movimiento

class MovimientoAdapter(private val listaMov: ArrayList<Movimiento>)
    : RecyclerView.Adapter<MovimientoAdapter.MovimientoHolder>() {
        inner class MovimientoHolder(val view: View): ViewHolder(view) {
            fun render(movimiento: Movimiento) {
                val origen = view.findViewById<TextView>(R.id.tv_origen)
                val destino = view.findViewById<TextView>(R.id.tv_destino)
                val descripcion = view.findViewById<TextView>(R.id.tv_descripcion)
                val monto = view.findViewById<TextView>(R.id.tv_monto)
                val fecha = view.findViewById<TextView>(R.id.tv_fecha)
                origen.text = movimiento.origen!!.nombre
                destino.text = movimiento.destino!!.nombre
                descripcion.text = movimiento.concepto
                monto.text = movimiento.monto.toString()
                fecha.text = movimiento.fecha.toString()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovimientoHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovimientoHolder(inflater.inflate(R.layout.movimiento, parent, false))
    }

    override fun getItemCount(): Int  {
        Log.i("size", listaMov.size.toString())
        return listaMov.size
    }

    override fun onBindViewHolder(holder: MovimientoHolder, position: Int) {
        Log.i("pos", position.toString())
        val mov = listaMov[position]
        holder.render(mov)
    }
}