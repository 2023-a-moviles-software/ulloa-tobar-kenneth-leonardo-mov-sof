package ec.edu.epn.swr.emp.examenib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import ec.edu.epn.swr.emp.examenib.bussiness.BaseDatos
import ec.edu.epn.swr.emp.examenib.bussiness.Videojuego

class VistaVideojuego : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_videojuego)

        val desarrolladoraTextView = findViewById<TextView>(R.id.textView_desarrolladora)

        val idSeleccionado = intent.getIntExtra("id_desarrolladora", -1)

        val videojuegos = ArrayList<Videojuego>()
        if(idSeleccionado != -1) {
            val desarrolladora = BaseDatos.desarrolladoras.find{it.id == idSeleccionado}
            if (desarrolladora != null) {
                videojuegos.addAll(desarrolladora.videojuegos)
                desarrolladoraTextView.text = desarrolladora.nombre
            }
        }

        val listView = findViewById<ListView>(R.id.lv_videojuegos)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            videojuegos
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }
}