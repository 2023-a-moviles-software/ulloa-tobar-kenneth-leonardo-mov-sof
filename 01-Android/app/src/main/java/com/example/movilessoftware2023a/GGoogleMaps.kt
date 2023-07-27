package com.example.movilessoftware2023a

import android.content.pm.PackageManager
import android.icu.text.CaseMap.Title
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PackageManagerCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import com.google.android.gms.maps.model.PolylineOptions

class GGoogleMaps : AppCompatActivity() {
    var permisos = false
    private lateinit var mapa: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ggoogle_maps)
        solicitarPermisos()
        iniciarLogicaMapa()

        val boton = findViewById<Button>(R.id.btn_ir_carolina)
        boton.setOnClickListener {
            irCarolina()
        }
    }

    fun irCarolina() {
        val carolina = LatLng(
            -0.1825684318486696,
            -78.48447277600916
        )
        val zoom = 17f
        moverCamaraConZoom(carolina, zoom)
    }

    fun solicitarPermisos() {
        val contexto = this.applicationContext
        val nombrePermisoFine = android.Manifest.permission.ACCESS_FINE_LOCATION
        val nombrePermisoCoarse = android.Manifest.permission.ACCESS_COARSE_LOCATION

        val permisosFileLocation = ContextCompat.
                checkSelfPermission(
                    contexto,
                    nombrePermisoFine
                )

        val tienePermisos = permisosFileLocation == PackageManager.PERMISSION_GRANTED
        if (tienePermisos) {
            permisos = true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    nombrePermisoFine, nombrePermisoCoarse
                ),
                1
            )
        }
    }

    fun iniciarLogicaMapa() {
        val fragmentoMapa = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        fragmentoMapa.getMapAsync { googleMap ->
            mapa = googleMap
            establecerConfiguracionMapa()
            marcadorQuicentro()
            anadirPolilinea()
            anadirPoligono()
            escucharListeners()
        }
    }

    fun escucharListeners() {
        mapa.setOnPolygonClickListener {
            Log.i("mapa","setOnPolygonClickListener")
            it.tag
        }

        mapa.setOnPolylineClickListener {
            Log.i("mapa","setOnPolylineClickListener")
            it.tag
        }

        mapa.setOnMarkerClickListener {
            Log.i("mapa","setOnMarkerClickListener")
            it.tag
            return@setOnMarkerClickListener true
        }

        mapa.setOnCameraMoveListener {
            Log.i("mapa","setOnCameraMoveListener")
        }

        mapa.setOnCameraMoveStartedListener {
            Log.i("mapa","setOnCameraMoveStartedListener")
        }

        mapa.setOnCameraIdleListener {
            Log.i("mapa","setOnCameraIdleListener")
        }
    }

    fun marcadorQuicentro() {
        val zoom = 17f
        val quicentro = LatLng(
            -0.17545195474599454, -78.47929668836093
        )
        val titulo = "Quicentro"
        val markQuicentro = anadirMarcador(quicentro, titulo)
        markQuicentro.tag = titulo
        moverCamaraConZoom(quicentro)
    }

    fun anadirMarcador(latLng: LatLng, title: String): Marker {
        return mapa.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(title)
        )!!
    }

    fun moverCamaraConZoom(latLng: LatLng, zoom: Float = 10f) {
        mapa.moveCamera(
            CameraUpdateFactory.newLatLngZoom(latLng, zoom)
        )
    }

    fun anadirPolilinea() {
        with(mapa) {
            val poliLineaUno = mapa
                .addPolyline(
                    PolylineOptions()
                        .clickable(true)
                        .add(
                            LatLng(
                                -0.1759187040647396,
                                -78.48506472421384
                            ),
                            LatLng(
                                -0.17632468492901104,
                                -78.48265589308046
                            ),
                            LatLng(
                                -0.17746143130181483,
                                -78.4770533307815
                            )
                        )
                )

            poliLineaUno.tag = "linea-1"
        }
    }

    fun anadirPoligono() {
        with(mapa) {
            val poligonoUno = mapa
                .addPolygon(
                    PolygonOptions()
                        .clickable(true)
                        .add(
                            LatLng(
                                -0.1771546902239471,
                                -78.48344981495214
                            ),
                            LatLng(
                                -0.17968981486125768,
                                -78.48269198043828
                            ),
                            LatLng(
                                -0.17710958124147777,
                                -78.48142892291546
                            )
                        )
                )

            poligonoUno.fillColor = -0xc771c4
            poligonoUno.tag = "poligono-2"
        }
    }

    fun establecerConfiguracionMapa() {
        val contexto = this.applicationContext
        with(mapa) {
            val permisosFineLocation = ContextCompat.
                    checkSelfPermission(
                        contexto,
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                    )
            val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
            if (tienePermisos) {
                mapa.isMyLocationEnabled = true
                uiSettings.isMyLocationButtonEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true
        }
    }
}