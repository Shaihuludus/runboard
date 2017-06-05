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
package pl.madshai.runboard

import javafx.application.Platform
import javafx.scene.Scene
import javafx.scene.paint.Color
import javafx.stage.Stage
import javafx.stage.StageStyle
import pl.madshai.runboard.configuration.loadShortcuts
import pl.madshai.runboard.views.ConfigView
import pl.madshai.runboard.views.MainBoardView
import tornadofx.*
import java.awt.MenuItem
import java.awt.PopupMenu
import java.awt.TrayIcon
import java.awt.event.MouseEvent
import javax.imageio.ImageIO

/**
 *Creates application with tray icon
 */
class RunBoardFx : App(MainBoardView::class) {

    private var showMenuItem = MenuItem();

    private val configView: ConfigView by inject()

    private var configStage: Stage = Stage()

    init {
        with(configView) {
            configStage = configView.prepareStage()
        }

    }

    override fun start(primaryStage: Stage) {
        prepareTrayMenu()
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.title = "RunBoard"
        super.start(primaryStage)
        loadShortcuts()
    }

    override fun createPrimaryScene(view: UIComponent): Scene {
        var toReturn = Scene(view.root)
        toReturn.fill = Color.TRANSPARENT
        return toReturn
    }

    private fun prepareTrayMenu() {
        trayicon(ImageIO.read(ClassLoader.getSystemResource("icon.png")), "RunBoard", false, false, { configureTray(this) })
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
        popupMenu.item("Configuration", null, {
            this.setOnAction(true, {
                configStage.show()
            })
        })
        popupMenu.item("Exit", null, { this.setOnAction { Platform.exit() } })

    }
}
