package ulloa.kenneth.gameswiki

import com.formdev.flatlaf.FlatDarkLaf
import com.formdev.flatlaf.FlatLightLaf
import ulloa.kenneth.gameswiki.gui.GUI
import xmlswing.XMLSwing
import java.awt.Color
import javax.swing.JDialog
import javax.swing.UIManager
import javax.xml.catalog.CatalogFeatures.defaults


class Init{}

fun main() {
    UIManager.setLookAndFeel(FlatDarkLaf())
    val defaults = UIManager.getLookAndFeelDefaults()
    if (defaults.get("Table.alternateRowColor") == null) defaults.put("Table.alternateRowColor", Color(86,89,92))
    GUI.launch();
    //tests()
}

fun tests() {

    val main = XMLSwing<JDialog>(GUI::class.java.getResourceAsStream("swing/desarrolladora_d.xml"))
    main.rootComponent
    main.rootComponent.isVisible = true
}