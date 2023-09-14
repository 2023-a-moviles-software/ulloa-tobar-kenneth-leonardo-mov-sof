package ec.edu.epn.swr.emp.chaucheritamovil.data

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDate
import javax.security.auth.callback.Callback

@Suppress("NewApi")
class BaseDeDatos {
    companion object {
        val user = Usuario(
            id = "TwldXtl8sPAlAQjKNaAN",
            nombre = "Pepito",
            apellido = "Algo"
        )
        fun realizarMovimiento(
            origen: Cuenta,
            destino: Cuenta,
            concepto: String,
            monto: Double,
            fecha: LocalDate = LocalDate.now(),
            success: () -> Unit,
            failure: () -> Unit = {}
        ) {
            val movimiento = Movimiento(
                origen = origen,
                destino = destino,
                concepto = concepto,
                monto = monto,
                fecha = fecha
            )


            val db = Firebase.firestore
            db.collection("usuarios").document(user.id)

                .collection("movimientos")
                .add(movimiento.toMap())
                .addOnSuccessListener {
                    success()
                }
                .addOnFailureListener {
                    failure()
                }

            db.collection("usuarios").document(user.id)
                .collection("cuentas")
                .document(origen._id!!)
                .update("monto", FieldValue.increment(-monto))
                .addOnSuccessListener {
                    success()
                }

            db.collection("usuarios").document(user.id)
                .collection("cuentas")
                .document(destino._id!!)
                .update("monto", FieldValue.increment(monto))
                .addOnSuccessListener {
                    success()
                }
        }

        fun crearCuenta(
            nombre: String,
            tipo: Int,
            success: () -> Unit,
            failure: () -> Unit = {}
        ) {
            getRef()
                .collection("cuentas")
                .add(Cuenta(nombre = nombre, tipo = tipo).toMap())
                .addOnSuccessListener {
                    success()
                }
                .addOnFailureListener {
                    failure()
                }
        }

        fun consultarCuenta(
            id: String,
            success: (cuenta: Cuenta?) -> Unit,
            failure: () -> Unit = {}
        ){

        }

        fun consultarCuentas(
            success: (cuentas: ArrayList<Cuenta>) -> Unit,
            failure: () -> Unit = {}
        ){
            val db = Firebase.firestore;
            db.collection("usuarios")
                .document(user.id)
                .collection("cuentas")
                .get()
                .addOnSuccessListener {
                    val cuentas = ArrayList<Cuenta>()
                    for (doc in it) {
                        val data = doc.data
                        data["id"] = doc.id
                        cuentas.add(Cuenta(data, user))
                    }
                    success(cuentas)
                }
                .addOnFailureListener {
                    failure()
                }
        }

        fun consultarMovimientos(
            success: (movimientos: ArrayList<Movimiento>, reload: Boolean) -> Unit,
            failure: () -> Unit = {}
        ){
            val db = Firebase.firestore;
            db.collection("usuarios")
                .document(user.id)
                .collection("movimientos")
                .get()
                .addOnSuccessListener {
                    val movimientos = ArrayList<Movimiento>()
                    for (doc in it) {
                        val data = doc.data
                        data["id"] = doc.id
                        movimientos.add(Movimiento(data, user))
                    }
                    success(movimientos, true)
                }
                .addOnFailureListener {
                    failure()
                }
        }

        fun getRef(): DocumentReference {
            val db = Firebase.firestore
            return db.collection("usuarios").document(user.id)
        }

        fun getTotal(callback: (value: Double) -> Unit) {
            getRef()
                .collection("cuentas")
                .whereEqualTo("tipo",0)
                .get()
                .addOnSuccessListener {
                    var valor = 0.0
                    for (doc in it) {
                        valor += doc.data.get("monto").toString().toDouble()
                    }
                    callback(valor)
                }
        }

    }
}