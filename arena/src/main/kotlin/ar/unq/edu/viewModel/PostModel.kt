package ar.unq.edu

import org.uqbar.commons.model.annotations.Observable

@Observable
class PostModel (anId: String, aLandscape: String, aPortrait: String, aDescripcion: String){
    val id = anId
    var landscape = aLandscape
    var portrait = aPortrait
    var description = aDescripcion

}
