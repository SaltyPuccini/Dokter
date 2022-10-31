package com.example.lekarzer_v_0_0_1

import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {
    override fun onStart() {
        super.onStart()
        val (currentResearcher, nextResearcher) = updateResearchersData()
        println("${currentResearcher.age}, ${nextResearcher.age}")
    }

    fun updateResearchersData(): Pair<Researcher, Researcher> {
        //Send post to server asking for the data of top 2 compatible researchers, not yet swiped
        //then create json from string and serialize it to a local Researcher object

        val researcher1 = Researcher(
            "Rias",
            "Gremory",
            18,
            "PWr",
            "Looking for research",
            1,
            mutableListOf("Anatomy"),
            mutableListOf("Hyperlink")
        )

        val researcher2 = Researcher(
            "Rias",
            "Gremory",
            22,
            "PWr",
            "Looking for research",
            1,
            mutableListOf("Anatomy"),
            mutableListOf("Hyperlink")
        )

        return Pair(researcher1, researcher2)
    }
}


