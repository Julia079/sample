package com.example.sample.data

import com.example.sample.models.Question
import com.example.sample.R

object BasicHardwareQuiz {
    val questions = listOf(
        Question(
            text = "What is the main function of the CPU?",
            options = listOf("Store files", "Process instructions", "Display images", "Provide internet connection"),
            correctAnswerIndex = 1,
            explanation = "The CPU’s main function is to process instructions."
        ),
        Question(
            text = "Which of the following is a storage device?",
            options = listOf("RAM", "Hard Drive", "GPU", "Keyboard"),
            correctAnswerIndex = 1,
            explanation = "'RAM' is a fast, temporary and volatile memory, the 'GPU' handles the graphics and Visual processing and the 'Keyboard' is an input device, while a 'Hard Drive' is a storage device."
        ),
        Question(
            text = "What is the purpose of RAM?",
            options = listOf("Long-term storage", "Display output", "Temporary memory while the computer is running", "Cooling system"),
            correctAnswerIndex = 2,
            explanation = "RAM provides temporary memory while the computer is running."
        ),
        Question(
            text = "Which one is an input device?",
            options = listOf("Monitor", "Speaker", "Mouse", "Projector"),
            correctAnswerIndex = 2,
            explanation = "'Speaker', 'Monitor' and 'Projector' are all output devices, while 'Mouse' is an input device used to give commands to the computer."
        ),
        Question(
            text = "What does the motherboard do?",
            options = listOf("Cools the CPU", "Connects all computer components", "Saves files", "Displays graphics"),
            correctAnswerIndex = 1,
            explanation = "The motherboard connects all the computer components so they can work together."
        ),
        Question(
            text = "Which language is used for styling web?",
            options = listOf("Python", "CSS", "Java", "C++"),
            correctAnswerIndex = 1,
            explanation = "'Python' is a machine language, 'JAVA' is a general purpose programming language, while 'CSS' is the language used for styling web pages."
        ),
        Question(
            text = "What part of the computer is this?",
            options = listOf("RAM", "CPU", "Motherboard", "Hard Drive"),
            correctAnswerIndex = 1,
            explanation = "The image displays a computer's CPU",
            imageResId = R.drawable.cpu_image // Add cpu_image.png to drawable and uncomment this line
        ),
        Question(
            text = "Based on the photo, what appears to be the main problem in his computer?",
            options = listOf("Not enough RAM", "CPU is overheating", "System process is using too much CPU", "Too many applications are open"),
            correctAnswerIndex = 2,
            explanation = "The Task Manager highlights the System Process consuming 97% of CPU, if its consistently at 97% this may indicate a problem. Better get the computer checked out.",
            imageResId = R.drawable.task_manager_image // Add task_manager_image.png to drawable and uncomment this line
        ),
        Question(
            text = "What is the primary purpose of a heat sink in a computer?",
            options = listOf("To store data", "To cool the CPU", "To increase the processing speed", "To provide power"),
            correctAnswerIndex = 1,
            explanation = "The primary purpose of Heat Sink is 'to cool the CPU'. Heat sinks are essential for preventing overheating, which can lead to performance issue and permanent damage if left unmanaged."
        ),
        Question(
            text = "A user complains that their monitor is black, but the PC's power light is on. What is the first thing you should check?",
            options = listOf("Buy a new monitor and call a friend.", "Check for loose connections.", "Be angry and nag the user.", "Panic and push all the buttons."),
            correctAnswerIndex = 1,
            explanation = "If the monitor is black but the PC is on, the first step is to check for loose connections to make sure everything is properly plugged in.",
            imageResId = R.drawable.monitor_image // Add monitor_image.png to drawable and uncomment this line
        )
    )
}
