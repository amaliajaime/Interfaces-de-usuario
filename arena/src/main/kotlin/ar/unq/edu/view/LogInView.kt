package ar.unq.edu

import org.unq.ui.model.NotFound
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import java.awt.Color


class LogInView : SimpleWindow<LogInModel> {

    constructor(parent: WindowOwner, model: LogInModel) : super(parent, model)

    override fun createFormPanel(panel: Panel) {}

    override fun createContents(panel: Panel) {
        iconImage = "icono.jpg"
        title = "Instagram"

        Panel(panel) with {
            asColumns(2)
            labelWithInput(it, "Email:     ", "email", 140)
            labelWithPassword(it, "Password: ", "password", 140)
        }

        Panel(panel) with {
            asColumns(3)
            Label(it) with{}
            addActions(it)
        }
    }

    override fun addActions(panel: Panel) {
        Button(panel) with {
            width = 90
            caption = "Log in"
            setFontSize(10)
            setBackground(Color(0, 139, 139))
            onClick {
                try {
                    var user = model().login()
                    var userModel = UserModel(user.id, user.name, user.email, user.password, user.image)
                    var userViewViewModel = UserViewViewModel(userModel, modelObject.system)
                    thisWindow.close()
                    UserView(thisWindow, userViewViewModel).open()

                }
                catch (e: NotFound) {
                    viewErrorUser(thisWindow, model())
                }
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

    fun labelWithPassword(panel: Panel, labelText: String, property: String, aWidth: Int){
        Label(panel) with {
            text = labelText
        }
        PasswordField(panel) with {
            bindTo(property)
            width = aWidth
        }
    }

    fun viewErrorUser(view: LogInView, model: LogInModel){
        NonExistentUserView(view, model) with {
            onAccept {
                close()
            }
            open()
        }
    }

    fun model(): LogInModel{
        return thisWindow.modelObject
    }
}







