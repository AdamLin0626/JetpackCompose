package com.adam.jetpackcompose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.adam.jetpackcompose.ui.theme.ErrorRed
import com.adam.jetpackcompose.ui.theme.Invisible
import com.adam.jetpackcompose.ui.theme.LightBackgroundColor
import com.adam.jetpackcompose.ui.theme.LightGray
import com.adam.jetpackcompose.ui.theme.MainColor
import com.adam.jetpackcompose.ui.theme.White
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun horizontalLayout(name: String){
    Row(
        modifier = Modifier
            .fillMaxWidth( )
            .height(100.dp)
            .padding(5.dp)
            .background(color = White, shape = RoundedCornerShape(8.dp)),
        verticalAlignment = Alignment.CenterVertically
    ){
        Box(modifier = Modifier.width(10.dp))
        iconFunction(
            Icons.Default.Person,
            "Person",
            color = MainColor,
            backgroundColor = LightBackgroundColor,
        )
        Box(modifier = Modifier.width(10.dp))
        Text(text = name, modifier = Modifier.wrapContentWidth( ), fontSize = 20.sp)
        Box(modifier = Modifier.weight(1f))
        iconFunction(
            Icons.Default.Edit,
            "Edit",
            color = LightGray,
            backgroundColor = Invisible,
        )
        Box(modifier = Modifier.width(2.dp))
        iconFunction(
            Icons.Default.Delete,
            "Delete",
            color = ErrorRed,
            backgroundColor = Invisible,
        )
        Box(modifier = Modifier.width(10.dp))
    }
}

@Composable
fun iconFunction(
    icon: ImageVector,
    description: String,
    color: Color,
    backgroundColor: Color,
    viewModel: SearchViewModel = viewModel( )
){

    IconButton (
        modifier = Modifier
            .size(55.dp)
            .padding(2.dp)
            .background(
                color = backgroundColor ,
                shape = RoundedCornerShape(8.dp),
                ),
        onClick = {
            when (icon) {
                Icons.Default.Edit -> {
                    Log.i("onClick", "Edit")
                    viewModel.toggleEditDialog(true)
                }
                Icons.Default.Delete -> {
                    viewModel.toggleConfirmDialog(true)
                }
                else -> {
                    Log.i("onClick", "Person")
                }
            }
        },
        ){
        Icon(icon, modifier = Modifier.size(30.dp), contentDescription = description, tint = color)
    }

}

//測試區
@Preview(showBackground = true)
@Composable
fun CardPreview() {
    horizontalLayout(name = "Admin")
}