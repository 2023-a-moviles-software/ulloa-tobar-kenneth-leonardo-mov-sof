package ec.edu.epn.swr.emp.examen02.model

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
    }

    override fun toString(): String {
        return "($key) $title"
    }

}