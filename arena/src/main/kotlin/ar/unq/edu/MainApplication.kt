package ar.unq.edu

import org.unq.ui.bootstrap.getInstagramSystem
import org.unq.ui.model.DraftPost
import org.uqbar.arena.Application
import org.uqbar.arena.windows.Window


fun main(){
    MainApplication().start()
}

class MainApplication() : Application() {

    override fun createMainWindow(): Window<*> {
        val system = getInstagramSystem()
        val user = system.register("Ivan1998", "ivan@gmail.com", "ivan1234", "https://pix.example/2.png")

        system.addPost(user.id, DraftPost("https://imgageLink.com/portrait1.png", "https://imgageLink.com/landscape1.png", "description1"))
        system.addPost(user.id, DraftPost("https://imgageLink.com/portrait2.png", "https://imgageLink.com/landscape2.png", "description2"))
        system.addPost(user.id, DraftPost("https://imgageLink.com/portrait3.png", "https://imgageLink.com/landscape3.png", "description3"))
        val login = LogInModel(system)

        return LogInView(this, login)
    }

}