package pl.madshai.runboard

import javafx.application.Application
import javafx.application.Platform
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import pl.madshai.runboard.views.TrayIconController
import java.awt.Toolkit.getDefaultToolkit
import java.awt.SystemTray
import java.awt.Toolkit
import java.awt.TrayIcon
import javax.imageio.ImageIO


/**
 * Created by daniel.madejek on 2017-06-01.
 */

class RunBoardFx : Application() {

    override fun start(primaryStage: Stage) {
        Platform.setImplicitExit(false);
        val root: Parent = FXMLLoader.load(javaClass.classLoader.getResource("RunBoard.fxml"));
        val scene = Scene(root, 300.0, 275.0);
        val controller = TrayIconController(primaryStage);
        primaryStage.title = "Fxml Welcome";
        primaryStage.scene = scene;
        primaryStage.show();
    }

}
