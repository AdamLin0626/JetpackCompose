package com.adam.jetpackcompose

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class dialogViewModel: ViewModel( ) {
    private val _newName = MutableStateFlow("")
    private val _newPhone = MutableStateFlow("")
    val newName: StateFlow<String> = _newName
    val newPhone: StateFlow<String> = _newPhone

    //輸入
    fun newNameChange(text: String){
        _newName.value = text
    }
    fun newPhoneChange(text: String){
        _newPhone.value = text
    }

}