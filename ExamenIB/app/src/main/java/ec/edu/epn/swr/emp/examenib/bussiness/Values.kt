package ec.edu.epn.swr.emp.examenib.bussiness

import android.annotation.SuppressLint
import java.time.LocalDate
import java.time.format.DateTimeFormatter

enum class Genero(val key: String, val title: String) {
    ACTION("AC","Action"),
    FPS("FPS","FPS"),
    TPS("TPS","TPS"),
    BATTLE_ROYALE("BR","Battle Royale"),
    MMO("MMO","MMO"),
    RPG("RPG","RPG"),
    ADVENTURE("ADV","Adventure"),
    SHOOTER("SHO","Shooter"),
    HORROR("HRR","Horror"),
    CARDS("CRD","Cards"),
    BOARD_GAME("BG","Board Game"),
    PLATFORMER("PF","Platformer");

    init {
        this.key
        this.title;
    }

    companion object {
        fun from(s: String): Genero? = values().find { it.key == s }
        fun fromName(s: String): Genero? = values().find {it.title == s}
        fun toList(str: String): MutableList<Genero> {
            val found = ArrayList<Genero>()
            for(i in str.split(";")) {
                val item = Genero.from(i.trim());
                if (item != null) {
                    found.add(item);
                }
            }
            return found
        }

        fun toList(str: Collection<String>): MutableList<Genero> {
            val found = ArrayList<Genero>()
            for(i in str) {
                val item = Genero.from(i.trim());
                if (item != null) {
                    found.add(item);
                }
            }
            return found
        }
    }

    override fun toString(): String {
        return "($key) $title"
    }

}

enum class Plataforma(val id: String, val title: String) {
    XBOX("XBX","Xbox"),
    PS("PS","PlayStation"),
    PC("PC","PC"),
    N_SWITCH("NSW","Nintendo Switch"),
    MOBILE("MOB","Mobile Devices"),
    NES("NES","NES");

    init {
        this.id
        this.title
    }

    override fun toString(): String {
        return this.title
    }

    companion object {
        fun from(s: String): Plataforma? = Plataforma.values().find { it.id == s }
        fun fromName(s: String): Plataforma? = Plataforma.values().find { it.title == s }
        fun toList(str: String): MutableList<Plataforma> {
            val found = ArrayList<Plataforma>()
            for(i in str.split(";")) {
                val item: Plataforma? = Plataforma.from(i.trim());
                if (item != null)
                    found.add(item);
            }
            return found
        }

        fun toList(list: Collection<String>): MutableList<Plataforma> {
            val found = ArrayList<Plataforma>()
            for(i in list) {
                val item: Plataforma? = Plataforma.from(i.trim());
                if (item != null)
                    found.add(item);
            }
            return found
        }
    }


}
@SuppressLint("NewApi")
class ManejoFechas {
    companion object {
        val formato = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        fun parsearFecha(string: String): LocalDate {
            return LocalDate.parse(string, formato)
        }

        fun mostrarFecha(fecha: LocalDate): String {
            return fecha.format(formato)
        }

    }
}

class Variables {
    companion object {
        val EXITO = 0
        val FALLO = -1
    }
}




