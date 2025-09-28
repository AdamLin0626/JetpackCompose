package com.adam.jetpackcompose

data class User(
    val id: Int,
    var name: String,
    var phone: String = ""
)
data class UserList(
    var users: MutableList<User>
)
