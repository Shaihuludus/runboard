package pl.madshai.runboard.views

import javafx.scene.Parent
import javafx.scene.layout.Pane
import tornadofx.View

/**
 * Created by Shai on 03.06.2017.
 */
class ConfigView : View() {
    override val root: Pane by fxml("/views/Config.fxml")
}