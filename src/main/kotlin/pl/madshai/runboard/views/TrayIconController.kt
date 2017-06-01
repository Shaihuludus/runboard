package pl.madshai.runboard.views

import com.sun.javafx.stage.StageHelper
import javafx.application.Platform
import javafx.stage.Stage
import java.awt.SystemTray
import java.awt.TrayIcon
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.imageio.ImageIO

/**
 * Created by daniel.madejek on 2017-06-01.
 */

class TrayIconController {

     val stageToShow: Stage

    constructor(stageToShow: Stage){
        this.stageToShow = stageToShow
        var trayIcon = TrayIcon(ImageIO.read( ClassLoader.getSystemResource( "GameCenter-icon.png" ) ))
        trayIcon.addActionListener { event -> Platform.runLater { this.showStage() } }
        SystemTray.getSystemTray().add(trayIcon)

    }

     fun showStage() {
         stageToShow.show()
         stageToShow.toFront()
     }


}


