package ec.edu.epn.swr.emp.uberreplica.model

import ec.edu.epn.swr.emp.uberreplica.R

class BaseDeDatos {
    companion object {
        val viajesRecientes: ArrayList<Viaje> = ArrayList()
        val sugerencias: ArrayList<Sugerencia> = ArrayList()
        init {
            viajesRecientes.add(
                Viaje(
                    "Facultad de Sistemas EPN",
                    "QGQ6+VCC, Quito 170143"
                ))
            viajesRecientes.add(
                Viaje(
                    "Quicentro Shopping",
                    "Avenida Naciones Unidas entre, Av. 6 de Diciembre, Quito"
                )
            )

            sugerencias.add(
                Sugerencia(
                    "Viaje",
                    R.drawable.hatchback
                )
            )

            sugerencias.add(
                Sugerencia(
                    "Envíos",
                    R.drawable.box
                )
            )

            sugerencias.add(
                Sugerencia(
                    "Reserva",
                    R.drawable.schedule
                )
            )
        }

    }
}