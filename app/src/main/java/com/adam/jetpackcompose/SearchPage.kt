package com.adam.jetpackcompose

import android.os.Bundle
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
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
fun MainLayout( ){
    ConstraintLayout(
        modifier = Modifier.fillMaxSize( )
    ) {
        val (searchBar, dataView) = createRefs()
        SearchBar(
            modifier = Modifier.constrainAs(searchBar) {
                top.linkTo(parent.top)
            }
        )
        UserView(
            modifier = Modifier.constrainAs(dataView) {
                top.linkTo(searchBar.bottom)
                bottom.linkTo(parent.bottom)
                height = Dimension.fillToConstraints
            }
        )

    }
}

//searchBarUI
@Composable
fun SearchBar(modifier: Modifier = Modifier){
    var searchText by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxWidth( )
            .background(White),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth( )
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
            onValueChange = {searchText=it},
            label = {Text("Search")},
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            trailingIcon = {
                if (searchText.isNotEmpty())
                    Icon(Icons.Default.Clear, contentDescription = "Clear")
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
fun UserView(modifier: Modifier = Modifier){
    LazyColumn(
        modifier = modifier
            .fillMaxWidth( ),
        verticalArrangement = Arrangement.spacedBy(1.dp),
    ) {
        items(20){
            horizontalLayout(name = "Admin")
        }
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
