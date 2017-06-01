package pl.madshai.runboard

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

/**
 * Created by daniel.madejek on 2017-06-01.
 */

class RunBoardFx : Application() {

    override fun start(primaryStage: Stage) {
        val root: Parent = FXMLLoader.load(javaClass.classLoader.getResource("RunBoard.fxml"));
        val scene = Scene(root, 300.0, 275.0);
        primaryStage.title = "Fxml Welcome";
        primaryStage.scene = scene;
        primaryStage.show();
    }

}
