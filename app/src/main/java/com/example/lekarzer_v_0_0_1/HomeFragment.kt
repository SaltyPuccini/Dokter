package com.example.lekarzer_v_0_0_1

import android.content.Intent
import android.content.Intent.getIntent
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.runBlocking
import org.json.JSONObject

class HomeFragment : Fragment(R.layout.fragment_home) {
    override fun onStart() {

        super.onStart()



        buttonYES.setOnClickListener {
            val person = Gson().fromJson(askForPerson(), Researcher::class.java)
        }

    }

    fun askForPerson(): String {
        val jsonObject = JSONObject()
        jsonObject.put("token", Global.global_token)
        jsonObject.put("source", "app")
        jsonObject.put("function", "next_person")

        var response = ""
        runBlocking {
            response = Poster.postExample(jsonObject)
        }
        return response
    }



}


