package ec.edu.epn.swr.emp.examenib.utils

enum class Modo(val key: Int, val nombre: String) {
    CREACION(0, "Creación"),
    ACTUALIZACION(1, "Actualización");

    companion object  {
        fun fromInt(key: Int): Modo {
            return values().find { it.key == key } ?: CREACION
        }
    }
}