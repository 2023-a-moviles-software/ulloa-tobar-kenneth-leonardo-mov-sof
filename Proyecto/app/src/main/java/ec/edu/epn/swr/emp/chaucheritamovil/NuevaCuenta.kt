package ec.edu.epn.swr.emp.chaucheritamovil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import ec.edu.epn.swr.emp.chaucheritamovil.data.BaseDeDatos
import ec.edu.epn.swr.emp.chaucheritamovil.data.Cuenta
import ec.edu.epn.swr.emp.chaucheritamovil.utils.MultiHandler

class NuevaCuenta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_cuenta)
        val btnGuardar = findViewById<Button>(R.id.btn_guardar_cuenta)
        btnGuardar.setOnClickListener {
            crearCuenta()
        }

        val spinnerTipo = findViewById<Spinner>(R.id.sp_tipo)
        spinnerTipo.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            Cuenta.Tipo.values()
        )
        (spinnerTipo.adapter as ArrayAdapter<Cuenta.Tipo> ).notifyDataSetChanged()
    }

    fun crearCuenta() {
        val nombreTxt = findViewById<EditText>(R.id.te_nombre_cuenta)
        val spinnerTipo = findViewById<Spinner>(R.id.sp_tipo)
        val tipo = (spinnerTipo.selectedItem!! as Cuenta.Tipo).id_
        BaseDeDatos.crearCuenta(
            nombreTxt.text.toString(),
            tipo,
            {
                MultiHandler.mostrar(nombreTxt, "Cuenta creada exitósamente")
            }
        )
    }
}