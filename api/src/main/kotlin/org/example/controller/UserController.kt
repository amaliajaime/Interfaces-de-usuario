package org.example.controller

import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import org.example.mapper.*
import org.example.token.TokenController
import org.unq.ui.model.*

class UserController(val system: InstagramSystem) : InstagramController(){

    val tokenController = TokenController()

    fun getUserId(ctx :Context) :String{
        return ctx.attribute<String>("userId") ?: throw BadRequestResponse("Not found user")
    }

    fun register(ctx : Context) {
        val newRegister = ctx.body<RegisterDTO>()
        try {
            val userRegister = system.register(newRegister.name,newRegister.email,newRegister.password,newRegister.image)
            ctx.header("Authorization", tokenController.generateToken(userRegister))
            ctx.status(201)
            ctx.json(MyResponse("ok"))
        }
        catch (e: UsedEmail){
            throw BadRequestResponse(e.message!!)
        }

    }

    fun login(ctx :Context){
        val userLogin = ctx.body<LoginDTO>()
        try {
            val user = system.login(userLogin.email, userLogin.password)
            ctx.header("Authorization", tokenController.generateToken(user))
            ctx.json(MyResponse("ok"))
        }
        catch (e: NotFound){
            ctx.status(404)
            ctx.json(UserNotFound("error", e.message!!))
        }
    }

    fun getUserLogin(ctx: Context){
        val userId = getUserId(ctx)
        try{
            val user = system.getUser(userId)
            val timeline = generatePostDTO(system.timeline(userId))
            val followers2 = generateFollowersDTO(user.followers)
            ctx.json(UserDTO(user.name, user.image, followers2, timeline))
        }
        catch (e: NotFound){
            throw BadRequestResponse(e.message!!)
        }
    }

    fun getUser(ctx: Context){
        val userID = ctx.pathParam("id")
        try {
            val user = system.getUser(userID)
            val posts = generatePostDTO(system.searchByUserId(userID))
            val followers = generateFollowersDTO(user.followers)
            ctx.json(ProfileDTO(user.name, user.image, followers, posts))
        }
        catch (e: NotFound){
            ctx.status(404)
            ctx.json(NotFoundProfile("Not found user with id ${userID}"))
        }
    }

    fun follow(ctx :Context){
        val idUserFollower = getUserId(ctx)
        val idUserToFollow = ctx.pathParam("id")
        try {
            system.updateFollower(idUserToFollow, idUserFollower)
            ctx.json(MyResponse("ok"))
        }
        catch (e: NotFound){
            ctx.status(404)
            ctx.json(NotFoundProfile("Not found user with id ${idUserToFollow}"))
        }
    }
}
