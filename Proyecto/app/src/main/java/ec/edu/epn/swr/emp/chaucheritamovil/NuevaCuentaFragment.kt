package ec.edu.epn.swr.emp.chaucheritamovil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import ec.edu.epn.swr.emp.chaucheritamovil.data.BaseDeDatos
import ec.edu.epn.swr.emp.chaucheritamovil.data.Cuenta
import ec.edu.epn.swr.emp.chaucheritamovil.utils.MultiHandler

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class NuevaCuentaFragment : Fragment() {

    lateinit var viewRef: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewRef = inflater.inflate(R.layout.fragment_nueva_cuenta, container, false)

        val btnGuardar = viewRef.findViewById<Button>(R.id.btn_guardar_cuenta)
        btnGuardar.setOnClickListener {
            crearCuenta()
        }

        val spinnerTipo = viewRef.findViewById<Spinner>(R.id.sp_tipo)
        spinnerTipo.adapter = ArrayAdapter(
            this.requireContext(),
            android.R.layout.simple_list_item_1,
            Cuenta.Tipo.values()
        )
        (spinnerTipo.adapter as ArrayAdapter<Cuenta.Tipo>).notifyDataSetChanged()
        return viewRef
    }

    fun crearCuenta() {
        val nombreTxt = viewRef.findViewById<EditText>(R.id.te_nombre_cuenta)
        val spinnerTipo =  viewRef.findViewById<Spinner>(R.id.sp_tipo)
        val tipo = (spinnerTipo.selectedItem!! as Cuenta.Tipo).id_
        BaseDeDatos.crearCuenta(
            nombreTxt.text.toString(),
            tipo,
            {
                MultiHandler.mostrar(nombreTxt, "Cuenta creada exitósamente")
            }
        )
    }


}