package pl.madshai.runboard.views

import javafx.event.ActionEvent
import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import javafx.scene.layout.VBox
import pl.madshai.runboard.configuration.Shortcut
import pl.madshai.runboard.configuration.loadShortcuts
import pl.madshai.runboard.configuration.saveShortcuts
import tornadofx.View
import tornadofx.observable


/**
 * Created by Shai on 03.06.2017.
 */
class ConfigView : View() {
    override val root: VBox by fxml("/views/Config.fxml")

    val configTable: TableView<Shortcut> by fxid()

    val nameField: TextField by fxid()

    val commandField: TextField by fxid()

    val mainBoardView : MainBoardView by inject()


    init {
        with(configTable) {
            val loadShortcuts = loadShortcuts()
            configTable.items = loadShortcuts.observable();
        }
    }

    fun addShortcut(event: ActionEvent) {
        if (nameField.text.isNotBlank() && commandField.text.isNotBlank()) {
            configTable.items.add(Shortcut(nameField.text, commandField.text))
        } else {
            val alert = Alert(AlertType.ERROR, "Fields can't be empty")
            alert.show()
        }
    }

    fun saveConfig() {
        configTable.items.removeIf { t -> t.name.isBlank() || t.command.isBlank() }
        saveShortcuts(configTable.items.toList())
        mainBoardView.refreshButtons()
    }

    fun changeShortcutName(t : TableColumn.CellEditEvent<Shortcut,String>){
        (t.getTableView().items.get(t.getTablePosition().row) as Shortcut).name =t.newValue ;
    }

    fun changeShortcutCommand(t : TableColumn.CellEditEvent<Shortcut,String>){
        (t.getTableView().items.get(t.getTablePosition().row) as Shortcut).command =t.newValue ;
    }
}