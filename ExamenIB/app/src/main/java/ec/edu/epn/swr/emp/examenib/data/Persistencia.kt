package ec.edu.epn.swr.emp.examenib.data

import android.database.Cursor

class ManejoCursor(
    val cursor: Cursor
) {

    fun getInt(columna: String, porDefecto: Int): Int {
        val indice = cursor.getColumnIndex(columna)
        return if(indice == -1){
            porDefecto
        } else {
            cursor.getInt(indice)
        }
    }

    fun getDouble(columna: String, porDefecto: Double): Double {
        val indice = cursor.getColumnIndex(columna)
        return if(indice == -1){
            porDefecto
        } else {
            cursor.getDouble(indice)
        }
    }

    fun getString(columna: String, porDefecto: String): String {
        val indice = cursor.getColumnIndex(columna)
        return if(indice == -1){
            porDefecto
        } else {
            cursor.getString(indice)
        }
    }

    fun getBoolean(columna: String, porDefecto: Boolean): Boolean {
        val indice = cursor.getColumnIndex(columna)
        return if(indice == -1){
            porDefecto
        } else {
            cursor.getInt(indice) == 0
        }
    }
}