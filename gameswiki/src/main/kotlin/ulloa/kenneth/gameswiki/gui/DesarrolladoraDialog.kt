package ulloa.kenneth.gameswiki.gui

import ulloa.kenneth.gameswiki.Init
import ulloa.kenneth.gameswiki.model.entity.Desarrolladora
import xmlswing.XMLSwing
import xmlswing.components.form.Form
import java.text.DecimalFormat
import javax.swing.*

class DesarrolladoraDialog(val main: XMLSwing<*>) {
    private val handler: XMLSwing<JDialog>
    private var desarrolladora: Desarrolladora? = null
    init {
        handler = XMLSwing(Init::class.java.getResourceAsStream("swing/desarrolladora_d.xml"))
        handler.rootComponent
        setUp()
    }

    private fun setUp() {
        handler.getElement("guardar",JButton::class.java)?.addActionListener {
            this.desarrolladora = leerCompania()
            handler.rootComponent.isVisible = false
        }

        handler.getElement("cancelar",JButton::class.java)?.addActionListener {
            handler.getElement("form", Form::class.java).clear()
        }
    }

    fun nuevaCompania(): Desarrolladora? {
        this.desarrolladora = null
        handler.rootComponent.isVisible = true
        return this.desarrolladora
    }

    private fun leerCompania(): Desarrolladora {
        val values = handler.getElement("form", Form::class.java).values
        return Desarrolladora(
            nombre = values["nombre"] as String,
            anioCreacion = (values["anio"] as Long).toInt(),
            paginaWeb = values["url"] as String,
            ubicacion = values["ubicacion"] as String,
            esIndependiente = (values["indep"] == "Si"),
            id = if(desarrolladora != null) desarrolladora!!.id else null

        )
    }

    private fun cargarCompania() {
        val table = main.getElement("desarrolladorasTable", JTable::class.java)
        val selectedIndex = table.selectedRow
        val obj = (table.model as DesarrolladoraTableModel).objectAt(selectedIndex)
        handler.getElement("nombre", JTextField::class.java).text = obj.nombre
        handler.getElement("anio", JTextField::class.java).text = obj.anioCreacion.toString()
        handler.getElement("ubicacion", JTextField::class.java).text = obj.ubicacion
        handler.getElement("url", JTextField::class.java).text = obj.paginaWeb
        if (obj.esIndependiente) {
            handler.getElement("si", JRadioButton::class.java).isSelected = true
        } else {
            handler.getElement("no", JRadioButton::class.java).isSelected = true
        }
        desarrolladora = obj
    }

    fun actualizarCompania(): Desarrolladora? {
        cargarCompania()
        handler.rootComponent.isVisible = true
        return desarrolladora
    }

}