package ar.unq.edu

import org.uqbar.commons.model.annotations.Observable

@Observable
class UserModel(id: String, name :String, email :String, password :String, image :String) {

    val id = id
    var name = name
    var email = email
    var password = password
    var image = image

}
