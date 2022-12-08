package com.example.lekarzer_v_0_0_1

import androidx.fragment.app.Fragment
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_config.*
import kotlinx.coroutines.runBlocking
import org.json.JSONObject

class ConfigFragment : Fragment(R.layout.fragment_config) {

    var me = Me("", "", 0, "", "", "", 0, "", "", "")

    override fun onStart() {
        super.onStart()


        runBlocking {me = Gson().fromJson(askForMe(), Me::class.java)}
        updateInfoOnScreen(me)

        savebtn.setOnClickListener {
            runBlocking {change_data()}
            runBlocking {me = Gson().fromJson(askForMe(), Me::class.java)}
            updateInfoOnScreen(me)
        }

    }

    fun updateInfoOnScreen(researcher: Me) {
        editTextTextPersonName.setText(researcher.name)
        editTextTextPersonSurname.setText(researcher.last_name)
        editTextAge.setText(researcher.age.toString())
        editTextPwr.setText(researcher.university)
        editTextTextStatus.setText(researcher.status)
        editTextprojects.setText(researcher.active_projects.toString())
        editTextPhone.setText(researcher.number)
    }


    fun askForMe(): String {
        val jsonObject = JSONObject()
        jsonObject.put("token", Global.global_token)
        jsonObject.put("source", "app")
        jsonObject.put("function", "load_own_info")

        var response = ""
        runBlocking {
            response = Poster.postExample(jsonObject)
        }
        return response
    }

    fun change_data(): String {
        val jsonObject = JSONObject()
        jsonObject.put("token", Global.global_token)
        jsonObject.put("source", "app")
        jsonObject.put("function", "change_data")
        jsonObject.put("imie", editTextTextPersonName.text.toString())
        jsonObject.put("nazwisko", editTextTextPersonSurname.text.toString())
        jsonObject.put("wiek", editTextAge.text)
        jsonObject.put("uczelnia", editTextPwr.text.toString())
        jsonObject.put("status", editTextTextStatus.text.toString())
        jsonObject.put("ilosc_aktywnych_projektow", editTextprojects.text)
        jsonObject.put("numer_telefonu", editTextPhone.text.toString())
        jsonObject.put("z1", me.interest1)
        jsonObject.put("z2", me.interest2)
        jsonObject.put("z3", me.interest3)

        var response = ""
        runBlocking {
            response = Poster.postExample(jsonObject)
        }
        return response
    }

}