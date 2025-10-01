package com.adam.jetpackcompose

import android.content.Context
import android.util.Log
import kotlinx.serialization.json.Json
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class getJsonData(private val context: Context) {
    companion object {
        private const val JSON_NAME = "fakeUsers.json"
    }
    fun copyJson() {
        val targetFile = File(context.filesDir, JSON_NAME)

        // 避免重複複製，可以先檢查檔案是否存在
        if (targetFile.exists()) {
            Log.i("jsonProject", "Json File already exists at: ${targetFile.absolutePath}")
            return
        }

        try {
            context.assets.open(JSON_NAME).use { input ->
                FileOutputStream(targetFile).use { output ->
                    input.copyTo(output)
                }
            }
            Log.i("jsonProject", "Copy Json Success to filesDir: ${targetFile.absolutePath}")

        } catch (e: IOException) {
            // 捕獲檔案找不到、讀寫失敗等錯誤
            Log.e("jsonProject", "Copy Json Error: ${e.message}. Target path: ${targetFile.absolutePath}")
        } catch (e: Exception) {
            Log.e("jsonProject", "An unknown error occurred: ${e.message}")
        }
    }
//    fun getIntFormJson(): Int {
//        val jsonFile = File(context.filesDir, JSON_NAME)
//
//        if (!jsonFile.exists()) {
//            Log.d("jsonProject", "Error: File ${jsonFile.absolutePath}  not Exist")
//            return 0
//        }
//        try {
//            val jsonString: String = jsonFile.readText(Charsets.UTF_8)
//            val json = Json
//            val userList: List<UserList> = json.decodeFromString(jsonString)
//            val itemCount = userList.size
//
//            Log.d("jsonProject", "${userList} Find ${itemCount} Data.")
////            Log.d("jsonProject", "First User: ID = ${userList.first().id}, Name = ${userList.first().name}")    //測試用
//
//            return itemCount
//
//        }catch (e: IOException) {
//            Log.d("jsonProject", "Error reading JSON file: ${e.message}")
//            return 0
//        } catch (e: Exception) {
//            Log.d("jsonProject", "JSON Read Problem: ${e.message}")
//            return 0
//        }
//    }

    fun getUsersFromJsonFile(): List<UserList> {
        val jsonFile = File(context.filesDir, JSON_NAME)

        if (!jsonFile.exists()) {
            Log.d("jsonProject", "Error: File ${jsonFile.absolutePath} not Exist")
            // 檔案不存在時，回傳空列表而不是 "ERROR" 字串
            return emptyList()
        }

        try {
            val jsonString: String = jsonFile.readText(Charsets.UTF_8)
            val json = Json

            // 核心：將 JSON 字串解析成 List<UserList>
            val userList: List<UserList> = json.decodeFromString(jsonString)
            val itemCount = userList.size

            Log.d("jsonProject", "${userList} Find ${itemCount} Data.")

            // 成功時回傳整個列表
            return userList

        } catch (e: IOException) {
            Log.e("jsonProject", "Error reading JSON file: ${e.message}")
            return emptyList() // 讀取錯誤時回傳空列表
        } catch (e: Exception) {
            Log.e("jsonProject", "JSON Decode Problem: ${e.message}")
            return emptyList() // 解析錯誤時回傳空列表
        }
    }

    fun searchUserByName(keyword: String): List<UserList> {
        val users = getUsersFromJsonFile()
        Log.d("jsonProject", "Search Keyword: $keyword")
        Log.d("jsonProject", "Search Result: ${users.filter { user -> user.name.contains("T", ignoreCase = true) }}")

        // 忽略大小寫搜尋 name，並回傳符合條件的使用者清單
        return users.filter { user ->
            user.name.contains(keyword, ignoreCase = true)
        }
    }

}

//fun main() {
// // ⚠️ 實際使用時，請將此處替換為您應用程式中 fileDir 的實際路徑
// // 例如在 Android 中可能是 context.filesDir
// val actualFileDir = File("C:\\Users\\User\\Desktop\\twJoin\\JetpackCompose\\app\\src\\main\\java\\fileDir")
// val count = readAndParseUsersFile(actualFileDir)
// println("Final Count: $count")
//}