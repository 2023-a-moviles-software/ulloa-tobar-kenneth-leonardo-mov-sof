package ec.edu.epn.swr.emp.examenib.bussiness

import java.time.LocalDate

class Videojuego {
    var nombre: String
    var fechaLanzamiento: LocalDate
    var desarrolladora: Desarrolladora?
    var calificacion: Double
    val plataformas: MutableList<Plataforma>
    val generos: MutableList<Genero>
    val id: Int?

    constructor(
        nombre: String,
        fechaLanzamiento: LocalDate,
        desarrolladora: Desarrolladora?,
        calificacion: Double,
        generos: MutableList<Genero> = ArrayList(),
        plataformas: MutableList<Plataforma> = ArrayList(),
        id: Int? = null
    ) {
        this.nombre = nombre
        this.fechaLanzamiento = fechaLanzamiento
        this.desarrolladora = desarrolladora
        this.calificacion = calificacion
        this.plataformas = plataformas
        this.generos = generos
        this.id = id
        this.desarrolladora?.videojuegos?.add(this)
    }


    override fun toString(): String {
        return "${this.nombre} (${this.desarrolladora?.nombre} ${this.fechaLanzamiento})"
    }
}