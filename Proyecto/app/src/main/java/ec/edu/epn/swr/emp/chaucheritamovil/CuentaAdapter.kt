package ec.edu.epn.swr.emp.chaucheritamovil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ec.edu.epn.swr.emp.chaucheritamovil.data.Cuenta

class CuentaAdapter(private val listaCuentas: ArrayList<Cuenta>)
    :Adapter<CuentaAdapter.CuentaHolder>(){
        inner class CuentaHolder(val view: View): ViewHolder(view) {
            fun render(cuenta: Cuenta) {
                val cuentaName = view.findViewById<TextView>(R.id.nombre_cuenta)
                val tipo = view.findViewById<TextView>(R.id.tipo_cuenta)
                cuentaName.text = cuenta.nombre
                tipo.text = Cuenta.Tipo.from(cuenta.tipo).text
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuentaHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CuentaHolder(inflater.inflate(R.layout.cuenta, parent, false))
    }

    override fun getItemCount(): Int = listaCuentas.size

    override fun onBindViewHolder(holder: CuentaHolder, position: Int) {
        holder.render(listaCuentas[position])
    }
}