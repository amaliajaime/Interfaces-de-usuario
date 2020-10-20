package ar.unq.edu

import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner

class EditUserView(owner: WindowOwner, model: UserModel): Dialog<UserModel>(owner, model) {

    override fun createFormPanel(p0: Panel?) {}

    override fun createContents(panel: Panel) {
        title = "Edit profile"
        iconImage = "icono.jpg"

        labelWithInput(panel, "Name", "name", 200)

        labelWithInput(panel, "Password", "password", 200)

        labelWithInput(panel, "Image", "image", 200)

        addActions(panel)
    }

    override fun addActions(panel: Panel) {
        Panel(panel) with {
            asHorizontal()
            moveRight(it, 50)
            Button(it) with {
                caption = "Accept"
                onClick { accept() }
            }
            Button(it) with {
                caption = "Cancel"
                onClick { cancel() }
            }
        }
    }

    fun labelWithInput(panel: Panel, labelText: String, property: String, aWidth:Int){
        Label(panel) with {
            text = labelText
        }
        TextBox(panel) with {
            bindTo(property)
            width = aWidth
        }
    }

    fun moveRight(aPanel: Panel, aWidth: Int){
        Label(aPanel) with{
            text = ""
            width = aWidth
        }
    }


}
