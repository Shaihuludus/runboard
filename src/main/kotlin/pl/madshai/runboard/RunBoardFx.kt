package pl.madshai.runboard

import javafx.application.Application
import javafx.application.Platform
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.paint.Color
import javafx.stage.Stage
import javafx.stage.StageStyle
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
        var scene = Scene(root);
        scene.fill = Color.TRANSPARENT;
        val controller = TrayIconController(primaryStage);
        primaryStage.title = "Fxml Welcome";
        primaryStage.scene = scene;
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

}
