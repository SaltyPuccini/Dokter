package com.example.lekarzer_v_0_0_1

import java.io.Serializable

data class Me(
    val name: String,
    val last_name: String,
    val age: Int,
    val university: String,
    val status: String,
    val number: String,
    val active_projects: Int,
    val interest1: String,
    val interest2: String,
    val interest3: String,
) : Serializable


//load_contact_info user = id

//liked
//liked id = 10