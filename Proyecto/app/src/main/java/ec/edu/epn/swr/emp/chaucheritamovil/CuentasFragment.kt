package ec.edu.epn.swr.emp.chaucheritamovil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ec.edu.epn.swr.emp.chaucheritamovil.data.BaseDeDatos
import ec.edu.epn.swr.emp.chaucheritamovil.data.Cuenta
import ec.edu.epn.swr.emp.chaucheritamovil.utils.MultiHandler


class CuentasFragment : Fragment() {

    lateinit var viewRef: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewRef = inflater.inflate(R.layout.fragment_cuentas, container, false)

        cargarCuentas()
        return viewRef
    }

    fun cargarCuentas() {
        val cuentasIngresoEgreso = viewRef.findViewById<RecyclerView>(R.id.rv_mov)
        val cuentasIngreso = viewRef.findViewById<RecyclerView>(R.id.lv_cuentas_ingreso)
        val cuentasEgreso = viewRef.findViewById<RecyclerView>(R.id.lv_cuentas_egreso)
        val listaIngresoEgreso = ArrayList<Cuenta>()
        val listaEgreso = ArrayList<Cuenta>()
        val listaIngreso= ArrayList<Cuenta>()
        val adapterIngresoEgreso = CuentaAdapter(listaIngresoEgreso)
        val adapterIngreso = CuentaAdapter(listaIngreso)
        val adapterEgreso = CuentaAdapter(listaEgreso)



        cuentasIngresoEgreso.adapter = adapterIngresoEgreso
        cuentasEgreso.adapter = adapterEgreso
        cuentasIngreso.adapter = adapterIngreso

        cuentasIngresoEgreso.layoutManager = LinearLayoutManager(this.context)
        (cuentasIngresoEgreso.layoutManager as LinearLayoutManager).orientation = LinearLayoutManager.HORIZONTAL

        cuentasEgreso.layoutManager = LinearLayoutManager(this.context)
        (cuentasEgreso.layoutManager as LinearLayoutManager).orientation = LinearLayoutManager.HORIZONTAL

        cuentasIngreso.layoutManager = LinearLayoutManager(this.context)
        (cuentasIngreso.layoutManager as LinearLayoutManager).orientation = LinearLayoutManager.HORIZONTAL

        BaseDeDatos.consultarCuentas({
            cuentas ->
                listaIngreso.clear()
                listaIngreso.addAll(
                    cuentas.filter { cuenta: Cuenta -> cuenta.tipo == Cuenta.Tipo.INGRESO.id_ })

                listaIngresoEgreso.clear()
                listaIngresoEgreso.addAll(
                    cuentas.filter { cuenta: Cuenta -> cuenta.tipo == Cuenta.Tipo.INGRESO_EGRESO.id_ })

                listaEgreso.clear()
                listaEgreso.addAll(
                    cuentas.filter { cuenta: Cuenta -> cuenta.tipo == Cuenta.Tipo.EGRESO.id_ })

                adapterEgreso.notifyDataSetChanged()
                adapterIngreso.notifyDataSetChanged()
                adapterIngresoEgreso.notifyDataSetChanged()
        })

        val btnCrearCuenta = viewRef.findViewById<Button>(R.id.btn_crear_cuenta)
        btnCrearCuenta.setOnClickListener {
            findNavController().navigate(R.id.action_menu_cuentas_to_nuevaCuentaFragment)
        }

    }
}