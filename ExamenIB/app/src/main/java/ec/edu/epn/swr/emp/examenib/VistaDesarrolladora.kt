package ec.edu.epn.swr.emp.examenib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import ec.edu.epn.swr.emp.examenib.bussiness.BaseDatos
import ec.edu.epn.swr.emp.examenib.bussiness.Desarrolladora
import ec.edu.epn.swr.emp.examenib.utils.CambiadorActividad
import ec.edu.epn.swr.emp.examenib.utils.GeneradorSnackbar
import ec.edu.epn.swr.emp.examenib.utils.Modo

class VistaDesarrolladora : AppCompatActivity() {
    lateinit var adaptador: ArrayAdapter<Desarrolladora>
    var idSeleccionado = 0
    var modo = Modo.CREACION
    val activityChange = CambiadorActividad(this)
    lateinit var generadorSnackbar: GeneradorSnackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        generadorSnackbar = GeneradorSnackbar(findViewById(R.id.lv_desarrolladoras))

        activityChange.callback = {
            intent ->
            intent.putExtra("idDesarrolladora",idSeleccionado)
            intent.putExtra("modo", modo)
        }

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
            activityChange.cambiarActividad(EdicionDesarrolladora::class.java)
        }

        /*
        listView.setOnItemClickListener { adapterView, view, i, l ->
            idSeleccionado = adaptador.getItem(i)?.id!!
            activityChange.cambiarActividad(VistaVideojuego::class.java)
        }*/
        registerForContextMenu(listView)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        var inflater = menuInflater
        inflater.inflate(R.menu.menu_desarrolladora, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idSeleccionado = adaptador.getItem(id)?.id!!
    }

    fun abrirDialogoEliminar() {
        var builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar?")
        builder.setPositiveButton("Si") { dialog, which ->
            if(BaseDatos.eliminar(idSeleccionado)){
                generadorSnackbar.mostrar("Elemento eliminado con éxito")
                adaptador.notifyDataSetChanged()
            }

        }
        builder.setNegativeButton("No", null)

        val dialog = builder.create()
        dialog.show()
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_item_editar -> {
                modo = Modo.ACTUALIZACION
                activityChange.cambiarActividad(EdicionDesarrolladora::class.java)
                true
            }

            R.id.menu_item_eliminar -> {
                abrirDialogoEliminar()
                true
            }

            R.id.menu_item_ver_elementos -> {
                activityChange.cambiarActividad(VistaVideojuego::class.java)
                true
            }

            else -> {
                super.onContextItemSelected(item)
            }
        }

    }

    override fun onRestart() {
        super.onRestart()
        adaptador.notifyDataSetChanged()
    }
}