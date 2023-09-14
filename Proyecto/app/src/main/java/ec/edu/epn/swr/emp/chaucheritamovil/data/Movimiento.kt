package ec.edu.epn.swr.emp.chaucheritamovil.data

import com.google.firebase.firestore.DocumentSnapshot
import java.time.LocalDate
import java.time.format.DateTimeFormatter
@Suppress("NewApi")
class Movimiento(
    val id: String? = null,
    val origen: Cuenta?,
    val destino: Cuenta?,
    val concepto: String,
    val fecha: LocalDate,
    val monto: Double
):Mapped() {

    constructor(map: Map<String, *>, usuario: Usuario): this(
        id = map.get("id") as String,
        origen = LazyCuenta(map.get("origen") as Map<String, *>, usuario = usuario),
        destino = LazyCuenta(map.get("destino") as Map<String, *>, usuario = usuario),
        concepto = map.get("concepto") as String,
        fecha = LocalDate.parse(map.get("fecha") as String,
            DateTimeFormatter.ofPattern("dd-MM-yyyy")
        ),
        monto = (map.get("monto")).toString().toDouble()
    )

    override fun toString(): String {
        return "Desde: ${origen?.nombre}\n" +
                "Hacia: ${destino?.nombre}\n" +
                "Concepto: $concepto\n" +
                "Monto: $monto\n" +
                "Fecha: $fecha"
    }

    override fun toMap(): Map<String, *> {
        return mapOf(
            "origen" to origen?.toRefMap(),
            "destino" to destino?.toRefMap(),
            "monto" to monto,
            "concepto" to concepto,
            "fecha" to fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))

        )
    }

    override fun toRefMap(): Map<String, *> {
        return toMap()
    }
}