package ec.edu.epn.swr.emp.chaucheritamovil.data

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import javax.security.auth.callback.Callback

class LazyCuenta(map: Map<String, *>,
                 usuario: Usuario,
                 callback: () -> Unit = {}) : Cuenta(_id = null, usuario=usuario) {

    init {
        _id = map.get("id") as String
        nombre = map.get("nombre") as String
    }
}