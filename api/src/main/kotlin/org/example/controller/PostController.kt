package org.example.controller

import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import org.example.mapper.FollowerDTO
import org.example.mapper.MyResponse
import org.example.mapper.PostDTO
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.*

class PostController(val instagramSystem: InstagramSystem) : InstagramController(){

    fun getUserId(ctx :Context) :String{
        return ctx.attribute<String>("userId") ?: throw BadRequestResponse("Not found user")
    }

    fun getPost(ctx : Context){
        val postId = ctx.pathParam("id")
        try{
            val post = instagramSystem.getPost(postId)
            val likes = generateFollowersDTO(post.likes)
            val comments = generateCommentsDTO(post.comments)
            ctx.json(
                PostDTO(
                    post.id,
                    post.description,
                    post.portrait,
                    post.landscape,
                    likes,
                    post.date.toString(),
                    FollowerDTO(post.user.name, post.user.image),
                    comments
                )
            )
        }
        catch (e: NotFound){
            ctx.status(404)
            ctx.json(MyResponse("Not found post with id ${postId}"))
        }
    }

    fun like(ctx :Context){
        val idPostToLike = ctx.pathParam("id")
        val userId = getUserId(ctx)
        try {
            instagramSystem.updateLike(idPostToLike, userId)
            ctx.json(MyResponse("ok"))
        }
        catch (e: NotFound){
            ctx.status(404)
            ctx.json(MyResponse("Not found post with id ${idPostToLike}"))
        }

    }

    fun comment(ctx :Context){
        val postId = ctx.pathParam("id")
        val newComment = ctx.body<DraftComment>()
        val userId = getUserId(ctx)
        try {
            instagramSystem.addComment(postId, userId, newComment)
            ctx.json(MyResponse("ok"))
        }
        catch (e :NotFound){
            ctx.status(404)
            ctx.json(MyResponse("Not found post with id ${postId}"))
        }

    }

}
