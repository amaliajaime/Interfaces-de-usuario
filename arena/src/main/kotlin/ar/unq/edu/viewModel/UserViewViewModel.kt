package ar.unq.edu

import org.unq.ui.model.DraftPost
import org.unq.ui.model.InstagramSystem
import org.uqbar.commons.model.annotations.Observable

@Observable
class UserViewViewModel(userModel: UserModel, system: InstagramSystem) {

    var user = userModel
    val system = system
    var keyText = ""
    var selected: PostModel? = null
    lateinit var viewPosts: List<PostModel>

    init {
        updatePosts()
    }

    fun searcher() {
            val post = system.getPost(keyText)
            if (post.user.id == user.id) {
                val foundPost = PostModel(post.id, post.landscape, post.portrait, post.description)
                viewPosts = mutableListOf(foundPost)
            }
            else {
                viewPosts = mutableListOf()
            }
    }

    fun editPost(id: String, post: DraftPostModel) {
        system.editPost(id, DraftPost(post.portrait, post.landscape, post.description))
        updatePosts()
    }

    fun addPost(post: DraftPostModel) {
        system.addPost(user.id, DraftPost(post.portrait, post.landscape, post.description))
        updatePosts()
    }


    fun removePost(post: PostModel) {
        system.deletePost(post.id)
        updatePosts()
    }

    fun editProfile(id: String, name: String, password: String, image: String) {
        system.editProfile(id, name, password, image)


    }

    fun updatePosts(){
        viewPosts = system.searchByUserId(user.id).map { PostModel(it.id, it.landscape, it.portrait, it.description) }
    }

    fun updateUser(updatedUser: UserModel) {
        user = updatedUser
    }

}
