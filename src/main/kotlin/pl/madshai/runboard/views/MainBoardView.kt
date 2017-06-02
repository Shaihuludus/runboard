package pl.madshai.runboard.views

import javafx.scene.Parent
import javafx.scene.control.Button
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.GridPane
import javafx.scene.layout.Pane
import pl.madshai.runboard.configuration.loadShortcuts
import tornadofx.*

/**
 * Created by daniel.madejek on 2017-06-02.
 */

class MainBoardView : View(){

    override val root: Pane by fxml("/views/RunBoard.fxml");
    val borderPane : GridPane = root.children[0] as GridPane
    val controller : RunBoardController by inject()

    init {
        with(root){
            for (loadShortcut in loadShortcuts()) {
                val button = Button(loadShortcut.name)
                button.styleClass.add("shiny-orange");
                borderPane.add(button,8,8)
            }

        }
    }

}
