package pl.madshai.runboard

import javafx.application.Platform
import javafx.scene.Scene
import javafx.scene.paint.Color
import javafx.stage.Stage
import javafx.stage.StageStyle
import pl.madshai.runboard.configuration.loadShortcuts
import pl.madshai.runboard.views.MainBoardView
import tornadofx.*
import java.awt.MenuItem
import java.awt.PopupMenu
import java.awt.TrayIcon
import java.awt.event.MouseEvent
import javax.imageio.ImageIO


/**
 * Created by daniel.madejek on 2017-06-01.
 */

class RunBoardFx : App(MainBoardView::class) {

    private var showMenuItem = MenuItem();

    override fun start(primaryStage: Stage) {
        prepareTrayMenu()
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        super.start(primaryStage)
        loadShortcuts()
    }

    override fun createPrimaryScene(view: UIComponent): Scene {
        var toReturn = Scene(view.root)
        toReturn.fill = Color.TRANSPARENT
        return toReturn
    }

    private fun prepareTrayMenu() {
        trayicon(ImageIO.read(ClassLoader.getSystemResource("GameCenter-icon.png")), "RunBoard", false, false, { configureTray(this) })
    }

    private fun configureTray(tray: TrayIcon) {
        tray.setOnMouseClicked(true, MouseEvent.BUTTON1, 1, {
            showMenuChange()
            stageShow()
        })
        tray.menu("RunBoard", { createItems(this) })
    }

    private fun stageShow() {
        if (FX.primaryStage.isShowing) {
            FX.primaryStage.close()
        } else {
            FX.primaryStage.show()
        }
    }

    private fun showMenuChange() {
        if (FX.primaryStage.isShowing) {
            this.showMenuItem.label = "Show"
        } else {
            this.showMenuItem.label = "Hide"
        }

    }

    private fun createItems(popupMenu: PopupMenu) {
        this.showMenuItem = popupMenu.item("Hide", null, { this.setOnAction(true, { showMenuChange(); stageShow() }) })
        popupMenu.item("Exit", null, { this.setOnAction { Platform.exit() } })
    }
}
