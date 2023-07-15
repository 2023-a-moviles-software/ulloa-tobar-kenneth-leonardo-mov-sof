package ec.edu.epn.swr.emp.uberreplica

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ec.edu.epn.swr.emp.uberreplica.adapters.ListaViajesAdapter
import ec.edu.epn.swr.emp.uberreplica.adapters.SugerenciaAdapter
import ec.edu.epn.swr.emp.uberreplica.model.BaseDeDatos

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentInicio.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentInicio : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_inicio, container, false)
        val adaptadorViajes = ListaViajesAdapter(BaseDeDatos.viajesRecientes)
        val vistaRecientes = view?.findViewById<RecyclerView>(R.id.rv_viajes_recientes)
        Log.i("viajes", BaseDeDatos.viajesRecientes.size.toString())
        vistaRecientes?.adapter = adaptadorViajes
        vistaRecientes?.layoutManager = LinearLayoutManager(view.context)
        adaptadorViajes.notifyDataSetChanged()


        val sugerenciasAdapter = SugerenciaAdapter(BaseDeDatos.sugerencias)
        val sugerenciasView = view?.findViewById<RecyclerView>(R.id.rv_sugerencias)
        sugerenciasView?.adapter = sugerenciasAdapter
        sugerenciasView?.layoutManager = GridLayoutManager(view.context,3,GridLayoutManager.VERTICAL,false)
        sugerenciasAdapter.notifyDataSetChanged()

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentInicio.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentInicio().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}