package com.example.lekarzer_v_0_0_1

import java.io.Serializable

data class Researcher(
    val name: String,
    val surname: String,
    val age: Int,
    val university: String,
    val status: String,
    val activeProjectsCount: Int,
    val interests: MutableList<String> = mutableListOf<String>(),
    val portfolio: MutableList<String> = mutableListOf<String>()
) : Serializable