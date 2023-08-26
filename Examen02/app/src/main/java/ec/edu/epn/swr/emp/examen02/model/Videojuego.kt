package ec.edu.epn.swr.emp.examen02.model

import java.time.LocalDate

class Videojuego {
    var nombre: String
    var fechaLanzamiento: LocalDate
    var desarrolladora: Desarrolladora?
    var calificacion: Double
    val plataformas: ArrayList<Plataforma>
    val generos: ArrayList<Genero>
    val id: Int

    constructor(
        nombre: String,
        fechaLanzamiento: LocalDate,
        desarrolladora: Desarrolladora?,
        calificacion: Double,
        generos: ArrayList<Genero> = ArrayList(),
        plataformas: ArrayList<Plataforma> = ArrayList(),
        id: Int = -1
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