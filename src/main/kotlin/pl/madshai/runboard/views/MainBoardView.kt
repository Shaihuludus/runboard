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

import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.ScrollPane
import javafx.scene.layout.GridPane
import javafx.scene.layout.VBox
import pl.madshai.runboard.configuration.Shortcut
import pl.madshai.runboard.configuration.loadShortcuts
import tornadofx.*
import java.io.IOException

/**
 * Creates main view of application
 */
class MainBoardView : View() {

    override val root: VBox by fxml("/views/RunBoard.fxml");

    private val boardPane : GridPane by fxid()

    init {
        with(root) {
            prepareButtons()
        }
    }

    /**
     * allows to refresh buttons after changes in configuration file
     */
    fun refreshButtons() {
        boardPane.children.removeAll(boardPane.children)
        prepareButtons()
    }

    private fun prepareButtons() {
        val MAX_COLUMN = 4
        var counterRow = 0
        var counterColumn = 0
        for (loadShortcut in loadShortcuts()) {
            createButton(loadShortcut, counterColumn, counterRow)
            if (++counterColumn == MAX_COLUMN) {
                counterColumn = 0
                counterRow++

            }
        }
    }

    private fun createButton(loadShortcut: Shortcut, counterColumn: Int, counterRow: Int) {
        val button = Button(loadShortcut.name)
        button.minWidth = 200.0;
        button.styleClass.add("shortcut-button");
        button.setOnAction {
            runShortcut(loadShortcut.command)
        }
        boardPane.add(button, counterColumn, counterRow)
    }

    private fun runShortcut(command: String) {
        try {
            Runtime.getRuntime().exec(command)
        } catch(e: IOException) {
            Alert(Alert.AlertType.ERROR, "Problem with calling: " + command).show()
        }
    }

}
