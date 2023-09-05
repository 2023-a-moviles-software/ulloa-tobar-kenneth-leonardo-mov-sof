package ec.edu.epn.swr.emp.examenib.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.MediaStore.Video
import ec.edu.epn.swr.emp.examenib.bussiness.BaseDatos
import ec.edu.epn.swr.emp.examenib.bussiness.Desarrolladora
import ec.edu.epn.swr.emp.examenib.bussiness.Genero
import ec.edu.epn.swr.emp.examenib.bussiness.ManejoFechas
import ec.edu.epn.swr.emp.examenib.bussiness.Plataforma
import ec.edu.epn.swr.emp.examenib.bussiness.Videojuego
import java.time.LocalDate

class VideojuegoSQLHelper(
    contexto: Context?):
    SQLiteOpenHelper(
        contexto,
        VideojuegoContract.TABLE_NAME,
        null,
        1
    ){
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(VideojuegoContract.creacion())
    }
/*
    fun crearVideojuego(
        nombre: String,
        fechaLanzamiento: LocalDate,
        desarrolladora: Int,
        calificacion: Double,
        plataformas: ArrayList<Plataforma>,
        generos: ArrayList<Genero>
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoesAGuardar = ContentValues()
        valoesAGuardar.put(
            VideojuegoContract.COLUMN_NOMBRE, nombre
        )
        valoesAGuardar.put(
            VideojuegoContract.COLUMN_LANZAMIENTO, fechaLanzamiento.toString()
        )
        valoesAGuardar.put(
            VideojuegoContract.COLUMN_DESARROLLADORA_ID, desarrolladora
        )
        valoesAGuardar.put(
            VideojuegoContract.COLUMN_CALIFICACION, calificacion
        )
        valoesAGuardar.put(
            VideojuegoContract.COLUMN_GENERO, GeneroParser().parseTo(generos,
                ListaContract.SEPARATOR))
        valoesAGuardar.put(
            VideojuegoContract.COLUMN_PLATAFORMA, PlataformaParser().parseTo(plataformas,
                ListaContract.SEPARATOR))

        val resultados = conexionEscritura.insert(
            VideojuegoContract.TABLE_NAME,
            null,
            valoesAGuardar
        )
        conexionEscritura.close()
        return resultados.toInt() != -1
    }

    fun eliminarVideojuego(id: Int): Boolean {
        val conexionEscritura = writableDatabase
        val resultado = conexionEscritura
            .delete(
                VideojuegoContract.TABLE_NAME,
                "id=?",
                arrayOf(id.toString())
            )
        conexionEscritura.close()
        return resultado != -1
    }

    fun actualizarVideojuego(
        nombre: String,
        fechaLanzamiento: LocalDate,
        desarrolladora: Int,
        calificacion: Double,
        plataformas: ArrayList<Plataforma>,
        generos: ArrayList<Genero>,
        id: Int
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put(
            VideojuegoContract.COLUMN_NOMBRE, nombre
        )
        valoresAActualizar.put(
            VideojuegoContract.COLUMN_LANZAMIENTO, fechaLanzamiento.toString()
        )
        valoresAActualizar.put(
            VideojuegoContract.COLUMN_DESARROLLADORA_ID, desarrolladora
        )
        valoresAActualizar.put(
            VideojuegoContract.COLUMN_CALIFICACION, calificacion
        )
        valoresAActualizar.put(
            VideojuegoContract.COLUMN_GENERO, GeneroParser().parseTo(generos))
        valoresAActualizar.put(
            VideojuegoContract.COLUMN_PLATAFORMA, PlataformaParser().parseTo(plataformas))
        val resultado = conexionEscritura
            .update(
                VideojuegoContract.TABLE_NAME,
                valoresAActualizar,
                "id=?",
                arrayOf(id.toString())
            )
        conexionEscritura.close()
        return resultado != -1
    }

    fun consultarVideojuego(id: Int): Videojuego {
        val conexionLectura = readableDatabase
        val consulta = """
            SELECT * FROM ${VideojuegoContract.TABLE_NAME} where id=?
        """.trimIndent()
        val resultadoConsulta = conexionLectura.rawQuery(
            consulta,
            arrayOf(id.toString())
        )
        val existeUsuario = resultadoConsulta.moveToFirst()
        var videojuegoEncontrado = Videojuego(
            "",
            LocalDate.now(),
            null,
            0.0,
            id = -1
        )
        val manejoCursor = ManejoCursor(resultadoConsulta)

        if (existeUsuario) {
            videojuegoEncontrado = leerVideojuego(manejoCursor)
        }
        resultadoConsulta.close()
        conexionLectura.close()
        return videojuegoEncontrado
    }

    fun leerVideojuego(manejador: ManejoCursor): Videojuego {
        val _id = manejador.getInt(
            VideojuegoContract.COLUMN_ID, -1)
        val nombre = manejador.getString(
            VideojuegoContract.COLUMN_NOMBRE, "")
        val fechaLanzamiento =
            ManejoFechas.parsearFecha(
                manejador.getString(
                    VideojuegoContract.COLUMN_LANZAMIENTO,
                    "")
            )
        val calificacion = manejador.getDouble(
            VideojuegoContract.COLUMN_CALIFICACION,
            0.0)
        val generos = GeneroParser().parseFrom(
                manejador.getString(
                    VideojuegoContract.COLUMN_GENERO,
                    ""))

        val plataformas = PlataformaParser().parseFrom(
            manejador.getString(
                VideojuegoContract.COLUMN_PLATAFORMA,
                ""))

        val desarrolladoraId = manejador.getInt(
            VideojuegoContract.COLUMN_DESARROLLADORA_ID,-1
        )
        val desarrolladora = BaseDatos.desarrolladoras?.consultarDesarrolladora(desarrolladoraId)
        val arrayGenero = arrayListOf<Genero>()
        arrayGenero.addAll(generos)

        val arrayPlataforma = arrayListOf<Plataforma>()
        arrayPlataforma.addAll(plataformas)

        return Videojuego(
            nombre = nombre,
            fechaLanzamiento = fechaLanzamiento,
            calificacion = calificacion,
            generos = arrayGenero,
            plataformas = arrayPlataforma,
            desarrolladora = desarrolladora,
            id = _id
        )
    }
    */

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}
}