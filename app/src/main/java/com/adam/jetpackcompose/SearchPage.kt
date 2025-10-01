package com.adam.jetpackcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adam.jetpackcompose.ui.theme.Black
import com.adam.jetpackcompose.ui.theme.JetpackComposeTheme
import com.adam.jetpackcompose.ui.theme.LightGray
import com.adam.jetpackcompose.ui.theme.MainColor
import com.adam.jetpackcompose.ui.theme.White


class SearchPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                Surface (
                    modifier = Modifier
                        .fillMaxSize( ),
                    color = MaterialTheme.colorScheme.background
                ){
                    MainLayout( )
                }
            }
        }
    }
}

//主Layout UI
@Composable
fun MainLayout(
    viewModel: SearchViewModel = viewModel( )
){
    val searchText by viewModel.searchText.collectAsState( )

    val showConfirm = viewModel.showDialog.collectAsState()
    val showEdit = viewModel.showEdit.collectAsState()
    ConstraintLayout(
        modifier = Modifier.fillMaxSize( )
    ) {
        val (searchBar, dataView) = createRefs()
        mySearchBar(
            modifier = Modifier.constrainAs(searchBar) {
                top.linkTo(parent.top)
            }
        )
        UserView(
            searchText = searchText,
            modifier = Modifier.constrainAs(dataView) {
                top.linkTo(searchBar.bottom)
                bottom.linkTo(parent.bottom)
                height = Dimension.fillToConstraints
            }
        )
    }
    if (showEdit.value){
        Dialog(
            onDismissRequest = { viewModel.toggleEditDialog(false) },
        ) {
            editDialog(
                title = "更改資料",
                onCancel = { viewModel.toggleEditDialog(false) },
                onConfirm = { a, b ->
                    Log.i("DialogInput",a)
                    Log.i("DialogInput", b)
                }
            )
        }
    }
    if (showConfirm.value){
        Dialog(
            onDismissRequest = { viewModel.toggleConfirmDialog(false) },
        ) {
            confirmDialog(
                "確定刪除嗎?",
                { viewModel.toggleConfirmDialog(false) },
                { viewModel.toggleConfirmDialog(false)}
            )
        }
    }
}

//searchBarUI
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun mySearchBar(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = viewModel( )
    ){
    val searchText by viewModel.searchText.collectAsState( )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(White),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .background(White)
                .padding(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MainColor,
                unfocusedLabelColor = LightGray,
                cursorColor = Black
            ),
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            value = searchText,
            onValueChange = {
                viewModel.searchBarTextChange(it)
                viewModel.searchUser(it)
                            },
            label = { Text("Search") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            trailingIcon = {
                if (searchText.isNotEmpty())
                    IconButton(
                        onClick = { viewModel.searchBarTextDelete() }
                    ) {
                        Icon(Icons.Default.Clear, contentDescription = "Clear")
                    }
            },
        )
        Box(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(color = LightGray)
        )
    }
}

//userView
@Composable
fun UserView(
    searchText: String,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = viewModel( )
){
    val users by viewModel.userList.collectAsState(initial = emptyList())
    LazyColumn(
        modifier = modifier
            .fillMaxWidth( ),
        verticalArrangement = Arrangement.spacedBy(1.dp),
    ) {
        items(users.size) { index ->
            horizontalLayout(name = users[index].name)
        }
//        if (searchText == "") {
//            // Search Bar 為空
//            items(users.size) { index ->
//                horizontalLayout(name = users[index].name)
//            }
//        }else{
//            items(1) {
//                horizontalLayout(name = searchText)
//            }
//        }
    }
}

//測試UI
@Preview(showBackground = true)
@Composable
fun Preview( ) {
    JetpackComposeTheme {
        Surface (
            modifier = Modifier
                .fillMaxSize( ),
            color = MaterialTheme.colorScheme.background
        ){
            MainLayout( )
        }
    }
}
