package ec.edu.epn.swr.emp.examenib.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ec.edu.epn.swr.emp.examenib.bussiness.Desarrolladora

class DesarrolladoraSQLHelper(
    contexto: Context):
    SQLiteOpenHelper(
        contexto,
        DesarrolladoraContract.TABLE_NAME,
        null,
        1
    ) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(DesarrolladoraContract.creacion())
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}
    /*
    fun crearDesarrolladora(
        nombre: String,
        ubicacion: String,
        anioCreacion: Int,
        paginaWeb: String,
        esIndependiente: Boolean
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoesAGuardar = ContentValues()
        valoesAGuardar.put(
            DesarrolladoraContract.COLUMN_NOMBRE, nombre
        )
        valoesAGuardar.put(
            DesarrolladoraContract.COLUMN_ANIO_CREACION, anioCreacion
        )
        valoesAGuardar.put(
            DesarrolladoraContract.COLUMN_PAGINA_WEB, paginaWeb
        )
        valoesAGuardar.put(
            DesarrolladoraContract.COLUMN_UBICACION, ubicacion
        )
        valoesAGuardar.put(
            DesarrolladoraContract.COLUMN_ES_INDEPENDIENTE, esIndependiente
        )

        val resultados = conexionEscritura.insert(
            DesarrolladoraContract.TABLE_NAME,
            null,
            valoesAGuardar
        )
        conexionEscritura.close()
        return resultados.toInt() != -1
    }

    fun eliminarDesarrolladora(id: Int): Boolean {
        val conexionEscritura = writableDatabase
        val resultado = conexionEscritura
            .delete(
                DesarrolladoraContract.TABLE_NAME,
                "id=?",
                arrayOf(id.toString())
            )
        conexionEscritura.close()
        return resultado != -1
    }

    fun actualizarDesarrolladora(
        nombre: String,
        ubicacion: String,
        anioCreacion: Int,
        paginaWeb: String,
        esIndependiente: Boolean,
        id: Int
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put(
            DesarrolladoraContract.COLUMN_NOMBRE, nombre
        )
        valoresAActualizar.put(
            DesarrolladoraContract.COLUMN_UBICACION, ubicacion
        )
        valoresAActualizar.put(
            DesarrolladoraContract.COLUMN_ANIO_CREACION, anioCreacion
        )
        valoresAActualizar.put(
            DesarrolladoraContract.COLUMN_PAGINA_WEB, paginaWeb
        )
        valoresAActualizar.put(
            DesarrolladoraContract.COLUMN_ES_INDEPENDIENTE, esIndependiente
        )
        val resultado = conexionEscritura
            .update(
                DesarrolladoraContract.TABLE_NAME,
                valoresAActualizar,
                "id=?",
                arrayOf(id.toString())
            )
        conexionEscritura.close()
        return resultado != -1
    }

    fun consultarDesarrolladora(id: Int): Desarrolladora {
        val conexionLectura = readableDatabase
        val consulta = """
            SELECT * FROM ${DesarrolladoraContract.TABLE_NAME} where id=?
        """.trimIndent()
        val resultadoConsulta = conexionLectura.rawQuery(
            consulta,
            arrayOf(id.toString())
        )
        val existeUsuario = resultadoConsulta.moveToFirst()
        var desarrolladoraEncontrada = Desarrolladora(
            "",
            "",
            0,
            "",
            false
        )

        val manejoCursor = ManejoCursor(resultadoConsulta)

        if (existeUsuario) {
            desarrolladoraEncontrada = leerDatosDesarrolladora(manejoCursor)
        }
        resultadoConsulta.close()
        conexionLectura.close()
        return desarrolladoraEncontrada
    }

    fun consultarTodo(): MutableList<Desarrolladora> {
        val conexionLectura = readableDatabase
        val consulta = """
            SELECT * FROM ${DesarrolladoraContract.TABLE_NAME}
        """.trimIndent()
        val resultadoConsulta = conexionLectura.rawQuery(
            consulta,
            arrayOf()
        )
        val existeDesarrolladora = resultadoConsulta.moveToFirst()
        val desarrolladoras = ArrayList<Desarrolladora>()

        val manejoCursor = ManejoCursor(resultadoConsulta)

        if (existeDesarrolladora) {
            do{
                val desarrolladoraEncontrada = leerDatosDesarrolladora(manejoCursor)
                if (desarrolladoraEncontrada.id != -1) {
                    desarrolladoras.add(desarrolladoraEncontrada)
                }
            }while (resultadoConsulta.moveToNext())

        }
        resultadoConsulta.close()
        conexionLectura.close()
        return desarrolladoras
    }

    private fun leerDatosDesarrolladora(manejoCursor: ManejoCursor): Desarrolladora {
        val _id = manejoCursor.getInt(
            DesarrolladoraContract.COLUMN_ID, -1)
        val nombre = manejoCursor.getString(
            DesarrolladoraContract.COLUMN_NOMBRE, "")
        val ubicacion = manejoCursor.getString(
            DesarrolladoraContract.COLUMN_UBICACION,
            "")
        val anioCreacion = manejoCursor.getInt(
            DesarrolladoraContract.COLUMN_ANIO_CREACION,
            0)
        val paginaWeb = manejoCursor.getString(
            DesarrolladoraContract.COLUMN_PAGINA_WEB,
            "")
        val esIndependiente = manejoCursor.getBoolean(
            DesarrolladoraContract.COLUMN_ES_INDEPENDIENTE,
            false
        )
        return Desarrolladora(
            nombre=nombre,
            ubicacion=ubicacion,
            anioCreacion=anioCreacion,
            paginaWeb=paginaWeb,
            esIndependiente=esIndependiente,
            id=_id
        )
    }

    */
}