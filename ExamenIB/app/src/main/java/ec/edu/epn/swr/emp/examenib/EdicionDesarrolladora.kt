package ec.edu.epn.swr.emp.examenib

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import ec.edu.epn.swr.emp.examenib.bussiness.BaseDatos
import ec.edu.epn.swr.emp.examenib.bussiness.Desarrolladora
import ec.edu.epn.swr.emp.examenib.utils.CambiadorActividad
import ec.edu.epn.swr.emp.examenib.utils.GeneradorSnackbar
import ec.edu.epn.swr.emp.examenib.utils.Modo

class EdicionDesarrolladora : AppCompatActivity() {
    var modo: Modo = Modo.CREACION
    var desarrolladora: Desarrolladora? = null
    val cambiadorActividad = CambiadorActividad(this)
    lateinit var generadorSnackbar: GeneradorSnackbar
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edicion_desarrolladora)
        generadorSnackbar = GeneradorSnackbar(findViewById(R.id.te_modo_desarrolladora))
        val botonGuardar = findViewById<Button>(R.id.btn_guardar_desarrolladora)
        val textViewModo = findViewById<TextView>(R.id.te_modo_desarrolladora)

        modo = intent.getSerializableExtra("modo", Modo::class.java) as Modo
        textViewModo.text = modo.nombre

        if (modo == Modo.ACTUALIZACION) {
            val id = intent.getIntExtra("idDesarrolladora", -1)
            Log.i("Desarrollo","$id")
            desarrolladora = BaseDatos.buscarDesarrolladora(id)
            desarrolladora?.let {
                cargarDatosDesarrolladora(desarrolladora!!)
            }

        }

        botonGuardar.setOnClickListener {
            realizarAccionDesarrolladora()
            //cambiadorActividad.cambiarActividad(VistaDesarrolladora::class.java)
        }
    }

    fun realizarAccionDesarrolladora() {
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
            if(modo == Modo.CREACION){
                BaseDatos.crearDesarrolladora(
                    nombre = nombre.text.toString(),
                    ubicacion = ubicacion.text.toString(),
                    paginaWeb = url.text.toString(),
                    anio = anio.text.toString().toInt(),
                    esIndependiente = independiente.isChecked
                )

                //generadorSnackbar.mostrar("Desarrolladora creada exitosamente")
                finish()

            }else if (modo == Modo.ACTUALIZACION) {
                var id: Int = if (desarrolladora?.id != null) desarrolladora?.id!! else -1

                BaseDatos.actualizarDesarrolladora(
                    nombre = nombre.text.toString(),
                    ubicacion = ubicacion.text.toString(),
                    paginaWeb = url.text.toString(),
                    anio = anio.text.toString().toInt(),
                    esIndependiente = independiente.isChecked,
                    id = id
                )
                //generadorSnackbar.mostrar("Desarrolladora actualizada exitosamente")
                finish()
            }

        } else {
            generadorSnackbar.mostrar("No pueden existir campos vacíos")
        }
    }

    fun cargarDatosDesarrolladora(desarrolladora: Desarrolladora) {
        val nombre = findViewById<EditText>(R.id.te_nombre_desarrolladora)
        val ubicacion = findViewById<EditText>(R.id.te_ubicacion_desarrolladora)
        val anio = findViewById<EditText>(R.id.te_anio_desarrolladora)
        val url = findViewById<EditText>(R.id.te_url_desarrolladora)
        val independiente = findViewById<Switch>(R.id.switch_independiente_desarrolladora)
        nombre.setText(desarrolladora.nombre)
        ubicacion.setText(desarrolladora.ubicacion)
        anio.setText(desarrolladora.anioCreacion.toString())
        url.setText(desarrolladora.paginaWeb)
        independiente.isChecked = desarrolladora.esIndependiente
    }

}

