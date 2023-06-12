package ulloa.kenneth.gameswiki.gui

import ulloa.kenneth.gameswiki.Init
import ulloa.kenneth.gameswiki.model.entity.Desarrolladora
import ulloa.kenneth.gameswiki.model.entity.Genero
import ulloa.kenneth.gameswiki.model.entity.Plataforma
import ulloa.kenneth.gameswiki.model.entity.Videojuego
import xmlswing.XMLSwing
import xmlswing.components.form.EmptyValueNotAllowedException
import xmlswing.components.form.Form
import xmlswing.components.input.groups.MultipleGroupPane
import java.text.DecimalFormat
import java.time.ZoneId
import java.util.*
import javax.swing.*
import javax.swing.table.AbstractTableModel
import kotlin.collections.ArrayList


class VideojuegoDialog(val main: XMLSwing<*>) {
    val handler = XMLSwing<JDialog>(Init::class.java.getResourceAsStream("swing/videogame_d.xml"))
    private var videojuego: Videojuego? = null
    init {
        setUp()
    }

    private fun setUp() {
        handler.rootComponent

        Genero.values().forEach {
            val a = JCheckBox(it.title)
            handler.getElement("generos", MultipleGroupPane::class.java).addElement(a)
        }

        Plataforma.values().forEach {
            val a = JCheckBox(it.title)
            handler.getElement("plataformas", MultipleGroupPane::class.java).addElement(a)
        }

        val desarrolladoraModel: DefaultComboBoxModel<Desarrolladora> = DefaultComboBoxModel()
        desarrolladoraModel.addAll(Desarrolladora.dao.getAll())
        handler.getElement("desarrolladora", JComboBox::class.java).model = desarrolladoraModel

        handler.getElement("guardar", JButton::class.java).addActionListener {
            videojuego = leerVideojuego()
            handler.rootComponent.isVisible = false
        }

        handler.getElement("cancelar", JButton::class.java).addActionListener {
            handler.getElement("form", Form::class.java).clear()
        }
    }

    fun nuevoVideojuego(): Videojuego? {
        recargar()
        handler.getElement("form", Form::class.java).clear()
        this.videojuego = null
        handler.rootComponent.title = "Nuevo Videojuego"
        handler.rootComponent.isVisible = true
        return videojuego
    }

    fun actualizarVideojuego(): Videojuego? {
        recargar()
        cargarVideojuego()
        handler.rootComponent.title = "Actualizar Videojuego"
        handler.rootComponent.isVisible = true
        return videojuego
    }

    private fun leerVideojuego(): Videojuego {
        val form = handler.getElement("form", Form::class.java).values
        val fecha = form["fecha"] as Date
        val fechaLanzamiento = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        val videojuego = Videojuego(
            nombre = form["nombre"] as String,
            calificacion = form["calificacion"] as Double,
            fechaLanzamiento = fechaLanzamiento,
            desarrolladora = form["desarrolladora"] as Desarrolladora,
            id = if (this.videojuego != null) this.videojuego?.id else null
        )

        (form["generos"] as ArrayList<String>).forEach {
            val genero = Genero.fromName(it)
            if(genero != null) {
                videojuego.generos.add(genero)
            }
        }

        (form["plataformas"] as ArrayList<String>).forEach {
            val plataforma = Plataforma.fromName(it)
            println(plataforma)
            if(plataforma != null) {
                videojuego.plataformas.add(plataforma)
            }
        }

        return videojuego
    }

    fun cargarVideojuego() {
        val table = main.getElement("videojuegosTable", JTable::class.java)
        val selectedIndex = table.selectedRow
        val obj = (table.model as VideojuegoTableModel).objectAt(selectedIndex)
        handler.getElement("desarrolladora", JComboBox::class.java).model.selectedItem = obj.desarrolladora
        println(handler.getElement("desarrolladora", JComboBox::class.java).selectedItem)
        handler.getElement("nombre", JTextField::class.java).text = obj.nombre
        handler.getElement("fecha", JTextField::class.java).text = obj.fechaLanzamiento.toString()
        val format = DecimalFormat("###.###")
        handler.getElement("calificacion", JTextField::class.java).text = format.format(obj.calificacion)
        videojuego = obj
    }

    private fun recargar() {
        val model = DefaultComboBoxModel<Desarrolladora>()
        model.addAll(Desarrolladora.dao.getAll())
        handler.getElement("desarrolladora", JComboBox::class.java)?.model = model
    }

}
