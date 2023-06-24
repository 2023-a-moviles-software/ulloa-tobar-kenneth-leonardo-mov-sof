package ec.edu.epn.swr.emp.examenib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import ec.edu.epn.swr.emp.examenib.bussiness.BaseDatos
import ec.edu.epn.swr.emp.examenib.bussiness.Desarrolladora

class VistaDesarrolladora : AppCompatActivity() {
    lateinit var adaptador: ArrayAdapter<Desarrolladora>
    var idSeleccionado = 0
    var modo = Modo.CREACION
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView = findViewById<ListView>(R.id.lv_desarrolladoras)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            BaseDatos.desarrolladoras
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonCrear = findViewById<Button>(R.id.btn_crear_desarrolladora)
        botonCrear.setOnClickListener {
            cambiarActividad(EdicionDesarrolladora::class.java)
        }

        listView.setOnItemClickListener { adapterView, view, i, l ->
            idSeleccionado = i + 1
            cambiarActividad(VistaVideojuego::class.java)
        }
    }

    fun cambiarActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        intent.putExtra("id_desarrolladora",idSeleccionado)
        intent.putExtra("modo", modo)
        startActivity(intent)
    }

    override fun onRestart() {
        super.onRestart()
        adaptador.notifyDataSetChanged()
    }
}

enum class Modo(val nombre: String) {
    CREACION("Creación"), ACTUALIZACION("Actualización")
}