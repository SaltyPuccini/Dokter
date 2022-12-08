package com.example.lekarzer_v_0_0_1

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.coroutines.runBlocking
import org.json.JSONObject


class ContactsFragment : Fragment(R.layout.fragment_contacts) {
    override fun onStart() {
        super.onStart()
        var a: String
        runBlocking { a = askForMatches() }
        val response = Gson().fromJson(a, MacherMaster::class.java)



        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(context)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        data.add(ItemsViewModel(response.user_0))
        data.add(ItemsViewModel(response.user_1))



        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

    }

    fun askForMatches(): String {
        val jsonObject = JSONObject()
        jsonObject.put("token", Global.global_token)
        jsonObject.put("source", "app")
        jsonObject.put("function", "matches")

        var response = ""
        runBlocking {
            response = Poster.postExample(jsonObject)
        }
        return response
    }

}