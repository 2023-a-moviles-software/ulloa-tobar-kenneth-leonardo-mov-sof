package ec.edu.epn.swr.emp.examen02.model

enum class Plataforma(val key: String, val title: String) {
    XBOX("XBX","Xbox"),
    PS("PS","PlayStation"),
    PC("PC","PC"),
    N_SWITCH("NSW","Nintendo Switch"),
    MOBILE("MOB","Mobile Devices"),
    NES("NES","NES");

    init {
        this.key
        this.title
    }

    override fun toString(): String {
        return this.title
    }

    companion object {
        fun from(s: String): Plataforma? = Plataforma.values().find { it.key == s }
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
    }


}