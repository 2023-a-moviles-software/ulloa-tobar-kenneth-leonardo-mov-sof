package ec.edu.epn.swr.emp.examen02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import ec.edu.epn.swr.emp.examen02.utils.CambiadorActividad

class VistaDesarrolladora : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_desarrolladora)

        val listViewVistaDesarrolladora = findViewById<ListView>(R.id.lv_desarrolladoras)
        val botonCrear = findViewById<Button>(R.id.btn_crear_desarrolladora)
        botonCrear.setOnClickListener {
            CambiadorActividad.cambiarActividad(this, EdicionDesarrolladora::class.java)
        }
    }
}