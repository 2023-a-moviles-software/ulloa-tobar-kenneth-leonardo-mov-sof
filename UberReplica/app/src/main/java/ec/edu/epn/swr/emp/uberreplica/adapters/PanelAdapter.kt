package ec.edu.epn.swr.emp.uberreplica.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ec.edu.epn.swr.emp.uberreplica.R
import ec.edu.epn.swr.emp.uberreplica.elements.CardBackground
import ec.edu.epn.swr.emp.uberreplica.elements.CustomCard
import ec.edu.epn.swr.emp.uberreplica.model.PanelInfo

class PanelAdapter(
    private val listaPaneles: ArrayList<PanelInfo>
    ): RecyclerView.Adapter<PanelAdapter.PanelViewHolder>()
{
        inner class PanelViewHolder(val view: View): ViewHolder(view) {
            fun render(panelInfo: PanelInfo) {

                val img = view.findViewById<ImageView>(R.id.imagen)
                Log.i("Image", panelInfo.imagen.toString())
                img.setImageResource(panelInfo.imagen)
                img.setBackgroundColor(panelInfo.color2)

                view.findViewById<TextView>(R.id.titulo).text = panelInfo.titulo
                view.findViewById<TextView>(R.id.subtitulo).text = panelInfo.subtitulo
                val bg = view.findViewById<LinearLayout>(R.id.background_container)
                bg.background.setTint(panelInfo.color1)

            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PanelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PanelViewHolder(inflater.inflate(R.layout.panel_imagen, parent, false))
    }

    override fun getItemCount(): Int = listaPaneles.size

    override fun onBindViewHolder(holder: PanelViewHolder, position: Int) {
        holder.render(listaPaneles[position])
    }


}