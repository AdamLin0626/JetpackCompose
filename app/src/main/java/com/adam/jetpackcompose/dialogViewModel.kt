package com.adam.jetpackcompose

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DialogViewModel: ViewModel( ) {

    private val _showDialog = MutableStateFlow(false)
    private val _showEdit = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> get() = _showDialog
    val showEdit: StateFlow<Boolean> get() = _showEdit


    // inputBox State
    private val _newName = MutableStateFlow("")
    private val _newPhone = MutableStateFlow("")
    val newName: StateFlow<String> = _newName
    val newPhone: StateFlow<String> = _newPhone

    fun toggleConfirmDialog(show: Boolean) {
        _showDialog.value = show
    }

    fun toggleEditDialog(show: Boolean) {
        _showEdit.value = show
    }

    //輸入
    fun newNameChange(text: String){
        _newName.value = text
    }
    fun newPhoneChange(text: String){
        _newPhone.value = text
    }
}