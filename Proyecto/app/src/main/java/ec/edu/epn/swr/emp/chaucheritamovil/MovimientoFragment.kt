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
import ec.edu.epn.swr.emp.chaucheritamovil.data.Movimiento
import ec.edu.epn.swr.emp.chaucheritamovil.utils.MultiHandler

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MovimientoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovimientoFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movimiento, container, false)
        val listaMovimientos = ArrayList<Movimiento>()
        val adapter = MovimientoAdapter(listaMovimientos)
        val rvMov = view.findViewById<RecyclerView>(R.id.rv_mov)
        rvMov.adapter = adapter
        rvMov.layoutManager = LinearLayoutManager(view.context)

        BaseDeDatos.consultarMovimientos({
            movimientos, reload ->
            listaMovimientos.clear()
            listaMovimientos.addAll(movimientos)
            adapter.notifyDataSetChanged()
        })

        val btnNuevoMovimiento = view.findViewById<Button>(R.id.btn_nuevo_mov)
        btnNuevoMovimiento.setOnClickListener {
            findNavController().navigate(R.id.action_menu_mov_to_nuevoMovimientoFragment)
        }

        return view
    }

}