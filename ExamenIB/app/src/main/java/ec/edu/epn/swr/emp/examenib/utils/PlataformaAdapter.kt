package ec.edu.epn.swr.emp.examenib.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ec.edu.epn.swr.emp.examenib.R
import ec.edu.epn.swr.emp.examenib.bussiness.Genero
import ec.edu.epn.swr.emp.examenib.bussiness.Plataforma

class ContenedorPlataforma(
    val plataforma: Plataforma,
    var seleccionado: Boolean = false
) {
    companion object {
        fun crearLista(plataformas: ArrayList<Plataforma> = arrayListOf()): ArrayList<ContenedorPlataforma> {
            val lista = ArrayList<ContenedorPlataforma>()
            Plataforma.values().forEach {
                    plataforma ->
                val contenedor = ContenedorPlataforma(plataforma, plataformas.contains(plataforma))
                lista.add(contenedor)
            }
            return lista
        }
    }
}

class PlataformaViewHolder(view: View): ViewHolder(view) {
    val checkBox = view.findViewById<CheckBox>(R.id.contenido)
    fun render(item: ContenedorPlataforma) {
        checkBox.text = item.plataforma.title
        checkBox.isChecked = item.seleccionado
        checkBox.setOnClickListener {
            item.seleccionado = checkBox.isChecked
        }
    }
}

class PlataformaAdapter(val listaPlataformas: ArrayList<ContenedorPlataforma>):
    RecyclerView.Adapter<PlataformaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlataformaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PlataformaViewHolder(inflater.inflate(R.layout.item_list, parent, false))
    }

    override fun getItemCount(): Int = listaPlataformas.size

    override fun onBindViewHolder(holder: PlataformaViewHolder, position: Int) {
        val item = listaPlataformas[position]
        holder.render(item)
    }

    fun getSelected(): ArrayList<Plataforma> {
        val lista = listaPlataformas
            .filter{ it.seleccionado }
            .map { it.plataforma }
        return ArrayList(lista)
    }
}