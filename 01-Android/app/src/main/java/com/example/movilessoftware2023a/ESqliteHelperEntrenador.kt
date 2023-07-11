package com.example.movilessoftware2023a

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperEntrenador(
    contexto: Context?
): SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
) {
    override fun onCreate(p0: SQLiteDatabase?) {
        val script = """
            CREATE TABLE ENTRENADOR (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR(50),
                descripcion VARCHAR(50)
            )
        """.trimIndent()
        p0?.execSQL(script)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int){}

    fun crearEntrenador(
        nombre: String,
        descripcion: String
    ): Boolean
    {
        val baseDatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("descripcion", descripcion)

        val resultadoGuardar = baseDatosEscritura
            .insert(
                "ENTRENADOR",
                null,
                valoresAGuardar
            )

        baseDatosEscritura.close()
        return resultadoGuardar.toInt() != -1
    }

    fun eliminarEntrenadorFormulario(id: Int): Boolean {
        val conexionEscritura = writableDatabase

        val parametroConsultaDelete = arrayOf( id.toString() )
        var resutladoEliminacion = conexionEscritura
            .delete(
                "ENTRENADOR",
                "id=?",
                parametroConsultaDelete
            )
        conexionEscritura.close()
        return resutladoEliminacion != -1
    }

    fun actualizarEntrenadorFormulario(
        nombre: String,
        descripcion: String,
        id: Int
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("descripcion", descripcion)

        val parametrosConsultaActualizacion = arrayOf( id.toString() )
        val resultadoActualizacion = conexionEscritura
            .update(
                "ENTRENADOR",
                valoresAActualizar,
                "id=?",
                parametrosConsultaActualizacion
            )
        conexionEscritura.close()
        return resultadoActualizacion != -1
    }

    fun consultarEntrenadorPorId(id: Int): BEntrenador {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM ENTRENADOR WHERE id = ?
        """.trimIndent()

        val parametrosConsultaLectura = arrayOf( id.toString() )
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura,
            parametrosConsultaLectura
        )

        val existeUsario = resultadoConsultaLectura.moveToFirst()
        val usuarioEncontrado = BEntrenador(0,"","")
        val arreglo = arrayListOf<BEntrenador>()

        if(existeUsario) {
            do{
                val id = resultadoConsultaLectura.getInt(0)
                val nombre = resultadoConsultaLectura.getString(1)
                val descripcion = resultadoConsultaLectura.getString(2)

                if (id != null) {

                    /*En caso de requerir más de un resultado de la consulta aqui se
                    agrega el resto de entrenadores al arreglo
                     */
                    usuarioEncontrado.id = id
                    usuarioEncontrado.nombre = nombre
                    usuarioEncontrado.descripcion = descripcion
                }
            } while(resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado
    }
}