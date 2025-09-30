package com.adam.jetpackcompose

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class dialogViewModel: ViewModel( ) {

    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()

    // inputBox State
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

    fun showDialog() {
        _showDialog.value = true
    }

    fun hideDialog() {
        _showDialog.value = false
    }
}