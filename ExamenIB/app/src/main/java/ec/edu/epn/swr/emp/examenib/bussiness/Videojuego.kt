package ec.edu.epn.swr.emp.examenib.bussiness

import android.os.Parcel
import android.os.Parcelable
import java.time.LocalDate

@Suppress("NewApi")
class Videojuego(
    var nombre: String?,
    var fechaLanzamiento: LocalDate?,
    var desarrolladora: Desarrolladora?,
    var calificacion: Double,
    val generos: ArrayList<Genero>? = ArrayList(),
    val plataformas: ArrayList<Plataforma>? = ArrayList(),
    val id: String? = null
): Parcelable {


    init {
        this.desarrolladora?.videojuegos?.add(this)
    }

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        LocalDate.parse(parcel.readString()),
        parcel.readParcelable(Desarrolladora::class.java.classLoader, Desarrolladora::class.java),
        parcel.readDouble(),
        getGenerosDesdeParcel(parcel),
        getPlataformasDesdeParcel(parcel),
        parcel.readString()
    )

    constructor(
        nombre: String?,
        fechaLanzamiento: LocalDate,
        desarrolladora: Desarrolladora?,
        calificacion: Double,
        plataformas: ArrayList<Plataforma>? = ArrayList(),
        generos: ArrayList<Genero>? = ArrayList(),

    ):this(
        nombre,
        fechaLanzamiento,
        desarrolladora,
        calificacion,
        generos,
        plataformas,
        id = null
    )
    override fun toString(): String {
        return "${this.nombre} (${this.desarrolladora?.nombre} ${this.fechaLanzamiento})"
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(parcel: Parcel, p1: Int) {
        parcel.writeString(nombre)
        parcel.writeString(fechaLanzamiento.toString())
        parcel.writeParcelable(desarrolladora, 0)
        parcel.writeDouble(calificacion)
        parcel.writeStringList(generos!!.map { it.key })
        parcel.writeStringList(plataformas!!.map { it.id })
        parcel.writeString(id)
    }
    companion object CREATOR : Parcelable.Creator<Videojuego> {
        override fun createFromParcel(parcel: Parcel): Videojuego {
            return Videojuego(parcel)
        }

        override fun newArray(size: Int): Array<Videojuego?> {
            return arrayOfNulls(size)
        }
        private fun getGenerosDesdeParcel(parcel: Parcel): ArrayList<Genero> {
            val lista = parcel.createStringArrayList()
            val listaFinal = ArrayList<Genero>()

            lista?.forEach { it ->
                if(it != null) {
                    Genero.from(it)?.let { it1 -> listaFinal.add(it1) }
                }
            }
            return listaFinal
        }

        private fun getPlataformasDesdeParcel(parcel: Parcel): ArrayList<Plataforma> {
            val lista = parcel.createStringArrayList()
            val listaFinal = ArrayList<Plataforma>()

            lista?.forEach { it ->
                if(it != null) {
                    Plataforma.from(it)?.let { it1 -> listaFinal.add(it1) }
                }

            }
            return listaFinal
        }

    }

}