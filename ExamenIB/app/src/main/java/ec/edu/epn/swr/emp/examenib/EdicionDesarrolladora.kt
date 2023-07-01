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
import ec.edu.epn.swr.emp.examenib.bussiness.BaseDatos
import ec.edu.epn.swr.emp.examenib.bussiness.Desarrolladora
import ec.edu.epn.swr.emp.examenib.utils.CambiadorActividad

class EdicionDesarrolladora : AppCompatActivity() {
    var modo: Modo = Modo.CREACION
    var desarrolladora: Desarrolladora? = null
    val cambiadorActividad = CambiadorActividad(this)
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edicion_desarrolladora)

        val botonGuardar = findViewById<Button>(R.id.btn_guardar_desarrolladora)
        val textViewModo = findViewById<TextView>(R.id.te_modo_desarrolladora)

        modo = intent.getSerializableExtra("modo", Modo::class.java) as Modo
        textViewModo.text = modo.nombre

        if (modo == Modo.ACTUALIZACION) {
            val id = intent.getIntExtra("id_desarrolladora", -1)
            Log.i("Desarrollo","$id")
            desarrolladora = BaseDatos.buscarDesarrolladora(id)
            desarrolladora?.let {
                cargarDatosDesarrolladora(desarrolladora!!)
            }

        }

        botonGuardar.setOnClickListener {
            realizarAccionDesarrolladora()
            cambiadorActividad.cambiarActividad(VistaDesarrolladora::class.java)
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
                crearDesarrolladora(
                    nombre = nombre.text.toString(),
                    ubicacion = ubicacion.text.toString(),
                    paginaWeb = url.text.toString(),
                    anio = anio.text.toString().toInt(),
                    esIndependiente = independiente.isChecked,
                    id = BaseDatos.desarrolladoras.size + 1
                )
            }else if (modo == Modo.ACTUALIZACION) {
                var id: Int = if (desarrolladora?.id != null) desarrolladora?.id!! else -1

                actualizarDesarrolladora(
                    nombre = nombre.text.toString(),
                    ubicacion = ubicacion.text.toString(),
                    paginaWeb = url.text.toString(),
                    anio = anio.text.toString().toInt(),
                    esIndependiente = independiente.isChecked,
                    id = id
                )

            }
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

    fun crearDesarrolladora(
        nombre: String,
        ubicacion: String,
        anio: Int,
        paginaWeb: String,
        esIndependiente: Boolean,
        id: Int) {
        val desarrolladora = Desarrolladora(
            nombre = nombre,
            ubicacion = ubicacion,
            anioCreacion = anio,
            paginaWeb = paginaWeb,
            esIndependiente = esIndependiente,
            id = id
        )

        BaseDatos.desarrolladoras.add(
            desarrolladora
        )
    }

    fun actualizarDesarrolladora(
        nombre: String,
        ubicacion: String,
        anio: Int,
        paginaWeb: String,
        esIndependiente: Boolean,
        id: Int
    ) {
        val desarrolladora = BaseDatos.desarrolladoras.find { it.id == id }
        desarrolladora?.nombre = nombre
        desarrolladora?.ubicacion = ubicacion
        desarrolladora?.anioCreacion = anio
        desarrolladora?.paginaWeb = paginaWeb
        desarrolladora?.esIndependiente = esIndependiente

    }
}

