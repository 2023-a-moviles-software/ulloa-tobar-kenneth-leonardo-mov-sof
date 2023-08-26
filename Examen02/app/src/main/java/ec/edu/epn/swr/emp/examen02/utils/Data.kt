package ec.edu.epn.swr.emp.examen02.utils

import ec.edu.epn.swr.emp.examen02.model.Desarrolladora

class Data {
    companion object {
        fun getDesarrolladoras(): ArrayList<Desarrolladora> {
            return arrayListOf<Desarrolladora>(
                Desarrolladora(
                    "Nintendo",
                    "Japón",
                    1989,
                    "www.nintendo.com",
                    true,
                )
            )
        }
    }
}