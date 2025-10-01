package com.adam.jetpackcompose

import android.provider.ContactsContract.CommonDataKinds.Phone
import kotlinx.serialization.Serializable

@Serializable
data class UserList(
    val id: Int,
    var name: String,
)
