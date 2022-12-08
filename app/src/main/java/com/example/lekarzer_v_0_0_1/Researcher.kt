package com.example.lekarzer_v_0_0_1

import java.io.Serializable

data class Researcher(
    val id : Int,
    val name: String,
    val last_name: String,
    val age: Int,
    val university: String,
    val status: String,
    val active_projects_count: Int,
    val interest1: String,
    val interest2: String,
    val interest3: String,
) : Serializable


//load_contact_info user = id

//liked
//liked id = 10