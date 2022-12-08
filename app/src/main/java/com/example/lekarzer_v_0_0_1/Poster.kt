package com.example.lekarzer_v_0_0_1

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Retrofit

object Poster {

//    public fun postExample(jsonObject: JSONObject): String {
//
//        var responseToReturn = "?"
//
//        // Create Retrofit
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://piwim-server-hqm5ttv7nq-lm.a.run.app")
//            .build()
//
//        // Create Service
//        val service = retrofit.create(APIService::class.java)
//
//        // Convert JSONObject to String
//        val jsonObjectString = jsonObject.toString()
//
//        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
//        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
//        val coroutine = CoroutineScope(Dispatchers.IO).launch {
//            // Do the POST request and get response
//            val response = service.createEmployee(requestBody)
//
//            withContext(Dispatchers.Main) {
//                if (response.isSuccessful) {
//
//                    // Convert raw JSON to pretty JSON using GSON library
//                    val gson = GsonBuilder().setPrettyPrinting().create()
//                    val prettyJson = gson.toJson(
//                        JsonParser.parseString(
//                            response.body()
//                                ?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
//                        )
//                    )
//                    Log.e("Pretty Printed JSON :", prettyJson)
//                    responseToReturn = prettyJson
//                    Log.e("Pretty Printed JSON :", responseToReturn)
//
//                } else {
//
//                    Log.e("RETROFIT_ERROR", response.code().toString())
//                    responseToReturn = "connection_error"
//                    Log.e("Pretty Printed JSON :", responseToReturn)
//
//                }
//            }
//        }
//        return responseToReturn
//    }

    //192.168.97.76:5000
    //https://piwim-server-hqm5ttv7nq-lm.a.run.app
    suspend fun postExample(jsonObject: JSONObject): String = withContext(Dispatchers.IO) {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://piwim-server-hqm5ttv7nq-lm.a.run.app")
            .build()
        val service = retrofit.create(APIService::class.java)
        val jsonObjectString = jsonObject.toString()
        Log.e("Wysyłany Request", jsonObjectString)
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
        val response = service.createEmployee(requestBody)
        if (response.isSuccessful) {
            val gson = GsonBuilder().setPrettyPrinting().create()
            var prettyJson = gson.toJson(
                JsonParser.parseString(
                    response.body()
                        ?.string()
                )
            )
            prettyJson = prettyJson.replace("\\s".toRegex(), "").trim();
            Log.e("Otrzymana odpowiedz", prettyJson)
            return@withContext prettyJson
        } else {
            return@withContext "connection_error"
        }
    }
}

//{"name":"a","last_name":"b","age":"22","university":"piwiriri"}

