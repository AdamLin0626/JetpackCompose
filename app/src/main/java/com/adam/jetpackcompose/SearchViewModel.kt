package com.adam.jetpackcompose

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class SearchViewModel(
    application:Application
): AndroidViewModel(application) {

    private val context = application.applicationContext

    //dialog
    private val _showDialog = MutableStateFlow(false)
    private val _showEdit = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> get() = _showDialog
    val showEdit: StateFlow<Boolean> get() = _showEdit

    //搜索文字
    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText

    //User 清單
    private val _userList = MutableStateFlow<List<UserList>>(emptyList())
    val userList: StateFlow<List<UserList>> = _userList

    fun searchBarTextChange(text: String){
        _searchText.value = text
    }

    fun searchBarTextDelete( ){
        _searchText.value = ""
    }

    //是否開啟Dialog
    fun toggleConfirmDialog(show: Boolean) {
        _showDialog.value = show
    }

    fun toggleEditDialog(show: Boolean) {
        _showEdit.value = show
    }


// 在 ViewModel 初始化時，異步在執行緒中讀取數據
    init {
        loadUser( )
    }

    private fun loadUser() {
        viewModelScope.launch(Dispatchers.IO) {
            getJsonData(context).copyJson()
            val jsonDataManager = getJsonData(context)
            val list = jsonDataManager.getUsersFromJsonFile()

            // 更新 StateFlow，Compose View 將會自動收到這個更新值

            if (_searchText.value != "") {
                _userList.value = jsonDataManager.searchUserByName(_searchText.value)
            }else _userList.value = list

        }
    }

    fun searchUser(keyword: String) {
        val list = getJsonData(context).searchUserByName(keyword)   // 用你剛寫的 function
        _userList.value = list
    }

}