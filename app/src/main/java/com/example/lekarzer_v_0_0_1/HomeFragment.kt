package com.example.lekarzer_v_0_0_1

import android.app.Person
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.runBlocking
import org.json.JSONObject


class HomeFragment : Fragment(R.layout.fragment_home) {

    var researcher = Researcher(0, "", "", 0, "", "", "", "", "")

    override fun onStart() {

        super.onStart()
        researcher = Gson().fromJson(askForPerson(), Researcher::class.java)
        updateInfoOnScreen(researcher)

        buttonYES.setOnClickListener {
            val respond :String
            runBlocking {respond = swipe(researcher, "True")}
            Log.e("respond yes", respond)
            var person : String
            runBlocking {person = askForPerson()}
            researcher = Gson().fromJson(person, Researcher::class.java)
            runBlocking {updateInfoOnScreen(researcher)}
        }


        buttonNO.setOnClickListener {
            val respond :String
            runBlocking {respond = swipe(researcher, "False")}
            Log.e("respond yes", respond)
            var person : String
            runBlocking {person = askForPerson()}
            researcher = Gson().fromJson(person, Researcher::class.java)
            runBlocking {updateInfoOnScreen(researcher)}
        }

    }


    fun swipe(researcher: Researcher, bool: String): String {
        val jsonObject = JSONObject()
        jsonObject.put("token", Global.global_token)
        jsonObject.put("source", "app")
        jsonObject.put("function", "liked")
        jsonObject.put("user", researcher.id)
        jsonObject.put("swipe", bool)
        var response: String
        runBlocking {
            response = Poster.postExample(jsonObject)
        }
        return response
    }

    fun updateInfoOnScreen(researcher: Researcher) {
        textViewAge.text = researcher.age.toString()
        textViewName.text = researcher.name
        textViewSurname.text = researcher.last_name
        researcher.university=researcher.university
        textViewUni.text = researcher.university
        textViewStatus.text = researcher.status
        textViewHobby1.text = researcher.interest1
        textViewHobby2.text = researcher.interest2
        textViewHobby3.text = researcher.interest3
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


