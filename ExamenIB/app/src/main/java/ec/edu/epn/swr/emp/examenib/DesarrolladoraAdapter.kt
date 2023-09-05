package ec.edu.epn.swr.emp.examenib

import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import ec.edu.epn.swr.emp.examenib.bussiness.Desarrolladora
import ec.edu.epn.swr.emp.examenib.utils.GeneroViewHolder

class DesarrolladoraAdapter(
    private val listDesarrolladora: ArrayList<Desarrolladora>,
    val vista: VistaDesarrolladora
    ): Adapter<DesarrolladoraAdapter.DesarrolladoraViewHolder>() {
    inner class DesarrolladoraViewHolder(
        val view: View,
        val vistaDesarrolladora: VistaDesarrolladora,
        var position_: Int = 0
        ):
        RecyclerView.ViewHolder(view),
        View.OnCreateContextMenuListener
    {
        init {
            view.setOnCreateContextMenuListener(this)
        }

        fun render(desarrallora: Desarrolladora) {
            val nombreTextView = view.findViewById<TextView>(R.id.tv_nombre)
            val anioTextView = view.findViewById<TextView>(R.id.tv_anio)
            val paginaTextView = view.findViewById<TextView>(R.id.tv_pagina_web)
            val ubicacionTextView = view.findViewById<TextView>(R.id.tv_ubicacion)
            val independienteTextView = view.findViewById<TextView>(R.id.tv_es_independiente)

            nombreTextView.text = desarrallora.nombre!!
            anioTextView.text = desarrallora.anioCreacion.toString()
            paginaTextView.text = desarrallora.paginaWeb!!
            ubicacionTextView.text = desarrallora.ubicacion!!

            if (desarrallora.esIndependiente) {
                independienteTextView.text = "Independiente"
            } else {
                Log.i("Es","Dep")
                independienteTextView.text = "Dependiente"
            }

        }

        override fun onCreateContextMenu(
            p0: ContextMenu?,
            p1: View?,
            p2: ContextMenu.ContextMenuInfo?
        ) {
            vistaDesarrolladora.onCreateContextMenu(
                p0,
                p1,
                AdapterContextMenuInfo(p1, position_,0))
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DesarrolladoraViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DesarrolladoraViewHolder(inflater.inflate(R.layout.desarrolladora_item, parent, false), vista)
    }

    override fun getItemCount(): Int = listDesarrolladora.size

    override fun onBindViewHolder(holder: DesarrolladoraViewHolder, position: Int) {
        holder.position_ = position
        holder.render(listDesarrolladora[position])
    };

    fun getItem(position: Int): Desarrolladora {
        return listDesarrolladora[position]
    }
}