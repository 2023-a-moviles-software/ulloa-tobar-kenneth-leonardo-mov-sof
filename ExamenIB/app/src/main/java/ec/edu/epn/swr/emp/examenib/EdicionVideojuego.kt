package ec.edu.epn.swr.emp.examenib

import android.annotation.SuppressLint
import android.graphics.MeshSpecification.Varying
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ec.edu.epn.swr.emp.examenib.bussiness.BaseDatos
import ec.edu.epn.swr.emp.examenib.bussiness.Desarrolladora
import ec.edu.epn.swr.emp.examenib.bussiness.ManejoFechas
import ec.edu.epn.swr.emp.examenib.bussiness.Variables
import ec.edu.epn.swr.emp.examenib.bussiness.Videojuego
import ec.edu.epn.swr.emp.examenib.utils.CambiadorActividad
import ec.edu.epn.swr.emp.examenib.utils.ContenedorPlataforma
import ec.edu.epn.swr.emp.examenib.utils.GeneradorSnackbar
import ec.edu.epn.swr.emp.examenib.utils.GeneroAdapter
import ec.edu.epn.swr.emp.examenib.utils.GeneroContenedor
import ec.edu.epn.swr.emp.examenib.utils.Modo
import ec.edu.epn.swr.emp.examenib.utils.PlataformaAdapter

@SuppressLint("NewApi")
class EdicionVideojuego : AppCompatActivity() {
    var modo = Modo.CREACION
    var videojuego: Videojuego? = null
    var desarrolladora: Desarrolladora? = null
    lateinit var adaptadorGenero: GeneroAdapter
    val cambiadorActividad = CambiadorActividad(this)
    lateinit var generadorSnackbar: GeneradorSnackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edicion_videojuego)

        cambiadorActividad.callback = {
            intent ->
            intent.putExtra("idDesarrolladora", this.intent.getIntExtra("idDesarrolladora", -1))
        }

        generadorSnackbar = GeneradorSnackbar(findViewById(R.id.te_modo_videojuego))

        modo = Modo.fromInt(intent.getIntExtra("modo", Modo.CREACION.key))
        val modoText = findViewById<TextView>(R.id.te_modo_videojuego)
        modoText.text = modo.nombre


        desarrolladora = intent.getParcelableExtra("desarrolladora",Desarrolladora::class.java)
        videojuego = intent.getParcelableExtra("videojuego", Videojuego::class.java)
        val desarrolladoraText = findViewById<TextView>(R.id.tv_desarrolladora_videojuego)
        desarrolladoraText.text = desarrolladora!!.nombre!!

        val panelGenero = findViewById<RecyclerView>(R.id.panel_generos)
        adaptadorGenero = GeneroAdapter(GeneroContenedor.crearLista())
        panelGenero.adapter = adaptadorGenero
        panelGenero.layoutManager = LinearLayoutManager(this)
        adaptadorGenero.notifyDataSetChanged()

        val panelPlataforma = findViewById<RecyclerView>(R.id.panel_plataformas)
        panelPlataforma.adapter = PlataformaAdapter(ContenedorPlataforma.crearLista())
        panelPlataforma.layoutManager = LinearLayoutManager(this)
        (panelPlataforma.adapter as PlataformaAdapter).notifyDataSetChanged()


        if (modo == Modo.ACTUALIZACION) {
            videojuego?.let {
                cargarDatosVideojuego(it)
            }

        }

        val botonGuardar = findViewById<Button>(R.id.btn_guardar_videojuego)
        botonGuardar.setOnClickListener {
            accionGuardar()
            //cambiadorActividad.cambiarActividad(VistaVideojuego::class.java)
        }


    }

    override fun onRestart() {
        super.onRestart()
    }

    fun cargarDatosVideojuego(videojuego: Videojuego) {
        val panelPlataforma = findViewById<RecyclerView>(R.id.panel_plataformas)
        val panelGenero = findViewById<RecyclerView>(R.id.panel_generos)
        val nombre = findViewById<EditText>(R.id.tv_nombre_videojuego)
        val fecha = findViewById<EditText>(R.id.tv_fecha_videojuego)
        val calificacion = findViewById<EditText>(R.id.tv_calificacion_videojuego)

        if (modo == Modo.ACTUALIZACION) {
            panelGenero.adapter = GeneroAdapter(GeneroContenedor.crearLista(videojuego.generos!!))
            panelPlataforma.adapter = PlataformaAdapter(ContenedorPlataforma.crearLista(videojuego.plataformas!!))
        }

        nombre.setText(videojuego.nombre)
        calificacion.setText(videojuego.calificacion.toString())
        fecha.setText(
            ManejoFechas.mostrarFecha(videojuego.fechaLanzamiento!!)
        )

    }

    fun accionGuardar() {
        val panelPlataforma = findViewById<RecyclerView>(R.id.panel_plataformas)
        val panelGenero = findViewById<RecyclerView>(R.id.panel_generos)
        val nombre = findViewById<EditText>(R.id.tv_nombre_videojuego)
        val fecha = findViewById<EditText>(R.id.tv_fecha_videojuego)
        val calificacion = findViewById<EditText>(R.id.tv_calificacion_videojuego)
        if (modo == Modo.CREACION) {

            desarrolladora?.let {
                val videojuegoCreado = Videojuego(
                    nombre = nombre.text.toString(),
                    fechaLanzamiento = ManejoFechas.parsearFecha(fecha.text.toString()),
                    calificacion = calificacion.text.toString().toDouble(),
                    desarrolladora = it,
                    plataformas = (panelPlataforma.adapter as PlataformaAdapter).getSelected(),
                    generos = (panelGenero.adapter as GeneroAdapter).getSelected(),
                )
                crearVideojuego(videojuegoCreado)

            }
        }else if (modo == Modo.ACTUALIZACION) {
            videojuego?.let {
                it.nombre = nombre.text.toString()
                it.fechaLanzamiento = ManejoFechas.parsearFecha(fecha.text.toString())
                it.calificacion = calificacion.text.toString().toDouble()
                it.plataformas!!.clear()
                it.plataformas.addAll((panelPlataforma.adapter as PlataformaAdapter).getSelected())
                it.generos!!.clear()
                it.generos.addAll((panelGenero.adapter as GeneroAdapter).getSelected())
                actualizarVideojuego(it)
            }


        }
    }

    fun crearVideojuego(videojuego: Videojuego) {
        BaseDatos.crearVideojuego(videojuego) {
            finish()
        }
    }

    fun actualizarVideojuego(videojuego: Videojuego) {
        BaseDatos.actualizarVideojuego(videojuego) {
            when(it) {
                Variables.EXITO -> {
                    finish()
                }

                Variables.FALLO -> {
                    generadorSnackbar.mostrar("Error al actualizar el videojuego")
                }
            }
        }
    }

}