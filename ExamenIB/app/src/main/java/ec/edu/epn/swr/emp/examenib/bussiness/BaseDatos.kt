package ec.edu.epn.swr.emp.examenib.bussiness

import android.annotation.SuppressLint
import android.provider.MediaStore.Video
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import ec.edu.epn.swr.emp.examenib.data.DesarrolladoraSQLHelper
import ec.edu.epn.swr.emp.examenib.data.VideojuegoSQLHelper
import ec.edu.epn.swr.emp.examenib.utils.ContenedorPlataforma
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class BaseDatos {

    @SuppressLint("NewApi")
    companion object {

        var desarrolladoras =  ArrayList<Desarrolladora>()
        fun getDesarrolladoras(callback: (arrayList: ArrayList<Desarrolladora>) -> Unit) {
            val db = Firebase.firestore
            db.collection("desarrolladoras")
                .get()
                .addOnSuccessListener {
                    documents ->
                        val arrayList = ArrayList<Desarrolladora>()
                        for (document in documents) {
                            arrayList.add(parsearDesarrolladora(document))
                        }
                        callback(arrayList);
                }
        }

        fun parsearDesarrolladora(documento: QueryDocumentSnapshot): Desarrolladora {

            val desarrolladora = Desarrolladora(
                nombre = documento.data.get("nombre") as String?,
                anioCreacion = (documento.data.get("anioCreacion") as Long).toInt(),
                paginaWeb = documento.data.get("paginaWeb") as String?,
                ubicacion = documento.data.get("ubicacion") as String?,
                id = documento.id,
                esIndependiente = documento.data.get("esIndependiente") as Boolean
            )

            return desarrolladora
        }

        fun getVideojuegosPorDesarrolladora(
            desarrolladora: Desarrolladora,
            callback: (arrayList: ArrayList<Videojuego>) -> Unit ) {
            val db = Firebase.firestore
            db.collection("videojuegos")
                .whereEqualTo("desarrolladora", desarrolladora.id!!)
                .get().addOnSuccessListener {
                    documentos ->
                    val videojuegos = ArrayList<Videojuego>()
                    for (doc in documentos) {
                        val videojuego = parsearVideojuego(doc)
                        videojuego.desarrolladora = desarrolladora
                        videojuegos.add(videojuego)
                    }
                    callback(videojuegos)
                }
        }

        fun crearVideojuego(
            videojuego: Videojuego,
            callback: (it: DocumentReference) -> Unit) {
            val map = videojuegoAMapa(videojuego)
            val db = Firebase.firestore
            db.collection("videojuegos")
                .add(map)
                .addOnSuccessListener {
                    callback(it)
                }

        }

        fun actualizarVideojuego(videojuego: Videojuego, callback: (it: Int) -> Unit) {
            val map = videojuegoAMapa(videojuego)
            val db = Firebase.firestore
            db.collection("videojuegos")
                .document(videojuego.id!!)
                .set(map)
                .addOnSuccessListener {
                    callback(Variables.EXITO)
                }
                .addOnFailureListener {
                    callback(Variables.FALLO)
                }

        }

        fun parsearVideojuego(documento: QueryDocumentSnapshot): Videojuego {

            val videojuego = Videojuego(
                documento.data.get("nombre") as String?,
                LocalDate.parse((documento.data.get("fechaLanzamiento") as String),
                DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                desarrolladora = null,
                documento.data.get("calificacion") as Double,
                plataformas = Plataforma.toList(documento.get("plataformas") as ArrayList<String>)
                as ArrayList<Plataforma>,
                generos = Genero.toList(documento.get("generos") as ArrayList<String>)
                        as ArrayList<Genero>,
                id = documento.id
            )

            return videojuego
        }

        fun videojuegoAMapa(videojuego: Videojuego): Map<String, Any?> {
            return  mapOf(
                    "nombre" to videojuego.nombre,
                    "fechaLanzamiento" to videojuego.fechaLanzamiento!!
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                    "calificacion" to videojuego.calificacion,
                    "plataformas" to videojuego.plataformas!!.map { it.id },
                    "generos" to videojuego.generos!!.map { it.key },
                    "desarrolladora" to videojuego.desarrolladora!!.id!!
                )
        }

        fun eliminarVideojuego(id: String, _callback: (it: Int) -> Unit) {
            val db = Firebase.firestore
            db.collection("videojuegos")
                .document(id)
                .delete()
                .addOnSuccessListener {
                    _callback(Variables.EXITO)
                }
                .addOnFailureListener {
                    _callback(Variables.FALLO)
                }
        }
    }





}