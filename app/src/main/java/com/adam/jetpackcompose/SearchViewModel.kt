package com.adam.jetpackcompose

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SearchViewModel: ViewModel( ) {
    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText

    fun searchBarTextChange(text: String){
        _searchText.value = text
    }

    fun searchBarTextDelete( ){
        _searchText.value = ""
    }
}