package com.example.lekarzer_v_0_0_1

import java.io.Serializable

data class Match(
    val name: String,
    val last_name: String,
    val active_projects: Int,
    val age: Int,
    val university: String,
    val status: String,
    val interest1: String,
    val interest2: String,
    val interest3: String,
    val number: String,
) : Serializable