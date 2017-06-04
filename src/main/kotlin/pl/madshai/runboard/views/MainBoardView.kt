package pl.madshai.runboard.views

import javafx.scene.control.Button
import javafx.scene.control.ScrollPane
import javafx.scene.layout.GridPane
import pl.madshai.runboard.configuration.Shortcut
import pl.madshai.runboard.configuration.loadShortcuts
import tornadofx.View


/**
 * Created by daniel.madejek on 2017-06-02.
 */

class MainBoardView : View() {

    override val root: ScrollPane by fxml("/views/RunBoard.fxml");

    private val boardPane = root.content as GridPane

    init {
        with(root) {
            prepareButtons()
        }
    }



    fun refreshButtons(){
        boardPane.children.removeAll(boardPane.children)
        prepareButtons()
    }

    private fun prepareButtons(){
        val MAX_COLUMN =4
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
        button.setOnAction { Runtime.getRuntime().exec(loadShortcut.command) }
        boardPane.add(button, counterColumn, counterRow)
    }

}
