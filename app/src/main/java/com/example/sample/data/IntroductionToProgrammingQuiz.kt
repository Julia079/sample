package com.example.sample.data

import com.example.sample.models.Question

object IntroductionToProgrammingQuiz {
    val questions = listOf(
        Question(
            text = "Programming is the process of:",
            options = listOf("Repairing computer hardware", "Browsing the internet", "Designing clothes", "Writing instructions for a computer"),
            correctAnswerIndex = 3,
            explanation = "Programming means writing instructions that tell a computer what to do."
        ),
        Question(
            text = "What do you call a step-by-step solution to a problem?",
            options = listOf("Variable", "Syntax", "Algorithm", "Output"),
            correctAnswerIndex = 2,
            explanation = "A 'Variable' holds data, 'Syntax' is a set of rules in a programming language ,'Output' is the result of a product, while an 'Algorithm' is a step-by-step solution to a problem."
        ),
        Question(
            text = "Which of the following is a programming language?",
            options = listOf("Google", "Wi-Fi", "Python", "Windows"),
            correctAnswerIndex = 2,
            explanation = "Python is a programming language used to write computer programs."
        ),
        Question(
            text = "A variable is used to:",
            options = listOf("Shut down a computer", "Store data", "Draw graphics", "Print documents"),
            correctAnswerIndex = 1,
            explanation = "Variable is used to store data in a program."
        ),
        Question(
            text = "Syntax in programming refers to:",
            options = listOf("Rules of writing code", "The storage capacity", "The color of the text", "The speed of the program"),
            correctAnswerIndex = 0,
            explanation = "A Syntax means the rules for writing code correctly."
        ),
        Question(
            text = "Which device executes the instructions of a program?",
            options = listOf("Speaker", "Monitor", "CPU", "Mouse"),
            correctAnswerIndex = 2,
            explanation = "While 'Speaker', 'Monitor' and 'Mouse' are all devices used with the computer, the 'CPU' executes program instructions."
        ),
        Question(
            text = "Which of the following translates code line-by-line?",
            options = listOf("Debugger", "Scanner", "Compiler", "Interpreter"),
            correctAnswerIndex = 3,
            explanation = "'Debugger' is used to test a program, 'Scanner' reads input from the user or a file, and a 'Compiler' translates the entire code, while 'interpreter' translates code line-by-line"
        ),
        Question(
            text = "What is the output of a program?",
            options = listOf("The result the program produces", "The power supply", "The computer’s hardware", "Internet speed"),
            correctAnswerIndex = 0,
            explanation = " An output is the result that a program produces."
        ),
        Question(
            text = "Comments in a program are for:",
            options = listOf("Making the program run faster", "Explaining the code", "Storing data", "Opening files"),
            correctAnswerIndex = 1,
            explanation = "In programming, it is common to work in groups, 'Comments' are used to explain the code and help programmers understand it."
        ),
        Question(
            text = "What happens when a program has a syntax error?",
            options = listOf("The program will run faster", "Nothing happens", "The program will not execute properly", "The computer will shut down"),
            correctAnswerIndex = 2,
            explanation = "A syntax error prevents a program from running properly. It indicates that a line of code does not follow the language’s syntax rules; in English, this is similar to a grammatical error."
        )
    )
}
