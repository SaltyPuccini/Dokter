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
import com.google.gson.Gson

import kotlinx.coroutines.*
import java.io.Serializable


class LoginActivity : AppCompatActivity() {

    data class Token(val token: String): Serializable

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

    fun goToMainScreen(token_json: String) {
        Intent(this, MainActivity::class.java).also {
            val token = Gson().fromJson(token_json, Token::class.java)
            Global.global_token = token.token
            startActivity(it)
        }
    }
}