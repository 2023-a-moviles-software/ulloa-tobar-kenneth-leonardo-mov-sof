package com.example.movilessoftware2023a

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class FRecyclerViewAdaptadorNombreCedula(
    private val contexto: FRecyclerView,
    private val lista: ArrayList<BEntrenador>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<FRecyclerViewAdaptadorNombreCedula.MyViewHolder>()
{
    inner class MyViewHolder(view: View): ViewHolder(view) {
        val nombreText: TextView
        val cedulaTextView: TextView
        val likesTextView: TextView
        val botonDarLike: Button
        var numeroLikes = 0

        init {
            nombreText = view.findViewById(R.id.tv_nombre)
            cedulaTextView = view.findViewById(R.id.tv_cedula)
            likesTextView = view.findViewById(R.id.tv_likes)
            botonDarLike = view.findViewById(R.id.btn_dar_like)
            botonDarLike.setOnClickListener {
                agregarLike()
            }
        }

        fun agregarLike() {
            numeroLikes += 1
            likesTextView.text = numeroLikes.toString()
            contexto.aumentarTotalLikes()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.recycler_view_vista,
                parent,
                false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val entrenadorActual = lista[position]
        holder.nombreText.text = entrenadorActual.nombre
        holder.cedulaTextView.text = entrenadorActual.descripcion
        holder.botonDarLike.text = "Like ${entrenadorActual.id} - ${entrenadorActual.nombre}"
        holder.likesTextView.text = "0"
    }

    override fun getItemCount(): Int = lista.size
}