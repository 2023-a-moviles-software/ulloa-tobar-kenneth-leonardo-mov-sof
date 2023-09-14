package ec.edu.epn.swr.emp.chaucheritamovil

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.annotation.RequiresApi
import ec.edu.epn.swr.emp.chaucheritamovil.data.BaseDeDatos
import ec.edu.epn.swr.emp.chaucheritamovil.data.Cuenta
import ec.edu.epn.swr.emp.chaucheritamovil.utils.MultiHandler
import java.time.LocalDate
import java.time.format.DateTimeFormatter
@Suppress("NewApi")
class NuevoMovimiento : AppCompatActivity() {
    var callCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_movimiento)
        val fechaEdit = findViewById<EditText>(R.id.et_fecha)
        fechaEdit.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))

        val botonNuevoMovimiento = findViewById<Button>(R.id.btn_guardar)
        botonNuevoMovimiento.setOnClickListener {
            guardarMovimiento()
        }
        cargarCuentas()

    }

    fun cargarCuentas() {
        BaseDeDatos.consultarCuentas({
            cuentas->
            val cuentasOrigen = findViewById<Spinner>(R.id.sp_origen)
            val adapterCuentasOrigen = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                cuentas.filter { c -> c.tipo == Cuenta.Tipo.INGRESO_EGRESO.id_ || c.tipo == Cuenta.Tipo.INGRESO.id_ }
            )

            cuentasOrigen.adapter = adapterCuentasOrigen


            val cuentasDestino = findViewById<Spinner>(R.id.sp_destino)
            val adapterCuentasDestino = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                cuentas.filter { c -> c.tipo == Cuenta.Tipo.INGRESO_EGRESO.id_ || c.tipo == Cuenta.Tipo.EGRESO.id_ }
            )

            cuentasDestino.adapter = adapterCuentasDestino

            adapterCuentasOrigen.notifyDataSetChanged()
            adapterCuentasDestino.notifyDataSetChanged()


        })
    }

    fun guardarMovimiento() {
        val cuentasOrigen = findViewById<Spinner>(R.id.sp_origen)
        val cuentaOrigenValue = (cuentasOrigen.selectedItem!! as Cuenta)
        val cuentasDestino = findViewById<Spinner>(R.id.sp_destino)
        val cuentaDestinoValue = (cuentasDestino.selectedItem!! as Cuenta)
        val conceptoEditText = findViewById<EditText>(R.id.et_concepto)
        val montoEditText = findViewById<EditText>(R.id.et_monto)
        val fechaEdit = findViewById<EditText>(R.id.et_fecha)
        Log.i("Cuenta origen", cuentasOrigen.toString())
        BaseDeDatos.realizarMovimiento(
            cuentaOrigenValue,
            cuentaDestinoValue,
            conceptoEditText.text.toString(),
            montoEditText.text.toString().toDouble(),
            LocalDate.parse(
                fechaEdit.text.toString(), DateTimeFormatter.ofPattern("dd-MM-yyyy")),
            {
                callCount += 1
                if (callCount >= 3) {
                    callCount = 0
                    MultiHandler.mostrar(cuentasDestino, "Movimiento realizado exitosamente")
                }

            }
        )
    }
}