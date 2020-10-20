package ar.unq.edu

import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner

class EditPostView(owner: WindowOwner, model: DraftPostModel): Dialog<DraftPostModel>(owner, model) {

    override fun createFormPanel(mainPanel: Panel) {}

    override fun createContents(mainPanel: Panel) {
        title = "Edite Post"
        iconImage = "icono.jpg"

        Label(mainPanel) withText "Portrait"

        TextBox(mainPanel) with {
            bindTo("portrait")
            setWidth(250)
        }

        Label(mainPanel) withText "Landscape"

        TextBox(mainPanel) with {
            bindTo("landscape")
            setWidth(250)
        }

        Label(mainPanel) withText "Description"

        TextBox(mainPanel) with {
            bindTo("description")
            setWidth(250)
        }

        addActions(mainPanel)
    }

    override fun addActions(panel: Panel) {
        Panel(panel) with {
            asHorizontal()
            moveRight(it, 70)
            Button(it) with {
                setWidth(50)
                caption = "Save"
                onClick {
                        accept()
                }
            }
            Button(it) with {
                caption = "Cancel"
                setWidth(50)
                onClick { cancel() }
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