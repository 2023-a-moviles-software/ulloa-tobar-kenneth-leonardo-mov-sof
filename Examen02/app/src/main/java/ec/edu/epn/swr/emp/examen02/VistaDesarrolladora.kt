package ec.edu.epn.swr.emp.examen02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import ec.edu.epn.swr.emp.examen02.model.Desarrolladora
import ec.edu.epn.swr.emp.examen02.utils.CambiadorActividad
import ec.edu.epn.swr.emp.examen02.utils.Data

class VistaDesarrolladora : AppCompatActivity() {
    private val listaDesarrolladoras = ArrayList<Desarrolladora>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_desarrolladora)

        val listViewVistaDesarrolladora = findViewById<ListView>(R.id.lv_desarrolladoras)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaDesarrolladoras
        )
        listViewVistaDesarrolladora.adapter = adapter


        val botonCrear = findViewById<Button>(R.id.btn_crear_desarrolladora)
        botonCrear.setOnClickListener {
            CambiadorActividad.cambiarActividad(this, EdicionDesarrolladora::class.java)
        }

        cargarDesarrolladoras(adapter)
    }

    private fun cargarDesarrolladoras(
        adapter: ArrayAdapter<Desarrolladora>
    ) {
        listaDesarrolladoras.clear()
        listaDesarrolladoras.addAll(Data.getDesarrolladoras())
        adapter.notifyDataSetChanged()
    }
}