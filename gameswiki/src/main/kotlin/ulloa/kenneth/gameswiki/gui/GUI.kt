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
import javax.swing.DefaultComboBoxModel
import javax.swing.JButton
import javax.swing.JCheckBox
import javax.swing.JComboBox
import javax.swing.JDialog
import javax.swing.JFrame
import javax.swing.JOptionPane
import javax.swing.JTable
import javax.swing.JTextField
import javax.swing.table.AbstractTableModel
import kotlin.collections.ArrayList

class GUI {
    companion object {
        val main = XMLSwing<JFrame>(Init::class.java.getResourceAsStream("swing/main.xml"))
        val videojuegoDialog: VideojuegoDialog
        val desarrolladoraDialog: DesarrolladoraDialog

        init {
            main.rootComponent;
            videojuegoDialog = VideojuegoDialog(main)
            desarrolladoraDialog = DesarrolladoraDialog(main)
        }

        fun launch() {
            main.rootComponent;
            setUpElements()
            setUpActions()
            main.rootComponent.isVisible = true
        }

        fun setUpElements() {
            val table = main.getElement("desarrolladorasTable", JTable::class.java)
            val model = DesarrolladoraTableModel(Desarrolladora.dao.getAll())
            table.model = model
            val tableV = main.getElement("videojuegosTable", JTable::class.java)
            val modelV = VideojuegoTableModel()
            modelV.reload()
            tableV.model = modelV
        }

        fun setUpActions() {

            main.getElement("nuevaDesarrolladora", JButton::class.java)?.addActionListener {
                try {
                    val compania = desarrolladoraDialog.nuevaCompania()
                    compania?.let {
                        Desarrolladora.dao.save(compania)
                        (main.getElement("desarrolladorasTable", JTable::class.java).model as DesarrolladoraTableModel).reload()
                    }
                }catch (e: EmptyValueNotAllowedException){
                    JOptionPane.showMessageDialog(main.rootComponent,e.message,"Error", JOptionPane.ERROR_MESSAGE)
                }
            }

            main.getElement("actualizarDesarrolladora", JButton::class.java)?.addActionListener {
                try {
                    val compania = desarrolladoraDialog.actualizarCompania()
                    compania?.let {
                        Desarrolladora.dao.update(compania)
                        (main.getElement("desarrolladorasTable", JTable::class.java).model as DesarrolladoraTableModel).reload()
                    }
                }catch (e: EmptyValueNotAllowedException){
                    JOptionPane.showMessageDialog(main.rootComponent,e.message,"Error", JOptionPane.ERROR_MESSAGE)
                }
            }

            main.getElement("eliminarDesarrolladora", JButton::class.java)?.addActionListener {
                val table = main.getElement("desarrolladorasTable", JTable::class.java)
                val selectedIndex = table.selectedRow
                val obj = (table.model as DesarrolladoraTableModel).objectAt(selectedIndex)
                val op = JOptionPane.showConfirmDialog(main.rootComponent,"Estas por eliminar ${obj.nombre}. ¿Estás seguro?","Eliminar desarrolladora", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)
                if (op == JOptionPane.YES_OPTION) {
                    obj.id?.let {
                        Desarrolladora.dao.delete(it)
                        (main.getElement("desarrolladorasTable", JTable::class.java).model as DesarrolladoraTableModel).reload()
                    }
                }
            }


            main.getElement("nuevoVideojuego", JButton::class.java)?.addActionListener {
                try {
                    val videojuego = videojuegoDialog.nuevoVideojuego()
                    videojuego?.let {
                        Videojuego.dao.save(videojuego)
                        (main.getElement("videojuegosTable", JTable::class.java).model as VideojuegoTableModel).reload()
                    }
                }catch (e: EmptyValueNotAllowedException){
                    JOptionPane.showMessageDialog(main.rootComponent,e.message,"Error", JOptionPane.ERROR_MESSAGE)
                }
            }

            main.getElement("actualizarVideojuego", JButton::class.java)?.addActionListener {
                val videojuego = videojuegoDialog.actualizarVideojuego()
                videojuego?.let {
                    Videojuego.dao.update(videojuego)
                    (main.getElement("videojuegosTable", JTable::class.java).model as VideojuegoTableModel).reload()
                }
            }

            main.getElement("eliminarVideojuego", JButton::class.java)?.addActionListener {
                val table = main.getElement("videojuegosTable", JTable::class.java)
                val selectedIndex = table.selectedRow
                val obj = (table.model as VideojuegoTableModel).objectAt(selectedIndex)
                val op = JOptionPane.showConfirmDialog(main.rootComponent,"Estas por eliminar ${obj.nombre}. ¿Estás seguro?","Eliminar videojuego", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)
                if (op == JOptionPane.YES_OPTION) {
                    obj.id?.let {
                        Videojuego.dao.delete(it)
                        (main.getElement("videojuegosTable", JTable::class.java).model as VideojuegoTableModel).reload()
                    }
                }
            }

        }

    }
}

class DesarrolladoraTableModel: AbstractTableModel {

    var companies: ArrayList<Desarrolladora> = ArrayList();
    val names: Array<String> = arrayOf("id","Nombre", "Año", "Ubicación", "Es independiente", "URL")

    constructor(data: ArrayList<Desarrolladora>) {
        companies.addAll(data);
    }

    override fun getRowCount(): Int {
        return companies.size;
    }

    override fun getColumnCount(): Int {
        return 5;
    }

    override fun getValueAt(rowIndex: Int, columnIndex: Int): Any? {
        when(columnIndex) {
            0 -> { return companies[rowIndex].id}
            1 -> { return companies[rowIndex].nombre }
            2 -> { return companies[rowIndex].anioCreacion }
            3 -> { return companies[rowIndex].ubicacion }
            4 -> { return if(companies[rowIndex].esIndependiente) "Si" else "No" }
            5 -> { return companies[rowIndex].paginaWeb }
        }
        return ""
    }

    override fun getColumnName(column: Int): String {
        return names[column];
    }

    fun add(desarrolladora: Desarrolladora) {
        companies.add(desarrolladora);
        fireTableDataChanged();
    }

    fun reload() {
        companies = ArrayList()
        companies.addAll(Desarrolladora.dao.getAll())
        fireTableDataChanged()
    }

    fun objectAt(rowIndex: Int): Desarrolladora {
        return companies[rowIndex]
    }
}

class VideojuegoTableModel: AbstractTableModel() {

    private val videojuegos: ArrayList<Videojuego> = ArrayList()
    private val nombres: Array<String> = arrayOf("ID","Nombre", "Fecha de lanzamiento", "Desarrolladora", "Calificación","Géneros","Plataformas")

    override fun getColumnName(column: Int): String {
        return nombres[column]
    }

    override fun getRowCount(): Int {
        return videojuegos.size
    }

    override fun getColumnCount(): Int {
        return nombres.size
    }

    override fun getValueAt(rowIndex: Int, columnIndex: Int): Any? {
        val videojuego: Videojuego = videojuegos[rowIndex]
        when(columnIndex) {
            0 -> { return videojuego.id}
            1 -> { return videojuego.nombre}
            2 -> { return videojuego.fechaLanzamiento}
            3 -> { return videojuego.desarrolladora?.nombre}
            4 -> { return videojuego.calificacion}
            5 -> { return videojuego.generos}
            6 -> {return videojuego.plataformas}
        }

        return ""

    }

    fun reload() {
        videojuegos.clear()
        videojuegos.addAll(Videojuego.dao.getAll())
        fireTableDataChanged()
    }

    fun objectAt(rowIndex: Int): Videojuego {
        return videojuegos[rowIndex]
    }
}

