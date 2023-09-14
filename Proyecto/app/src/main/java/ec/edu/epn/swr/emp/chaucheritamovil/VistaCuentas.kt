package ec.edu.epn.swr.emp.chaucheritamovil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import ec.edu.epn.swr.emp.chaucheritamovil.data.BaseDeDatos
import ec.edu.epn.swr.emp.chaucheritamovil.data.Cuenta
import ec.edu.epn.swr.emp.chaucheritamovil.utils.MultiHandler

class VistaCuentas : AppCompatActivity() {
    val listaCuentas = ArrayList<Cuenta>()
    lateinit var adapter: ArrayAdapter<Cuenta>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_cuentas)
        val lisView = findViewById<ListView>(R.id.rv_mov)
        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaCuentas
        )
        lisView.adapter = adapter
        cargarCuentas()

        val btnCrearCuenta = findViewById<Button>(R.id.btn_crear_cuenta)
        btnCrearCuenta.setOnClickListener {
            MultiHandler.cambiarActividad(this, NuevaCuenta::class.java)
        }

    }

    fun cargarCuentas() {
        BaseDeDatos.consultarCuentas({
            cuentas ->
                listaCuentas.clear()
                listaCuentas.addAll(cuentas)
                adapter.notifyDataSetChanged()
        })
    }
}