package ec.edu.epn.swr.emp.examenib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import ec.edu.epn.swr.emp.examenib.bussiness.BaseDatos
import ec.edu.epn.swr.emp.examenib.bussiness.Desarrolladora
import ec.edu.epn.swr.emp.examenib.bussiness.Variables
import ec.edu.epn.swr.emp.examenib.bussiness.Videojuego
import ec.edu.epn.swr.emp.examenib.utils.CambiadorActividad
import ec.edu.epn.swr.emp.examenib.utils.GeneradorSnackbar
import ec.edu.epn.swr.emp.examenib.utils.Modo
@Suppress("all")
class VistaVideojuego : AppCompatActivity() {
    val cambiadorActividad: CambiadorActividad = CambiadorActividad(this)
    var modo: Modo = Modo.CREACION
    lateinit var adaptador: ArrayAdapter<Videojuego>
    var desarrolladora: Desarrolladora? = null
    var videojuego: Videojuego? = null
    val listaVideojuegos = ArrayList<Videojuego>()
    lateinit var generadorSnackbar: GeneradorSnackbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_videojuego)


        val desarrolladoraTextView = findViewById<TextView>(R.id.textView_desarrolladora)
        generadorSnackbar = GeneradorSnackbar(desarrolladoraTextView)
        desarrolladora = intent.getParcelableExtra("desarrolladora", Desarrolladora::class.java)

        cambiadorActividad.callback = {
                intent ->
            intent.putExtra("modo", modo.key)
            intent.putExtra("videojuego", videojuego)
            intent.putExtra("desarrolladora", desarrolladora)
        }

        if(desarrolladora != null) {
            desarrolladoraTextView.text = desarrolladora!!.nombre
        }

        val botonCrear = findViewById<Button>(R.id.btn_crear_videojuego)

        botonCrear.setOnClickListener {
            modo = Modo.CREACION
            cambiadorActividad.cambiarActividad(EdicionVideojuego::class.java)
        }


        val listView = findViewById<ListView>(R.id.lv_videojuegos)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaVideojuegos
        )

        listView.adapter = adaptador
        registerForContextMenu(listView)
        cargarAdapter()

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_videojuego, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        videojuego = adaptador.getItem(id)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_item_editar -> {
                modo = Modo.ACTUALIZACION
                cambiadorActividad.cambiarActividad(EdicionVideojuego::class.java)
                true
            }

            R.id.menu_item_eliminar -> {
                abrirDialogoEliminar()
                true
            }

            else -> {
                super.onContextItemSelected(item)
            }
        }
    }

    fun abrirDialogoEliminar() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar el videojuego?")
        builder.setPositiveButton("Si") { dialog, which ->
            BaseDatos.eliminarVideojuego(videojuego!!.id!!){
                when(it) {
                    Variables.EXITO -> {

                        cargarAdapter();
                        generadorSnackbar.mostrar("Videojuego eliminado exitosamente")
                    }
                    Variables.FALLO -> {
                        generadorSnackbar.mostrar("Error al eliminar el videojuego")
                    }
                }
            }
        }
        builder.setNegativeButton("No", null)

        val dialog = builder.create()
        dialog.show()
    }

    override fun onResume() {
        super.onResume()
        cargarAdapter()
    }

    private fun cargarAdapter() {
        BaseDatos.getVideojuegosPorDesarrolladora(desarrolladora!!) {
            videojuegos ->
            listaVideojuegos.clear()
            listaVideojuegos.addAll(videojuegos)
            adaptador.notifyDataSetChanged()
        }
    }

}