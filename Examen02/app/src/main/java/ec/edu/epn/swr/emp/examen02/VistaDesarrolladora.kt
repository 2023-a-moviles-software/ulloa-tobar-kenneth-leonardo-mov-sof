package ec.edu.epn.swr.emp.examen02

import android.app.AlertDialog
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
import ec.edu.epn.swr.emp.examen02.model.Desarrolladora
import ec.edu.epn.swr.emp.examen02.utils.CambiadorActividad
import ec.edu.epn.swr.emp.examen02.utils.Data

class VistaDesarrolladora : AppCompatActivity() {
    private val listaDesarrolladoras = ArrayList<Desarrolladora>()
    private var idSeleccionado: Int = -1
    private var modo: Int = Modo.CREACION
    val callback:(intent: Intent) -> Unit = {
            intent ->
                intent.putExtra("idSeleccionado", idSeleccionado)
                intent.putExtra("modo", modo)
    }
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
            CambiadorActividad.cambiarActividad(
                this,
                EdicionDesarrolladora::class.java,
                callback
            )
        }

        registerForContextMenu(listViewVistaDesarrolladora)
        cargarDesarrolladoras(adapter)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_items_desarrolladora, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        //idSeleccionado = adaptador.getItem(id)?.id!!
        //Log.i("Elemento", idSeleccionado.toString())
    }

    fun abrirDialogoEliminar() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar la desarrolladora?")
        builder.setPositiveButton("Si") { dialog, which ->

        }
        builder.setNegativeButton("No", null)

        val dialog = builder.create()
        dialog.show()
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item_editar -> {
                CambiadorActividad.cambiarActividad(
                    this,
                    EdicionDesarrolladora::class.java,
                    callback
                )
                true
            }

            R.id.menu_item_eliminar -> {
                abrirDialogoEliminar()
                true
            }

            R.id.menu_item_videojuegos -> {
                CambiadorActividad.cambiarActividad(
                    this,
                    VistaVideojuego::class.java,
                    callback
                )
                true
            }

            else -> {
                super.onContextItemSelected(item)
            }
        }
    }

    private fun cargarDesarrolladoras(
        adapter: ArrayAdapter<Desarrolladora>
    ) {
        listaDesarrolladoras.clear()
        listaDesarrolladoras.addAll(Data.getDesarrolladoras())
        adapter.notifyDataSetChanged()
    }
}