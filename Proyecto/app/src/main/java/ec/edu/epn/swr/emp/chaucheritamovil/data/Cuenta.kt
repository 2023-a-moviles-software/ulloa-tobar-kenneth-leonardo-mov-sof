package ec.edu.epn.swr.emp.chaucheritamovil.data

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QueryDocumentSnapshot

open class Cuenta(
    var _id: String? = null,
    var nombre: String? = null,
    var total: Double = 0.0,
    var tipo: Int = 0,
    var usuario: Usuario? = null
):Mapped() {

    enum class Tipo(val id_: Int, val text: String) {
        INGRESO_EGRESO(0, "Ingreso y egreso"),
        INGRESO(1, "Ingreso"),
        EGRESO(2, "Egreso");

        companion object {
            fun from(id: Int): Tipo {
                return when(id) {
                    1 -> {
                        Cuenta.Tipo.INGRESO
                    }
                    2 -> {
                        Cuenta.Tipo.EGRESO
                    }
                    else -> {
                        Cuenta.Tipo.INGRESO_EGRESO
                    }
                }
            }
        }
    }

    constructor(map: Map<String, *>, usuario: Usuario): this() {
        _id = map.get("id").toString()
        nombre = map.get("nombre").toString()
        total = map.get("monto").toString().toDouble()
        tipo = (map.get("tipo") as Long).toInt()
        this.usuario = usuario
    }

    fun ingresarMonto(monto: Double) {
        total += monto
    }

    fun retirarMonto(monto: Double) {
        total += monto;
    }

    override fun toString(): String {
        return "$nombre (${Tipo.from(tipo)})\n $${Math.abs(total)}"
    }

    override fun toMap(): Map<String, *> {
        return mapOf(
            "nombre" to nombre,
            "tipo" to tipo,
            "monto" to total
        )
    }

    override fun toRefMap(): Map<String, *> {
        return mapOf(
            "id" to _id,
            "nombre" to nombre)
    }

}