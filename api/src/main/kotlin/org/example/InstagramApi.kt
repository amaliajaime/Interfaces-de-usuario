package org.example

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.security.Role
import io.javalin.core.util.RouteOverviewPlugin
import org.example.controller.PostController
import org.example.controller.SearchController
import org.example.controller.UserController
import org.unq.ui.bootstrap.getInstagramSystem

enum class ToDoRoles: Role {
    ANYONE, USER
}

class InstagramApi {
    fun start() {
        val instagramSystem = getInstagramSystem()
        val userController = UserController(instagramSystem)
        val postController = PostController(instagramSystem)
        val searchController = SearchController(instagramSystem)
        val app = Javalin.create {
            it.defaultContentType = "application/json"
            it.registerPlugin(RouteOverviewPlugin("/routes"))
            it.accessManager(InstagramAccessManager(instagramSystem))
        }
        app.routes {
            path("register") {
                post(userController::register, setOf(ToDoRoles.ANYONE))
            }
            path("login") {
                post(userController::login, setOf(ToDoRoles.ANYONE))
            }
            path("user") {
                get(userController::getUserLogin, setOf(ToDoRoles.USER))
                path(":id") {
                    get(userController::getUser, setOf(ToDoRoles.USER))
                    path("follow"){
                        put(userController::follow, setOf(ToDoRoles.USER))
                    }
                }
            }
            path("post") {
                path(":id") {
                    get(postController::getPost, setOf(ToDoRoles.USER))
                }
                path("like"){
                    put(postController::like, setOf(ToDoRoles.USER))
                }
                path("comment"){
                    post(postController::comment, setOf(ToDoRoles.USER))
                }
            }
            path("search"){
                get(searchController::search, setOf(ToDoRoles.USER))
            }
        }
        app.start(7000)
    }
}

fun main(args: Array<String>) {
    InstagramApi().start()
}



