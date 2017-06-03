package pl.madshai.runboard.views

import javafx.beans.property.ReadOnlyStringWrapper
import javafx.scene.SnapshotParametersBuilder
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.layout.VBox
import javafx.scene.shape.Path
import javafx.util.Callback
import pl.madshai.runboard.configuration.Shortcut
import pl.madshai.runboard.configuration.loadShortcuts
import tornadofx.View
import tornadofx.observable
import java.util.Arrays
import java.nio.file.Files
import java.io.IOException



/**
 * Created by Shai on 03.06.2017.
 */
class ConfigView : View() {
    override val root: VBox by fxml("/views/Config.fxml")

    init {
        with(root) {
            val loadShortcuts = loadShortcuts()
            var table = root.children[0] as TableView<Shortcut>
            table.columns[0].cellValueFactory = Callback { param -> ReadOnlyStringWrapper (param.value.name)  }
            table.columns[1].cellValueFactory = Callback { param -> ReadOnlyStringWrapper (param.value.command)  }

            table.items = loadShortcuts.observable();

        }
    }

}