package com.example.lekarzer_v_0_0_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Toast
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.jsonBody
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        this.supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener{
            //loginAndCheckResponse(createLoginJson())
            goToMainScreen()
        }
    }

    fun createLoginJson() : JSONObject{
        val jsonToSend = JSONObject()
        jsonToSend.put("login", etLogin.text.toString())
        jsonToSend.put("password", etPassword.text.toString())
        Log.d("LoginScreen", jsonToSend.getString("login"))
        Log.d("LoginScreen", jsonToSend.getString("password"))
        return jsonToSend
    }

    fun loginAndCheckResponse(jsonToSend: JSONObject) : String? {

        val (request, response, result) = Fuel.post("http://10.0.2.2:5000")
            .body(jsonToSend.toString())
            .responseString()
        val (payload, error) = result

        if (payload!="OK"){
            Toast.makeText(this, "Invalid username or password.", Toast.LENGTH_LONG).show()
            etPassword.setText("")
        }

        println(payload)
        return payload

    }

    fun goToMainScreen(){
        Intent (this, MainActivity::class.java).also {
            startActivity(it)
        }
    }

}