package com.example.sample.data

import com.example.sample.models.Question
import com.example.sample.R

object OperatingSystemsQuiz {
    val questions = listOf(
        Question(
            text = "What is the mostly used operating system?",
            options = listOf("Mac OS", "Linux", "Chrome OS", "Windows"),
            correctAnswerIndex = 3,
            explanation = "Windows has the largest number of users worldwide, especially on personal computers (desktops and laptops)."
        ),
        Question(
            text = "What is the latest Windows OS version?",
            options = listOf("Windows 7", "Windows 11", "Windows XP", "Windows 10"),
            correctAnswerIndex = 1,
            explanation = "Windows 11 is the latest major version of the Windows operating system released by Microsoft."
        ),
        Question(
            text = "Which company developed Windows?",
            options = listOf("Apple", "Samsung", "Microsoft", "Facebook"),
            correctAnswerIndex = 2,
            explanation = "Windows was created and is maintained by Microsoft, a major software company."
        ),
        Question(
            text = "Which OS is known for being open-source?",
            options = listOf("macOS", "Linux", "iOS", "Windows"),
            correctAnswerIndex = 1,
            explanation = "Linux is known for being open-source because its source code is freely available for anyone to view, modify, and share. This allows developers around the world to improve and customize it."
        ),
        Question(
            text = "If you delete your system 32 folder in your computer what will happen to your computer operating system?",
            options = listOf("It will shut down and not restart", "It will restart automatically", "Nothing will happen", "It will run faster"),
            correctAnswerIndex = 0,
            explanation = "Careful, deleting the System 32 folder will break the operating system since it contains drivers, files and libraries needed for functionality."
        ),
        Question(
            text = "What makes an OS user-friendly?",
            options = listOf("Complex commands only", "Easy-to-use interface", "High cost", "Large size"),
            correctAnswerIndex = 1,
            explanation = "A user-friendly operating system is easy to understand and use, even for beginners."
        ),
        Question(
            text = "A system software that manages a computer’s hardware and software resources to provide a user’s interface for interacting with the device?",
            options = listOf("Operating System", "Application Software", "Utility Software", "Firmware"),
            correctAnswerIndex = 0,
            explanation = "Operating System is the middle man between user and the computer hardware and software"
        ),
        Question(
            text = "Identify this logo",
            options = listOf("Linux", "Windows", "macOS", "Chrome OS"),
            correctAnswerIndex = 0,
            imageResId = R.drawable.linux_logo, // Placeholder
            explanation = "The logo of Linux has always been represented by a penguin."
        ),
        Question(
            text = "Identify this logo",
            options = listOf("Ubuntu", "Fedora", "Debian", "Android"),
            correctAnswerIndex = 0,
            imageResId = R.drawable.ubuntu_logo, // Placeholder
            explanation = "The Ubuntu logo has been orange and white since its inception in 2004. It is called the 'Circle of Friends' symbol."
        ),
        Question(
            text = "Identify this logo",
            options = listOf("Windows", "macOS", "Linux", "Chrome OS"),
            correctAnswerIndex = 1,
            imageResId = R.drawable.macos_logo, // Placeholder
            explanation = "The image is the logo of macOS, one of the many symbols that has evolves over time."
        )
    )
}
