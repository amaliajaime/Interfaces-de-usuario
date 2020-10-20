package ar.unq.edu

import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner
import java.awt.Color

class NonExistentUserView(owner: WindowOwner, model: LogInModel) : Dialog<LogInModel>(owner, model) {

    override fun createFormPanel(panel: Panel) {}

    override fun createContents(panel: Panel?) {

        title = "Ups"

        iconImage = "iconoError.jpg"

        Label(panel) with {
            text = "Email or password incorrect."
            setFontSize(10)
        }

        Label(panel) with {
            text = "Try again."
            setFontSize(10)
        }

        Panel(panel) with {
            asColumns(3)
            Label(it) with {}
            Button(it) with {
                setWidth(60)
                caption = "Accept"
                setFontSize(10)
                setBackground(Color(90, 70, 110))
                onClick { accept() }
            }
        }
    }

}
