package ec.edu.epn.swr.emp.examenib.bussiness

import android.annotation.SuppressLint
import ec.edu.epn.swr.emp.examenib.data.DesarrolladoraSQLHelper
import ec.edu.epn.swr.emp.examenib.data.VideojuegoSQLHelper

class BaseDatos {

    @SuppressLint("NewApi")
    companion object {
        var desarrolladoras: DesarrolladoraSQLHelper? = null
        var videojuegos: VideojuegoSQLHelper? = null
    }


}