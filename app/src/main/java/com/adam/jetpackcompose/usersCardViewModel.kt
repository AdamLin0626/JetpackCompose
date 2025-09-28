package com.adam.jetpackcompose

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DialogViewModel : ViewModel() {
    // 是否顯示 Dialog
    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog

    fun openDialog(isEdit: Boolean) {
        _showDialog.value = true
    }

    fun closeDialog(isEdit: Boolean) {
        _showDialog.value = false
    }
}
