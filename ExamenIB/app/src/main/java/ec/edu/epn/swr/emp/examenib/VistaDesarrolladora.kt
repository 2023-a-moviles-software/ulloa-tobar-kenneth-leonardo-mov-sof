package ec.edu.epn.swr.emp.examenib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
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
            idSeleccionado = i
            cambiarActividad(VistaVideojuego::class.java)
        }
        registerForContextMenu(listView)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        var inflater = menuInflater
        inflater.inflate(R.menu.menu_items, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idSeleccionado = id

    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_item_editar -> {
                modo = Modo.ACTUALIZACION
                cambiarActividad(EdicionDesarrolladora::class.java)
                true
            }

            R.id.menu_item_eliminar -> {
                BaseDatos.eliminar(idSeleccionado + 1)
                adaptador.notifyDataSetChanged()
                true
            }

            else -> {
                super.onContextItemSelected(item)
            }
        }

    }

    fun cambiarActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        intent.putExtra("id_desarrolladora",idSeleccionado + 1)
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