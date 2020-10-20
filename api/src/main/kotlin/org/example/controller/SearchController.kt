package org.example.controller

import io.javalin.http.Context
import org.example.mapper.Content
import org.unq.ui.model.InstagramSystem

class SearchController(val instagramSystem: InstagramSystem) : InstagramController(){

    fun search(ctx : Context){
        val text = ctx.queryParam("q")
        if (text!!.startsWith('#')) {
            val resultTag = generateContentTag(instagramSystem.searchByTag(text))
            ctx.json(Content(resultTag))
        }
        else {
            val resultUser = generateContentUsers(instagramSystem.searchByName(text))
            ctx.json(Content(resultUser))
        }
    }
}
