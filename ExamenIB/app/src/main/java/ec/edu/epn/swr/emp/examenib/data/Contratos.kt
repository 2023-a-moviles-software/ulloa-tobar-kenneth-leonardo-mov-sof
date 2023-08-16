package ec.edu.epn.swr.emp.examenib.data

class DesarrolladoraContract {
    companion object {
        const val TABLE_NAME = "Desarrolladoras"
        const val COLUMN_ID = "id"
        const val COLUMN_NOMBRE = "nombre"
        const val COLUMN_UBICACION = "ubicacion"
        const val COLUMN_ANIO_CREACION = "anioCreacion"
        const val COLUMN_PAGINA_WEB = "paginaWeb"
        const val COLUMN_ES_INDEPENDIENTE = "esIndependiente"

        fun creacion(): String {
            return """
                CREATE TABLE $TABLE_NAME (
                    $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                    $COLUMN_NOMBRE TEXT NOT NULL,
                    $COLUMN_UBICACION TEXT NOT NULL,
                    $COLUMN_ANIO_CREACION INTEGER NOT NULL,
                    $COLUMN_PAGINA_WEB TEXT,
                    $COLUMN_ES_INDEPENDIENTE BOOLEAN NOT NULL
                );
            """.trimIndent()
        }
    }
}

class VideojuegoContract {
    companion object {
        const val TABLE_NAME = "Videojuegos"
        const val COLUMN_ID = "id"
        const val COLUMN_NOMBRE = "nombre"
        const val COLUMN_GENERO = "genero"
        const val COLUMN_PLATAFORMA = "plataforma"
        const val COLUMN_LANZAMIENTO = "lanzamiento"
        const val COLUMN_DESARROLLADORA_ID = "desarrolladora_id"
        const val COLUMN_CALIFICACION = "calificacion"

        fun creacion(): String {
            return """
                CREATE TABLE $TABLE_NAME (
                    $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                    $COLUMN_NOMBRE TEXT NOT NULL,
                    $COLUMN_GENERO TEXT NOT NULL,
                    $COLUMN_PLATAFORMA TEXT,
                    $COLUMN_LANZAMIENTO TEXT NOT NULL,
                    $COLUMN_CALIFICACION REAL NOT NULL,
                    $COLUMN_DESARROLLADORA_ID INTEGER,
                    FOREIGN KEY ($COLUMN_DESARROLLADORA_ID) 
                    REFERENCES ${DesarrolladoraContract.TABLE_NAME} 
                    (${DesarrolladoraContract.COLUMN_ID})
                );
            """.trimIndent()
        }
    }
}

class ListaContract {
    companion object{
        const val SEPARATOR = ";"
    }
}