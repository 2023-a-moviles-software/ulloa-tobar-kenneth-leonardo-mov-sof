package ec.edu.epn.swr.emp.uberreplica.model

class BaseDeDatos {
    companion object {
        val viajesRecientes: ArrayList<Viaje> = ArrayList()
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
        }

    }
}