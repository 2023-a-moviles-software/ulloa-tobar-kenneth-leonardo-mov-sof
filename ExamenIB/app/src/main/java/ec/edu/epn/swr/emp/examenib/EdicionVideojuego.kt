package ec.edu.epn.swr.emp.examenib

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewParent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.helper.widget.Flow
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ec.edu.epn.swr.emp.examenib.bussiness.BaseDatos
import ec.edu.epn.swr.emp.examenib.bussiness.Desarrolladora
import ec.edu.epn.swr.emp.examenib.bussiness.Genero
import ec.edu.epn.swr.emp.examenib.bussiness.Videojuego
import ec.edu.epn.swr.emp.examenib.utils.CambiadorActividad
import ec.edu.epn.swr.emp.examenib.utils.ContenedorPlataforma
import ec.edu.epn.swr.emp.examenib.utils.GeneradorSnackbar
import ec.edu.epn.swr.emp.examenib.utils.GeneroAdapter
import ec.edu.epn.swr.emp.examenib.utils.GeneroContenedor
import ec.edu.epn.swr.emp.examenib.utils.Modo
import ec.edu.epn.swr.emp.examenib.utils.PlataformaAdapter
import java.time.LocalDate
import java.time.format.DateTimeFormatter
@SuppressLint("NewApi")
class EdicionVideojuego : AppCompatActivity() {
    var modo = Modo.CREACION
    lateinit var videojuego: Videojuego
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

        modo = intent.getSerializableExtra("modo", Modo::class.java) as Modo
        val modoText = findViewById<TextView>(R.id.te_modo_videojuego)
        modoText.text = modo.nombre
        val idDesarrolladora = intent.getIntExtra("idDesarrolladora", -1)
        val idVideojuego = intent.getIntExtra("idVideojuego", -1)
        val desarrolladora = BaseDatos.buscarDesarrolladora(idDesarrolladora)
        val desarrolladoraText = findViewById<TextView>(R.id.tv_desarrolladora_videojuego)

        val panelGenero = findViewById<RecyclerView>(R.id.panel_generos)
        adaptadorGenero = GeneroAdapter(GeneroContenedor.crearLista())
        panelGenero.adapter = adaptadorGenero
        panelGenero.layoutManager = LinearLayoutManager(this)
        adaptadorGenero.notifyDataSetChanged()

        val panelPlataforma = findViewById<RecyclerView>(R.id.panel_plataformas)
        panelPlataforma.adapter = PlataformaAdapter(ContenedorPlataforma.crearLista())
        panelPlataforma.layoutManager = LinearLayoutManager(this)
        (panelPlataforma.adapter as PlataformaAdapter).notifyDataSetChanged()

        desarrolladora?.let {//La desarrolladora existe y no es nula
            desarrolladoraText.text = it.nombre
            it.videojuegos.find {
                    videojuego ->  videojuego.id == idVideojuego
            }?.let { //se encuentra un videojuego
                    self -> videojuego = self
            }
        }

        if (modo == Modo.ACTUALIZACION) {
            cargarDatosVideojuego(videojuego)
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
            panelGenero.adapter = GeneroAdapter(GeneroContenedor.crearLista(videojuego.generos))
            panelPlataforma.adapter = PlataformaAdapter(ContenedorPlataforma.crearLista(videojuego.plataformas))
        }

        nombre.setText(videojuego.nombre)
        calificacion.setText(videojuego.calificacion.toString())
        fecha.setText(videojuego.fechaLanzamiento.toString())

    }

    fun accionGuardar() {
        val panelPlataforma = findViewById<RecyclerView>(R.id.panel_plataformas)
        val panelGenero = findViewById<RecyclerView>(R.id.panel_generos)
        val nombre = findViewById<EditText>(R.id.tv_nombre_videojuego)
        val fecha = findViewById<EditText>(R.id.tv_fecha_videojuego)
        val calificacion = findViewById<EditText>(R.id.tv_calificacion_videojuego)
        if (modo == Modo.CREACION) {
            val idDesarrolladora = intent.getIntExtra("idDesarrolladora", -1)
            val desarrolladora = BaseDatos.buscarDesarrolladora(idDesarrolladora)
            desarrolladora?.let {
                Videojuego(
                    nombre = nombre.text.toString(),
                    fechaLanzamiento = LocalDate.parse(fecha.text),
                    calificacion = calificacion.text.toString().toDouble(),
                    desarrolladora = it,
                    plataformas = (panelPlataforma.adapter as PlataformaAdapter).getSelected(),
                    generos = (panelGenero.adapter as GeneroAdapter).getSelected(),
                    id = it.videojuegos.size + 1
                )
                //generadorSnackbar.mostrar("¡Videojuego creado exitosamente!")
                finish()
            }
        }else if (modo == Modo.ACTUALIZACION) {
            videojuego.nombre = nombre.text.toString()
            videojuego.fechaLanzamiento = LocalDate.parse(fecha.text)
            videojuego.calificacion = calificacion.text.toString().toDouble()
            videojuego.plataformas.clear()
            videojuego.plataformas.addAll((panelPlataforma.adapter as PlataformaAdapter).getSelected())
            videojuego.generos.clear()
            videojuego.generos.addAll((panelGenero.adapter as GeneroAdapter).getSelected())
            //generadorSnackbar.mostrar("¡Videojuego actualizado exitosamente!")
            finish()
        }


    }

}