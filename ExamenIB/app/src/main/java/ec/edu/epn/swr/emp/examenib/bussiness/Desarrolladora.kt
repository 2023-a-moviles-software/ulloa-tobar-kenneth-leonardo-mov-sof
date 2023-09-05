package ec.edu.epn.swr.emp.examenib.bussiness

import android.annotation.SuppressLint
import android.os.Build
import android.os.Build.VERSION_CODES.TIRAMISU
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi

class Desarrolladora(
    var nombre: String?,
    var ubicacion: String?,
    var anioCreacion: Int,
    var paginaWeb: String?,
    var esIndependiente: Boolean = true,
    val videojuegos: ArrayList<Videojuego> = ArrayList(),
    val id: String? = null
): Parcelable {


    @RequiresApi(Build.VERSION_CODES.Q, TIRAMISU)
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readBoolean(),
        parcel.readArrayList(Videojuego::class.java.classLoader, Videojuego::class.java) as ArrayList<Videojuego>,
        parcel.readString()
    )

    constructor(nombre: String,
                ubicacion: String,
                paginaWeb: String,
                anio: Int,
                esIndependiente: Boolean) :
            this(nombre, ubicacion, anio, paginaWeb, esIndependiente)


    override fun toString(): String {
        return "${this.nombre} (${this.anioCreacion})"
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeString(nombre)
        p0.writeString(ubicacion)
        p0.writeInt(anioCreacion)
        p0.writeString(paginaWeb)
        p0.writeBoolean(esIndependiente)
        p0.writeList(videojuegos)
        p0.writeString(id)
    }

    override fun equals(other: Any?): Boolean {
        if (other is Desarrolladora) {
            if(this.nombre == other.nombre) {
                return true
            }
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = nombre?.hashCode() ?: 0
        result = 31 * result + (ubicacion?.hashCode() ?: 0)
        result = 31 * result + anioCreacion
        result = 31 * result + (paginaWeb?.hashCode() ?: 0)
        result = 31 * result + esIndependiente.hashCode()
        result = 31 * result + videojuegos.hashCode()
        result = 31 * result + (id?.hashCode() ?: 0)
        return result
    }

    companion object CREATOR : Parcelable.Creator<Desarrolladora> {

        override fun createFromParcel(parcel: Parcel): Desarrolladora {
            return Desarrolladora(parcel)
        }

        override fun newArray(size: Int): Array<Desarrolladora?> {
            return arrayOfNulls(size)
        }
    }

}