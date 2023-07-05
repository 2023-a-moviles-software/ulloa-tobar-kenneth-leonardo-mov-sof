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
import ec.edu.epn.swr.emp.examenib.bussiness.Videojuego
import ec.edu.epn.swr.emp.examenib.utils.CambiadorActividad
import ec.edu.epn.swr.emp.examenib.utils.Modo

class VistaVideojuego : AppCompatActivity() {
    val cambiadorActividad: CambiadorActividad = CambiadorActividad(this)
    var modo: Modo = Modo.CREACION
    private var idVideojuego = -1
    private var idDesarrolladora = -1
    lateinit var adaptador: ArrayAdapter<Videojuego>
    lateinit var desarrolladora: Desarrolladora
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_videojuego)


        val desarrolladoraTextView = findViewById<TextView>(R.id.textView_desarrolladora)

        idDesarrolladora = intent.getIntExtra("idDesarrolladora", -1)

        cambiadorActividad.callback = {
                intent ->
            intent.putExtra("modo", modo)
            intent.putExtra("idVideojuego", idVideojuego)
            intent.putExtra("idDesarrolladora", idDesarrolladora)
        }

        val videojuegos = ArrayList<Videojuego>()
        if(idDesarrolladora != -1) {
            val desarrolladora = BaseDatos.buscarDesarrolladora(idDesarrolladora)
            if (desarrolladora != null) {
                this.desarrolladora = desarrolladora
                //videojuegos.addAll(desarrolladora.videojuegos)
                desarrolladoraTextView.text = desarrolladora.nombre
            }
        }

        val listView = findViewById<ListView>(R.id.lv_videojuegos)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            desarrolladora.videojuegos
        )

        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(listView)

        val botonCrear = findViewById<Button>(R.id.btn_crear_videojuego)

        botonCrear.setOnClickListener {
            modo = Modo.CREACION
            //cambiadorActividad.cambiarActividad(EdicionVideojuego::class.java)
        }
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
        idVideojuego = adaptador.getItem(id)?.id!!
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
        var builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar?")
        builder.setPositiveButton("Si") { dialog, which ->
            val desarrolladora = BaseDatos.buscarDesarrolladora(idDesarrolladora)
            if(desarrolladora != null){
                //generadorSnackbar.mostrar("Elemento eliminado con éxito")
                desarrolladora.videojuegos.removeIf { it.id == idVideojuego }
                adaptador.notifyDataSetChanged()
            }
        }
        builder.setNegativeButton("No", null)

        val dialog = builder.create()
        dialog.show()
    }

    override fun onResume() {
        super.onResume()
        adaptador.notifyDataSetChanged()
    }

}