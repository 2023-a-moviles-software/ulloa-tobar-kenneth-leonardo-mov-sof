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

class GeneroContenedor (
    val genero: Genero,
    var seleccionado: Boolean = false
        ) {

    companion object {
        fun crearLista(generos: ArrayList<Genero> = arrayListOf()): ArrayList<GeneroContenedor> {
            val lista = ArrayList<GeneroContenedor>()
            Genero.values().forEach {
                genero ->
                val contenedor = GeneroContenedor(genero, generos.contains(genero))
                lista.add(contenedor)
            }

            return lista
        }
    }
}

class GeneroViewHolder(view: View): ViewHolder(view) {
    lateinit var generoContenedor: GeneroContenedor
    val checkBox = view.findViewById<CheckBox>(R.id.contenido)
    fun render(item: GeneroContenedor) {
        this.generoContenedor = item
        checkBox.text = item.genero.title
        checkBox.isChecked = item.seleccionado
        checkBox.setOnClickListener {
            generoContenedor.seleccionado = checkBox.isChecked
        }
    }
}

class GeneroAdapter(private val listaGeneros: ArrayList<GeneroContenedor>): RecyclerView.Adapter<GeneroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneroViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return GeneroViewHolder(inflater.inflate(R.layout.item_list, parent, false))
    }
    override fun getItemCount(): Int = listaGeneros.size

    override fun onBindViewHolder(holder: GeneroViewHolder, position: Int) {
        val item = listaGeneros[position]
        holder.render(item)
    }

    fun getSelected(): ArrayList<Genero> {
        val lista = listaGeneros
            .filter{ it.seleccionado }
            .map { it.genero }
        return ArrayList(lista)
    }

}