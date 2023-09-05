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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edicion_desarrolladora)

        val textViewModo = findViewById<TextView>(R.id.te_modo_desarrolladora)
        generadorSnackbar = GeneradorSnackbar(textViewModo)
        val botonGuardar = findViewById<Button>(R.id.btn_guardar_desarrolladora)


        modo = Modo.fromInt(intent.getIntExtra("modo", 0))
        textViewModo.text = modo.nombre

        if (modo == Modo.ACTUALIZACION) {
            desarrolladora = intent.getParcelableExtra("desarrolladora", Desarrolladora::class.java)

            desarrolladora?.let {
                cargarDatosDesarrolladora(it)
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

                crearDesarrolladora(Desarrolladora(
                    nombre = nombre.text.toString(),
                    ubicacion = ubicacion.text.toString(),
                    paginaWeb = url.text.toString(),
                    anio = anio.text.toString().toInt(),
                    esIndependiente = independiente.isChecked
                ))

            }else if (modo == Modo.ACTUALIZACION) {

                desarrolladora?.let {
                    desarrolladora ->
                    Log.i("EEE","Llegó")
                    if(desarrolladora.id != null) {
                        desarrolladora.nombre = nombre.text.toString()
                        desarrolladora.ubicacion = ubicacion.text.toString()
                        desarrolladora.paginaWeb = url.text.toString()
                        desarrolladora.anioCreacion = anio.text.toString().toInt()
                        desarrolladora.esIndependiente = independiente.isChecked
                        actualizarDesarrolladora(desarrolladora)
                    }
                }
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

    fun crearDesarrolladora(desarrolladora: Desarrolladora) {
        val desarrolladoraMap = hashMapOf(
            "nombre" to desarrolladora.nombre,
            "paginaWeb" to desarrolladora.paginaWeb,
            "anioCreacion" to desarrolladora.anioCreacion,
            "ubicacion" to desarrolladora.ubicacion,
            "esIndependiente" to desarrolladora.esIndependiente
        )
        val db = Firebase.firestore
        db.collection("desarrolladoras")
            .add(desarrolladoraMap)
            .addOnSuccessListener {
                finish()
            }.addOnFailureListener {
                generadorSnackbar.mostrar("Error al guardar la desarrolladora")
            }
    }

    fun actualizarDesarrolladora(desarrolladora: Desarrolladora) {
        val desarrolladoraMap = hashMapOf(
            "nombre" to desarrolladora.nombre,
            "paginaWeb" to desarrolladora.paginaWeb,
            "anioCreacion" to desarrolladora.anioCreacion,
            "ubicacion" to desarrolladora.ubicacion,
            "esIndependiente" to desarrolladora.esIndependiente
        )
        val db = Firebase.firestore
        db.collection("desarrolladoras")
            .document(desarrolladora.id!!)
            .set(desarrolladoraMap)
            .addOnSuccessListener {
                generadorSnackbar.mostrar("Desarrolladora guardada con éxito")
                finish()
            }.addOnFailureListener {
                generadorSnackbar.mostrar("Error al guardar la desarrolladora")
            }
    }
}

