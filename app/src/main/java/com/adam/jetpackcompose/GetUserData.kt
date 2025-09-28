package com.adam.jetpackcompose

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import java.io.File

class GetUserData(private val context: Context) {
    private val gson = Gson( )
    private val fileName = "fakeUsers.json"

    private fun getFile( ): File {
        return File(context.filesDir, fileName)
    }
    fun loadUsers( ): UserList {
        val file = getFile( )
        return if (file.exists( )) {
            val jsonString = file.readText( )
            gson.fromJson(jsonString, UserList::class.java)
        } else {
            UserList(mutableListOf( ))
        }
    }

    fun saveUsers(userList: UserList) {
        val file = getFile( )
        val jsonString = gson.toJson(userList)
        file.writeText(jsonString)
    }

    fun updateUser(
        id: Int,
        newName: String,
        newPhone: String
    ) {
        val userList = loadUsers( )
        userList.users.find { it.id == id }?.name = newName
        userList.users.find { it.id == id }?.phone = newPhone
        saveUsers(userList)
        Log.d(
            "JsonUpdate",
            "updateUser: no.$id setName $newName setPhone $newPhone")
    }
}