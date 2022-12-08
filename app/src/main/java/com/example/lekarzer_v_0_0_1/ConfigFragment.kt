package com.example.lekarzer_v_0_0_1

import android.view.Gravity
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_config.*
import kotlinx.coroutines.runBlocking
import org.json.JSONObject

class ConfigFragment : Fragment(R.layout.fragment_config) {

    var me = Me("", "", 0, "", "", "", 0, "", "", "", 0)

    override fun onStart() {
        super.onStart()


        runBlocking { me = Gson().fromJson(askForMe(), Me::class.java) }
        updateInfoOnScreen(me)

        savebtn.setOnClickListener {
            runBlocking { change_data() }
            runBlocking {
                val response = askForMe()
                me = Gson().fromJson(response, Me::class.java)
                if (!response.contains("error")){
                    val toast = Toast.makeText(context, "You've successfully updated your bio!", Toast.LENGTH_LONG)
                    toast.show()
                }else{
                    val toast = Toast.makeText(context, "Sorry, something went wrong. Please try again.", Toast.LENGTH_LONG)
                    toast.show()
                }
            }
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

    fun slownikZainteresowan(zainteresowanie: String): Int {
        when (zainteresowanie) {
            "Zawalserca" -> return 1
            "Arytmia" -> return 2
            "Jaskra" -> return 3
            "Stozekrogowki" -> return 4
            "Zlamania" -> return 5
            "ZespolDowna" -> return 6
            "ZespolKushinga" -> return 7
            "ChorobaWilsona" -> return 8
            "MalformacjaChiariego" -> return 9
            "ChorobaCrohna" -> return 10
        }
        return 1
    }

    fun change_data(): String {


        val jsonObject = JSONObject()
        jsonObject.put("token", Global.global_token)
        jsonObject.put("source", "app")
        jsonObject.put("function", "change_data")
        jsonObject.put("name", editTextTextPersonName.text.toString())
        jsonObject.put("last_name", editTextTextPersonSurname.text.toString())
        jsonObject.put("age", editTextAge.text)
        jsonObject.put("university", editTextPwr.text.toString())
        jsonObject.put("status", editTextTextStatus.text.toString())
        jsonObject.put("active_projects", editTextprojects.text)
        jsonObject.put("number", editTextPhone.text.toString())
        jsonObject.put("z1", slownikZainteresowan(me.interest1).toString())
        jsonObject.put("z2", slownikZainteresowan(me.interest2).toString())
        jsonObject.put("z3", slownikZainteresowan(me.interest3).toString())

        var response = ""
        runBlocking {
            response = Poster.postExample(jsonObject)
        }
        return response
    }

}