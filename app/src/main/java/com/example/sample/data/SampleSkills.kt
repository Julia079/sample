package com.example.sample.data

import com.example.sample.models.Skill

object SampleSkills {
    val skills = listOf(
        Skill(
            id = "1",
            name = "Introduction to IT",
            description = "Learn the basics of the IT field."
        ),
        Skill(
            id = "2",
            name = "Basic Computer Hardware",
            description = "Understand the essential components of a computer."
        ),
        Skill(
            id = "3",
            name = "Operating Systems",
            description = "Explore the fundamentals of operating systems."
        ),
        Skill(
            id = "4",
            name = "Introduction to Programming",
            description = "Get started with the core concepts of programming."
        ),
        Skill(
            id = "5",
            name = "HTML Basics",
            description = "Learn the foundation of web pages."
        ),
        Skill(
            id = "6",
            name = "CSS Basics",
            description = "Style your web pages with CSS."
        ),
        Skill(
            id = "7", 
            name = "Final Assessment",
            description = "Your final step. Unlocks when all topics are passed."
        )
    )
}
