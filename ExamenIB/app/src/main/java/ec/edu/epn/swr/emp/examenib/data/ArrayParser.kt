package ec.edu.epn.swr.emp.examenib.data

import ec.edu.epn.swr.emp.examenib.bussiness.Genero
import ec.edu.epn.swr.emp.examenib.bussiness.Plataforma

abstract class ArrayParser<T> {
    abstract fun parseTo(values: List<T>, separator: String = ListaContract.SEPARATOR): String
    abstract fun parseFrom(string: String, separator: String = ListaContract.SEPARATOR): List<T>
}

class GeneroParser: ArrayParser<Genero>() {
    override fun parseTo(values: List<Genero>, separator: String): String {
        return values.joinToString(separator)
    }

    override fun parseFrom(string: String, separator: String): List<Genero> {
        return string.split(string).map {
            Genero.from(it)!!
        }
    }
}

class PlataformaParser: ArrayParser<Plataforma>() {
    override fun parseTo(values: List<Plataforma>, separator: String): String {
        return values.joinToString(separator)
    }

    override fun parseFrom(string: String, separator: String): List<Plataforma> {
        return string.split(string).map {
            Plataforma.from(it)!!
        }
    }

}