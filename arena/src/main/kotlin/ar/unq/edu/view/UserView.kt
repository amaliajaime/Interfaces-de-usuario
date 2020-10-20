package ar.unq.edu

import org.unq.ui.model.NotFound
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.commons.model.exceptions.UserException
import java.awt.Color

class UserView : SimpleWindow<UserViewViewModel> {

    constructor(parent: WindowOwner, model: UserViewViewModel) : super(parent, model)

    override fun createFormPanel(p0: Panel?) {}

    override fun createContents(mainPanel: Panel) {
        title = "Instagram"
        iconImage = "icono.jpg"

        Label(mainPanel) with { bgColor = Color(90, 70, 110) }

        Label(mainPanel) with {
            color = Color(90, 70, 110)
            setFontSize(15)
            text = "Name: ${bindTo("user.name")}"
        }

        Label(mainPanel) with {
            color = Color(90, 70, 110)
            setFontSize(15)
            text = "Email: ${bindTo("user.email")}"
        }

        Label(mainPanel) with {
            color = Color(90, 70, 110)
            setFontSize(15)
            text = "Id: ${bindTo("user.id")}"
        }

        Panel(mainPanel) with {
            asHorizontal()
            moveRight(it, 200)
            Button(it) with{
                setWidth(100)
                setBackground(Color(90, 70, 110))
                caption = "Edit user"
                onClick {
                    val user = thisWindow.modelObject.user
                    val updatedUser = UserModel(user.id,
                                              user.name,
                                              user.email,
                                              user.password,
                                              user.image)
                    val view = EditUserView(thisWindow, updatedUser)
                    view.onAccept {
                        thisWindow.modelObject.editProfile(updatedUser.id,
                                                           updatedUser.name,
                                                           updatedUser.password,
                                                           updatedUser.image)
                        thisWindow.modelObject.updateUser(updatedUser)
                    }
                    view.open()
                }
            }
        }

        Label(mainPanel) with { bgColor = Color(0, 139, 139) }

        Panel(mainPanel) with {
            asHorizontal()
            labelWithInput(it, "Search post", "keyText", 200)
            Button(it) with {
                caption = "Search"
                setBackground(Color(0, 139, 139))
                onClick{
                    try{
                        thisWindow.modelObject.searcher()
                    }
                    catch (e: NotFound){
                        throw UserException("Not found ID ${thisWindow.modelObject.keyText}")
                    }
                }
            }
            Button(it) with {
                caption = "My posts"
                setBackground(Color(90, 70, 110))
                onClick {
                    thisWindow.modelObject.updatePosts()
                }
            }
        }

        table<PostModel>(mainPanel) {
            bindItemsTo("viewPosts")
            bindSelectionTo("selected")
            visibleRows = 10
            column {
                title = "#"
                setBackground(Color(90, 70, 110))
                fixedSize = 50
                bindContentsTo("id")
            }
            column {
                title = "Landscape"
                fixedSize = 231
                bindContentsTo("landscape")
            }
            column {
                title =  "Portrait"
                fixedSize = 217
                bindContentsTo("portrait")
            }
        }

        Panel(mainPanel) with {
            asHorizontal()
            moveRight(it, 110)
            addActions(it)
        }
    }

    override fun addActions(panel: Panel) {
        Button(panel) with {
            setBackground(Color(90, 70, 110))
            width = 90
            caption = "New post"
            onClick{
                val post = DraftPostModel()
                val view = EditPostView(thisWindow, post)
                view.onAccept {
                    modelObject.addPost(post)
                }
                view.open()
            }
        }
        Button(panel) with {
            width = 90
            setBackground(Color(90, 70, 110))
            caption = "Edite post"
            onClick{
                if (modelObject.selected == null) {
                    throw UserException("Choose a post")
                }
                val post = DraftPostModel(modelObject.selected!!)
                val view = EditPostView(thisWindow, post)
                view.onAccept {
                    modelObject.editPost(modelObject.selected!!.id, post)
                }
                view.open()
            }
        }
        Button(panel) with {
            width = 90
            setBackground(Color(90, 70, 110))
            caption = "Delete post"
            onClick{
                if (modelObject.selected == null) {
                    throw UserException("Choose a post")
                }
                val confirm = ConfirmView(thisWindow, modelObject.selected!!)
                confirm.onAccept {
                    modelObject.removePost(modelObject.selected!!)
                }
                confirm.open()
            }
        }
    }

    fun labelWithInput(panel: Panel, labelText: String, property: String, aWidth: Int){
        Label(panel) with {
            text = labelText
        }
        TextBox(panel) with {
            width = aWidth
            bindTo(property)
        }
    }

    fun moveRight(aPanel: Panel, aWidth: Int){
        Label(aPanel) with{
            text = ""
            width = aWidth
        }
    }

}
