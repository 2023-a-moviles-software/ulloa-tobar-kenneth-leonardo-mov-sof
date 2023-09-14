package ec.edu.epn.swr.emp.chaucheritamovil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import ec.edu.epn.swr.emp.chaucheritamovil.data.BaseDeDatos
import ec.edu.epn.swr.emp.chaucheritamovil.data.Movimiento
import ec.edu.epn.swr.emp.chaucheritamovil.utils.MultiHandler

class VistaMovimientos : AppCompatActivity() {
    val listaMov = ArrayList<Movimiento>()
    lateinit var adapter: ArrayAdapter<Movimiento>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_movimientos)

        val listView = findViewById<ListView>(R.id.rv_mov)
        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaMov
        )
        listView.adapter = adapter
        cargarMovimientos()

        val btnNuevoMovimiento = findViewById<Button>(R.id.btn_nuevo_mov)
        btnNuevoMovimiento.setOnClickListener {
            MultiHandler.cambiarActividad(this, NuevoMovimiento::class.java)
        }

    }

    fun cargarMovimientos() {
        BaseDeDatos.consultarMovimientos({
            movimientos, reload ->
            if (reload) {
                listaMov.clear()
                listaMov.addAll(movimientos)
            }
            adapter.notifyDataSetChanged()
        })
    }


}