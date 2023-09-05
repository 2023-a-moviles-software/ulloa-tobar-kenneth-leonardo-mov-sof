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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import ec.edu.epn.swr.emp.examenib.bussiness.BaseDatos
import ec.edu.epn.swr.emp.examenib.bussiness.Desarrolladora
import ec.edu.epn.swr.emp.examenib.utils.CambiadorActividad
import ec.edu.epn.swr.emp.examenib.utils.GeneradorSnackbar
import ec.edu.epn.swr.emp.examenib.utils.Modo

class VistaDesarrolladora : AppCompatActivity() {
    var desarrolladora: Desarrolladora? = null
    var modo: Modo = Modo.CREACION
    val activityChange = CambiadorActividad(this)
    lateinit var generadorSnackbar: GeneradorSnackbar
    var listaDesarrolladoras = ArrayList<Desarrolladora>()
    lateinit var adaptador: DesarrolladoraAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView = findViewById<RecyclerView>(R.id.lv_desarrolladoras)
        generadorSnackbar = GeneradorSnackbar(listView)

        activityChange.callback = {
            intent ->
            intent.putExtra("desarrolladora",desarrolladora)
            intent.putExtra("modo", modo.key)
        }

        val botonCrear = findViewById<Button>(R.id.btn_crear_desarrolladora)
        botonCrear.setOnClickListener {
            modo = Modo.CREACION
            activityChange.cambiarActividad(EdicionDesarrolladora::class.java)
        }

        adaptador = DesarrolladoraAdapter(listaDesarrolladoras, this)
        listView.layoutManager = LinearLayoutManager(this)
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        //registerForContextMenu(listView)

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_desarrolladora, menu)
        if (menuInfo != null) {
            val info = menuInfo as AdapterView.AdapterContextMenuInfo
            val id = info.position
            desarrolladora = adaptador.getItem(id)
        }

        Log.i("Menu", menuInfo.toString())
        //Log.i("Elemento", idSeleccionado.toString())
    }

    fun abrirDialogoEliminar() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar la desarrolladora?")
        builder.setPositiveButton("Si") { dialog, which ->
            desarrolladora?.let {
                if(it.id != null) {
                    val db = Firebase.firestore
                    db.collection("desarrolladoras")
                        .document(it.id)
                        .delete()
                        .addOnSuccessListener {
                            generadorSnackbar.mostrar("Desarrolladora borrada")
                            cargarAdapter()
                        }
                        .addOnFailureListener {
                            generadorSnackbar.mostrar("Ocurrió un problema al eliminar la desarrolladora")
                        }
                }

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
        cargarAdapter()
    }

    override fun onResume() {
        super.onResume()
        cargarAdapter()
    }

    private fun cargarAdapter() {

        BaseDatos.getDesarrolladoras {
            desarrolladoras ->
            listaDesarrolladoras.clear()
            listaDesarrolladoras.addAll(desarrolladoras)
            adaptador.notifyDataSetChanged()
        }
        //adaptador.notifyDataSetChanged()
    }

}