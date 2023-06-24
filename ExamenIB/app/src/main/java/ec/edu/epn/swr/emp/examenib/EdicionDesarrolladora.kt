package ec.edu.epn.swr.emp.examenib

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import ec.edu.epn.swr.emp.examenib.bussiness.BaseDatos
import ec.edu.epn.swr.emp.examenib.bussiness.Desarrolladora

class EdicionDesarrolladora : AppCompatActivity() {
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edicion_desarrolladora)

        val botonGuardar = findViewById<Button>(R.id.btn_guardar_desarrolladora)
        val textViewModo = findViewById<TextView>(R.id.te_modo_desarrolladora)

        textViewModo.text = (
                intent.getSerializableExtra("modo", Modo::class.java) as Modo
                ).nombre

        botonGuardar.setOnClickListener {
            guardarDesarrolladora()
            cambiarActividad(VistaDesarrolladora::class.java)
        }
    }

    fun cambiarActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun guardarDesarrolladora() {
        val nombre = findViewById<EditText>(R.id.te_nombre_desarrolladora)
        val ubicacion = findViewById<EditText>(R.id.te_ubicacion_desarrolladora)
        val anio = findViewById<EditText>(R.id.te_anio_desarrolladora)
        val url = findViewById<EditText>(R.id.te_url_desarrolladora)
        val independiente = findViewById<Switch>(R.id.switch_independiente_desarrolladora)

        if (
            nombre.text.isNotEmpty() &&
            ubicacion.text.isNotEmpty() &&
            anio.text.isNotEmpty() &&
            url.text.isNotEmpty()
        ) {
            val desarrolladora = Desarrolladora(
                nombre = nombre.text.toString(),
                ubicacion = ubicacion.text.toString(),
                anioCreacion = anio.text.toString().toInt(),
                paginaWeb = url.text.toString(),
                esIndependiente = independiente.isChecked,
                id = BaseDatos.desarrolladoras.size + 1
            )

            BaseDatos.desarrolladoras.add(
                desarrolladora
            )
        }
    }
}

