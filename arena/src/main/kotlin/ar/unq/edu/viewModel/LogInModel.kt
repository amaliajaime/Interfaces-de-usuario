package ar.unq.edu

import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.User
import org.uqbar.commons.model.annotations.Observable

@Observable
class LogInModel (system: InstagramSystem){

    val system :InstagramSystem = system
    var email = ""
    var password = ""

    fun login() : User {
        return system.login(email, password)

    }

}