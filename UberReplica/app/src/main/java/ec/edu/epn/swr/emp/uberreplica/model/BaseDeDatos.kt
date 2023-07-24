package ec.edu.epn.swr.emp.uberreplica.model

import android.graphics.Color
import ec.edu.epn.swr.emp.uberreplica.R

class BaseDeDatos {
    companion object {
        val viajesRecientes: ArrayList<Viaje> = ArrayList()
        val sugerencias: ArrayList<Sugerencia> = ArrayList()
        val planificaciones: ArrayList<PanelCarta> = ArrayList()
        val paneles: ArrayList<PanelInfo> = ArrayList()
        val maneras: ArrayList<PanelCarta> = ArrayList()
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

            planificaciones.add(
                PanelCarta(
                    "Para grupos grandes",
                    "Espacio para todo el grupo y el equipaje",
                    R.drawable.packing
                )
            )

            planificaciones.add(
                PanelCarta(
                    "Reserva y relájate",
                    "Reserva con hasta 90 días de anticipación",
                    R.drawable.scheduled
                )
            )

            paneles.addAll(
                arrayListOf<PanelInfo>(
                    PanelInfo(
                        "¡No se amontonen!",
                        "Prueba UberXL",
                        R.drawable.packing_2,
                        Color.parseColor("#AAC9FF"),
                        Color.parseColor("#9CB5DE")),

                    PanelInfo(
                        "Viaja en tu propio tiempo",
                        "Reserva un viaje",
                        R.drawable.smartwatch_svgrepo_com,
                        Color.parseColor("#3A1558"),
                        Color.parseColor("#9CB5DE")
                    ),

                    PanelInfo(
                        "Descuentos de hasta 30%",
                        "Pedir",
                        R.drawable.french_fries_155679_1280,
                        Color.parseColor("#FBE5B6"),
                        Color.parseColor("#FEE3AC")
                    ),
                    PanelInfo(
                        "¿Necesitas ir a algún lugar?",
                        "Viajar con la app",
                        R.drawable.colorful_fireworks_isolated_on_transparent_background_ai_generated_digital_illustration_png,
                        Color.parseColor("#002661"),
                        Color.parseColor("#112C61")
                    )
                )
            )

            maneras.addAll(
                arrayListOf(
                    PanelCarta(
                        "Enviar artículo",
                        "Entregas a pedido en toda la ciudad",
                        R.drawable.c1
                    ),
                    PanelCarta(
                        "Elige la comodidad",
                        "Socios de la App con mejores calificaciones y autos más nuevos",
                        R.drawable.b2
                    ),
                    PanelCarta(
                        "Funciones de seguridad",
                        "Ayuda durante el viaje con problemas de seguridad",
                        R.drawable.b1
                    )
                )
            )
        }

    }
}