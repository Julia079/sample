package com.example.sample.data

import com.example.sample.models.Question

object IntroToITQuiz {
    val questions = listOf(
        Question(
            text = "What does CPU stand for?",
            options = listOf("Central Processing Unit", "Computer Personal Unit", "Central Power Unit"),
            correctAnswerIndex = 0,
            explanation = "CPU stands for 'Central Processing Unit'.It is often referred to as the 'Brain' of the computer."
        ),
        Question(
            text = "Which of these is a type of computer memory?",
            options = listOf("CPU", "RAM", "GPU"),
            correctAnswerIndex = 1,
            explanation = "The 'CPU' is the 'Brain' of the computer, 'GPU' is the 'Graphics Card' that handles graphical operations, while 'RAM' is the fast, temporary and volatile memory. Data stored in RAM is lost once the computer is turned off."
        ),
        Question(
            text = "What is the main function of an Operating System (OS)?",
            options = listOf("To browse the internet", "To manage hardware and software resources", "To create documents"),
            correctAnswerIndex = 1,
            explanation = "'To connect to the internet' is handled by network software and hardware, 'To create documents' is done using applications, while 'To manage computer programs and hardware resources' is the role of operating system (OS) which is the main control program of a computer."
        ),
        Question(
            text = "Which of the following is NOT an input device?",
            options = listOf("Keyboard", "Mouse", "Printer"),
            correctAnswerIndex = 2,
            explanation = "'Keyboard' and 'Mouse' are input devices since they are used to interact and input commands on a computer while 'Printer' is an output device because it prints out the user's desired output on paper."
        ),
        Question(
            text = "What does 'IT' stand for?",
            options = listOf("Internet Technology", "Information Technology", "Intelligent Technology"),
            correctAnswerIndex = 1,
            explanation = "Information Technology is the abbreviation for IT."
        ),
        Question(
            text = "What is the programming language for machine learning?",
            options = listOf("Java", "HTML", "CSS", "Python"),
            correctAnswerIndex = 3,
            explanation = "'HTML' and 'CSS' focus on the website design and structure, while 'JAVA' is more broad than python. 'Python' is widely used in machine learning and it’s good for beginners learning to code."
        ),
        Question(
            text = "What is the largest network in the world?",
            options = listOf("LAN", "MAN", "WAN", "Internet"),
            correctAnswerIndex = 3,
            explanation = "'LAN' covers small area, 'MAN' covers city or large campus and 'WAN' covers a large geographic such as country or continent while 'INTERNET' it connects millions of LAN’s, MAN's, and WAN’s together."
        ),
        Question(
            text = "What do we call a group of connected computers?",
            options = listOf("Database", "Server", "Network", "Software"),
            correctAnswerIndex = 2,
            explanation = "DATABASE is a collection of data, SERVER is a computer that provides services, SOFTWARE is a program and applications while a NETWORK  means group of connected computers."
        ),
        Question(
            text = "Which term refers to protecting computers from threats?",
            options = listOf("Encoding", "Cybersecurity", "Formatting", "Debugging"),
            correctAnswerIndex = 1,
            explanation = "ENCODING is a converting data into another form, Formatting is preparing storage devices and documents while CYBERSECURITY refers to protecting computers, network, and data from threats."
        ),
        Question(
            text = "What does HTTP stands for?",
            options = listOf("Hyper Type Text Protocol", "Hyper Text Transfer Protocol", "Him This That Protocol", "Home Type Text Protocol"),
            correctAnswerIndex = 1,
            explanation = "Hyper Text Transfer Protocol is the abbreviation for HTTP."
        )
    )
}
