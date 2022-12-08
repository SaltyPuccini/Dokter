package com.example.lekarzer_v_0_0_1


import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import android.view.Gravity

import org.json.JSONObject

import android.widget.Toast

import kotlinx.coroutines.*


class LoginActivity : AppCompatActivity() {

    @SuppressLint("RtlHardcoded")
    override fun onCreate(savedInstanceState: Bundle?) {
        this.supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {

            val jsonObject = JSONObject()
            jsonObject.put("source", "app")
            jsonObject.put("function", "login")
            jsonObject.put("login", etLogin.text.toString())
            jsonObject.put("password", etPassword.text.toString())

            var response = ""
            runBlocking {
                response = Poster.postExample(jsonObject)
            }

            Log.e("response", response)

            if (response == "connection_error") {
                val toast = Toast.makeText(this, "We are sorry, server is currently down.", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.LEFT,0,200)
                toast.show()
            } else if (!response.contains("error")) {
                goToMainScreen(response)
            } else {
                val toast = Toast.makeText(this, "Incorrect login or password. Try again.", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.LEFT,0,200)
                toast.show()
            }
            etPassword.setText("")
        }
    }

    fun goToMainScreen(token: String) {
        Intent(this, MainActivity::class.java).also {
            Global.global_token = token
            Log.e("token", Global.global_token)
            startActivity(it)
        }
    }
}