package ec.edu.epn.swr.emp.examen02.model

class Desarrolladora {
    var nombre: String
    var ubicacion: String
    var anioCreacion: Int
    var paginaWeb: String
    var esIndependiente: Boolean
    val videojuegos: ArrayList<Videojuego>
    val id: Int

    constructor(
        nombre: String,
        ubicacion: String,
        anioCreacion: Int,
        paginaWeb: String,
        esIndependiente: Boolean,
        videojuegos: ArrayList<Videojuego> = ArrayList(),
        id: Int = -1
    ) {
        this.nombre = nombre
        this.ubicacion = ubicacion
        this.anioCreacion = anioCreacion
        this.paginaWeb = paginaWeb
        this.esIndependiente = esIndependiente
        this.id = id
        this.videojuegos = videojuegos
    }

    override fun toString(): String {
        return "${this.nombre} (${this.anioCreacion})"
    }

    override fun equals(other: Any?): Boolean {
        if (other is Desarrolladora) {
            if(this.nombre == other.nombre) {
                return true
            }
        }
        return super.equals(other)
    }

}