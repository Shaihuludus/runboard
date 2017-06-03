package pl.madshai.runboard.views

import javafx.scene.control.Button
import javafx.scene.control.ScrollPane
import javafx.scene.layout.GridPane
import javafx.scene.layout.Pane
import pl.madshai.runboard.configuration.loadShortcuts
import tornadofx.View
import javafx.scene.layout.Priority
import javafx.scene.layout.RowConstraints



/**
 * Created by daniel.madejek on 2017-06-02.
 */

class MainBoardView : View() {

    override val root: ScrollPane by fxml("/views/RunBoard.fxml");
    val borderPane = root.content as GridPane
    val controller: RunBoardController by inject()

    init {
        with(root) {
            val MAX_COLUMN =4
            var counterRow = 0
            var counterColumn = 0
            for (loadShortcut in loadShortcuts()) {
                val button = Button(loadShortcut.name)
                button.minWidth = 200.0;
                button.styleClass.add("shortcut-button");
                borderPane.add(button, counterColumn,counterRow)
                if(++counterColumn == MAX_COLUMN){
                    counterColumn=0
                    counterRow++

                }
            }

        }
    }

}
