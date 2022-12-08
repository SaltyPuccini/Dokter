package com.example.lekarzer_v_0_0_1

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*


interface APIService {
    @POST("/")
    suspend fun createEmployee(@Body requestBody: RequestBody): Response<ResponseBody>
}