package ar.unq.edu

import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner

class ConfirmView(owner: WindowOwner, model: PostModel): Dialog<PostModel>(owner, model) {

    override fun createFormPanel(p0: Panel?) {}

    override fun createContents(mainPanel: Panel) {

        title = "Instagram"
        iconImage = "icono.jpg"

        Label(mainPanel) with{
            text = "Do you want to delete ${modelObject.id}?"
        }

        addActions(mainPanel)
    }

    override fun addActions(panel: Panel) {
        Panel(panel) with{
            asHorizontal()
            moveRight(it, 20)
            Button(it) with {
                caption = "Accept"
                onClick {
                    accept()
                }
            }
            Button(it) with{
                caption = "Cancel"
                onClick {
                    cancel()
                }
            }
        }
    }

    fun moveRight(aPanel: Panel, aWidth: Int){
        Label(aPanel) with{
            text = ""
            width = aWidth
        }
    }

}
