package com.adam.jetpackcompose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adam.jetpackcompose.ui.theme.Black
import com.adam.jetpackcompose.ui.theme.ErrorRed
import com.adam.jetpackcompose.ui.theme.LightBackgroundColor
import com.adam.jetpackcompose.ui.theme.LightGray
import com.adam.jetpackcompose.ui.theme.MainColor

@Composable
fun confirmDialog(
    title: String,
    onCancel: () -> Unit,
    onConfirm:() -> Unit
){
    Dialog(onDismissRequest = onCancel) {
        Column (
            modifier = Modifier
                .width(380.dp)
                .background(
                    color = LightBackgroundColor,
                    shape = RoundedCornerShape(20.dp) // rectangle_button 的效果
                )
                .padding(0.dp)
        ){
            Text(
                text = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 14.dp, bottom = 10.dp),
                color = MainColor,
                fontSize = 28.sp,
                textAlign = TextAlign.Left
            )
            Box(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(color = LightGray)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ){
                buttonFunction(
                    text = "取消",
                    modifier = Modifier.weight(1f),
                    textColor = MainColor,
                    onClick = onCancel
                )
                buttonFunction(
                    text = "確認",
                    modifier = Modifier.weight(1f),
                    textColor = ErrorRed,
                    onClick = {onConfirm( )}
                )
            }
        }
    }
}

/**editDialog是專門用來新增資料
 * title: 標題
 */
@Composable
fun editDialog(
    title: String,
    onCancel: () -> Unit,
    onConfirm: (stringOne: String, stringTwo: String) -> Unit,
){
// Column作為大容器
    Column (
        modifier = Modifier
            .width(380.dp)
            .background(
                color = LightBackgroundColor,
                shape = RoundedCornerShape(20.dp) // rectangle_button 的效果
            )
            .padding(0.dp)
    ){
        Text(
            text = title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 5.dp, bottom = 5.dp),
            color = MainColor,
            fontSize = 28.sp,
            textAlign = TextAlign.Left
        )
        Box(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(color = LightGray)
        )
        // 內容
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ){
            inputBox(type = "姓名")
            inputBox(type = "電話")
        }
        Box(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(color = LightGray)
        ) {
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ){
            buttonFunction(
                text = "取消",
                modifier = Modifier.weight(1f),
                textColor = MainColor,
                onClick = onCancel
            )
            buttonFunction(
                text = "確認",
                modifier = Modifier.weight(1f),
                textColor = ErrorRed,
                onClick = {
                    onConfirm("123", "456")
                }
            )
        }
    }
}

@Composable
fun inputBox(
    type: String,
    viewModel: DialogViewModel = viewModel( )
){
    val newName = viewModel.newName.collectAsState( )
    val newPhone = viewModel.newPhone.collectAsState( )

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = MainColor,
            unfocusedLabelColor = LightGray,
            cursorColor = Black
        ),
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        value = if (type == "姓名") newName.value else newPhone.value,
        leadingIcon = {
            if (type == "姓名") {
                Icon(Icons.Default.Person, contentDescription = "Name")
            } else {
                Icon(Icons.Default.Phone, contentDescription = "Phone")
            }
        },
        onValueChange = {
            if (type == "姓名") viewModel.newNameChange(it) else viewModel.newPhoneChange(it)
        },
        label = { Text(text = type) }
    )
}

@Composable
fun buttonFunction(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color,
    onClick: () -> Unit
){
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Black
        )
    ) {
        Text(text, color = textColor, fontSize = 24.sp)
    }
}

//測試區
@Preview
@Composable
fun editDialogPreview( ){
    editDialog(
        title = "新增",
        onCancel = { },
        onConfirm = { a, b ->
            println(a)
            println(b)
        }
    )
}

@Preview
@Composable
fun confirmDialogPreview( ){
    confirmDialog(
        title = "確定要刪除嗎?",
        onCancel = { },
        onConfirm = { Log.e("UiTest", "confirmDialog:onConfirm is being called") }
    )
}
