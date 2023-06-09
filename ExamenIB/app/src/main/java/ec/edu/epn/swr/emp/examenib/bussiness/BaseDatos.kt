package ec.edu.epn.swr.emp.examenib.bussiness

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

class BaseDatos {

    @SuppressLint("NewApi")
    companion object {
        val desarrolladoras = ArrayList<Desarrolladora>()
        private var ultimoId = 0

        init {
            desarrolladoras.add(
                Desarrolladora(
                    id = 1,
                    nombre = "Nintendo",
                    esIndependiente = true,
                    anioCreacion = 1889,
                    paginaWeb = "www.nintendo.com",
                    ubicacion = "Kioto, Japón",

                )
            )

            desarrolladoras.add(
                Desarrolladora(
                    id = 2,
                    nombre = "Activision",
                    ubicacion = "Santa Monica, California",
                    anioCreacion = 1979,
                    paginaWeb = "www.activision.com",
                    esIndependiente = true
                )
            )

            desarrolladoras.add(
                Desarrolladora(
                    id = 3,
                    nombre = "Rockstar Games",
                    ubicacion = "New York, Estados Unidos",
                    anioCreacion = 1998,
                    paginaWeb = "www.rockstargames.com",
                    esIndependiente = true
                )
            )

            Videojuego(
                    id = 1,
                    nombre = "Mario Bros",
                    fechaLanzamiento = LocalDate.of(1985,9,13),
                    generos = arrayListOf(Genero.BOARD_GAME),
                    plataformas = arrayListOf(
                        Plataforma.N_SWITCH,
                        Plataforma.NES,
                        Plataforma.PC
                    ),
                    calificacion = 0.9,
                    desarrolladora = desarrolladoras[0]
                )

            Videojuego(
                    id = 2,
                    nombre = "Mario Party",
                    fechaLanzamiento = LocalDate.of(1998,1,18),
                    generos = arrayListOf(Genero.BATTLE_ROYALE),
                    plataformas = arrayListOf(
                        Plataforma.N_SWITCH,
                        Plataforma.NES,
                        Plataforma.PC
                    ),
                    calificacion = 0.7,
                    desarrolladora = desarrolladoras[0]
            )

            Videojuego(
                    id = 3,
                    nombre = "GTA San Andreas",
                    fechaLanzamiento = LocalDate.of(2004,1,24),
                    generos = arrayListOf(
                        Genero.ACTION,
                        Genero.FPS,
                        Genero.SHOOTER
                    ),
                    plataformas = arrayListOf(
                        Plataforma.PS,
                        Plataforma.XBOX,
                        Plataforma.PC
                    ),
                    calificacion = 0.8,
                    desarrolladora = desarrolladoras[2]
                )

            Videojuego(
                    id = 4,
                    nombre = "Call of Duty",
                    fechaLanzamiento = LocalDate.of(2006,9,22),
                    generos = arrayListOf(
                        Genero.ACTION,
                        Genero.FPS,
                        Genero.SHOOTER
                    ),
                    plataformas = arrayListOf(
                        Plataforma.PS,
                        Plataforma.XBOX,
                        Plataforma.PC
                    ),
                    calificacion = 0.78,
                    desarrolladora = desarrolladoras[1]
                )

            ultimoId = desarrolladoras.size

        }

        fun buscarDesarrolladora(id: Int): Desarrolladora? {
            return desarrolladoras.find { it.id == id }
        }

        fun eliminar(id: Int): Boolean {
            val desarrolladora = buscarDesarrolladora(id) ?: return false
            return desarrolladoras.remove(desarrolladora)
        }

        fun guardarDesarrolladora(desarrolladora: Desarrolladora) {
            desarrolladoras.add(desarrolladora)
        }

        fun crearDesarrolladora(
            nombre: String,
            ubicacion: String,
            anio: Int,
            paginaWeb: String,
            esIndependiente: Boolean
            ) {

            desarrolladoras.add(
                Desarrolladora(
                    nombre = nombre,
                    ubicacion = ubicacion,
                    anioCreacion = anio,
                    paginaWeb = paginaWeb,
                    esIndependiente = esIndependiente,
                    id = ultimoId + 1
                )
            )
            ultimoId += 1
        }

        fun actualizarDesarrolladora(
            nombre: String,
            ubicacion: String,
            anio: Int,
            paginaWeb: String,
            esIndependiente: Boolean,
            id: Int
        ) {
            val desarrolladora = buscarDesarrolladora(id)
            desarrolladora?.nombre = nombre
            desarrolladora?.ubicacion = ubicacion
            desarrolladora?.anioCreacion = anio
            desarrolladora?.paginaWeb = paginaWeb
            desarrolladora?.esIndependiente = esIndependiente

        }

    }


}