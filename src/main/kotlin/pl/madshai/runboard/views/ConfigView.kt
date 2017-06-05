/*
 * #%L
 * RunBoard
 * %%
 * Copyright (C) 2017 Daniel Madejek
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package pl.madshai.runboard.views

import javafx.event.ActionEvent
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.stage.Stage
import javafx.stage.StageStyle
import pl.madshai.runboard.configuration.Shortcut
import pl.madshai.runboard.configuration.loadShortcuts
import pl.madshai.runboard.configuration.saveShortcuts
import tornadofx.*


/**
 * View for configuration window
 */
class ConfigView : View() {
    override val root: VBox by fxml("/views/Config.fxml")

    private val configTable: TableView<Shortcut> by fxid()

    private val nameField: TextField by fxid()

    private val commandField: TextField by fxid()

    private val mainBoardView: MainBoardView by inject()

    init {
        with(configTable) {
            val loadShortcuts = loadShortcuts()
            configTable.items = loadShortcuts.observable();
        }
    }

    /**
     * Called when shortcut is added
     */
    fun addShortcut(event: ActionEvent) {
        if (nameField.text.isNotBlank() && commandField.text.isNotBlank()) {
            configTable.items.add(Shortcut(nameField.text, commandField.text))
        } else {
            Alert(AlertType.ERROR, "Fields can't be empty").show()
        }
    }

    /**
     * Called when configuration should be saved
     */
    fun saveConfig() {
        configTable.items.removeIf { t -> t.name.isBlank() || t.command.isBlank() }
        saveShortcuts(configTable.items.toList())
        mainBoardView.refreshButtons()
    }

    /**
     * Called when shorcut name is changed
     */
    fun changeShortcutName(t: TableColumn.CellEditEvent<Shortcut, String>) {
        (t.getTableView().items.get(t.getTablePosition().row) as Shortcut).name = t.newValue;
    }

    /**
     * Called when shorcut command is changed
     */
    fun changeShortcutCommand(t: TableColumn.CellEditEvent<Shortcut, String>) {
        (t.getTableView().items.get(t.getTablePosition().row) as Shortcut).command = t.newValue;
    }

    /**
     * Prepares config stage
     */
    fun prepareStage(): Stage {
        var configStage = Stage()
        configStage.initStyle(StageStyle.UTILITY)
        configStage.title = "Configuration"
        configStage.scene = Scene(root)
        configStage.scene.fill = Color.TRANSPARENT
        configStage.isResizable = false;
        return configStage
    }
}
