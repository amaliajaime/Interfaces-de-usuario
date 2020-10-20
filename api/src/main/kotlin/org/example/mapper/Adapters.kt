package org.example.mapper

data class RegisterDTO(val name :String, val email :String, val password :String, val image :String)

data class LoginDTO(val email :String, val password: String )

data class MyResponse(val result: String)

data class UserNotFound(val result :String, val message :String)

data class UserDTO(val name :String, val image :String, var followers :List<FollowerDTO>, var timeline :List<PostDTO>)

data class FollowerDTO(val name: String, val image: String)

data class PostDTO(val id :String,
                   val description :String,
                   val portrait :String,
                   val landscape :String,
                   val likes :List<FollowerDTO>,
                   val date :String,
                   val user : FollowerDTO,
                   val comments: List<CommentsDTO>)

data class CommentsDTO(val id :String, val body :String, val user : FollowerDTO)

data class ProfileDTO(val name :String, val image :String, var followers :List<FollowerDTO>, val posts :List<PostDTO>)

data class NotFoundProfile(val result :String)

data class Content(val content :List<Result>)

abstract class Result

data class TagDTO(val id: String,
                  val description :String,
                  val portrait :String,
                  val landscape :String,
                  val likes :List<FollowerDTO>,
                  val date :String,
                  val user : FollowerDTO,
) : Result()

data class SearchUserDTO(val name :String, val image :String, val followers :List<FollowerDTO>) : Result()
