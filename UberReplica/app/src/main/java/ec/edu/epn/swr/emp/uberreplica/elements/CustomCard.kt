package ec.edu.epn.swr.emp.uberreplica.elements

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.res.getResourceIdOrThrow
import androidx.core.view.marginEnd
import ec.edu.epn.swr.emp.uberreplica.R

class CustomCard(context: Context, attrs: AttributeSet? = null) : FrameLayout(context, attrs) {

    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        View.inflate(context,R.layout.panel_imagen, this)

        val titulo = findViewById<TextView>(R.id.titulo)
        val subtitulo = findViewById<TextView>(R.id.subtitulo)
        val imagen = findViewById<ImageView>(R.id.imagen)

        attrs?.let{
            context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.CustomCard,
                0, 0).apply {

                try {
                    val bg = getColor(R.styleable.CustomCard_bg, 0)
                    val fg = getColor(R.styleable.CustomCard_fg, 0)

                    val tituloText = getString(R.styleable.CustomCard_titulo)
                    val subtituloText = getString(R.styleable.CustomCard_subtitulo)
                    titulo.text = tituloText
                    subtitulo.text = subtituloText

                    val imagenRecurso = getResourceIdOrThrow(R.styleable.CustomCard_img)
                    imagen.setImageResource(imagenRecurso)

                } finally {
                    recycle()
                }
            }
        }
    }

    fun setBg(color1: Int, color2: Int) {
        background = CardBackground(
            color1,
            color2
        ).getDrawable(context)
    }

    fun setTitulo(texto: String) {
        val titulo = findViewById<TextView>(R.id.titulo)
        titulo.text = texto
    }

    fun setSubtitulo(texto: String) {
        val subtitulo = findViewById<TextView>(R.id.subtitulo)
        subtitulo.text = texto
    }

    fun setImg(img: Int) {
        val imagen = findViewById<ImageView>(R.id.imagen)
        imagen.setImageResource(img)
    }

}


