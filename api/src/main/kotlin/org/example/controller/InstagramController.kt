package org.example.controller

import org.example.mapper.*
import org.unq.ui.model.Comment
import org.unq.ui.model.Post
import org.unq.ui.model.User

open class InstagramController () {

    fun generateContentTag(tag :List<Post>) :List<TagDTO>{
        return tag.map{
            TagDTO(
                it.id,
                it.description,
                it.portrait,
                it.landscape,
                generateFollowersDTO(it.likes),
                it.date.toString(),
                FollowerDTO(it.user.name, it.user.image)
            )
        }
    }

    fun generateFollowersDTO(followers :List<User>): List<FollowerDTO>{
        return followers.map { FollowerDTO(it.name, it.image) }
    }

    fun generateContentUsers(users :List<User>) :List<SearchUserDTO> {
        return users.map {
            SearchUserDTO(
                it.name,
                it.image,
                generateFollowersDTO(it.followers)
            )
        }
    }

    fun generateCommentsDTO(comments :List<Comment>) :List<CommentsDTO>{
        return comments.map {
            CommentsDTO(
                it.id,
                it.body,
                FollowerDTO(it.user.name, it.user.image)
            )
        }
    }

    fun generatePostDTO(posts :List<Post>) :List<PostDTO> {
        return posts.map {
            PostDTO(
                it.id,
                it.description,
                it.portrait,
                it.landscape,
                generateFollowersDTO(it.likes),
                it.date.toString(),
                FollowerDTO(it.user.name, it.user.image),
                generateCommentsDTO(it.comments),
            )
        }
    }
}