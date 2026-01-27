package com.example.sample.data

import com.example.sample.models.Question

object HtmlBasicsQuiz {
    val questions = listOf(
        Question(
            text = "What does HTML stand for?",
            options = listOf("Hyper Text Markup Language", "High Text Markup Language", "Hyper Tabular Markup Language", "None of the above"),
            correctAnswerIndex = 0,
            explanation = "Hyper Text Markup Language is the abbreviation for HTML."
        ),
        Question(
            text = "Which tag is the root element of an HTML page?",
            options = listOf("<html>", "<head>", "<body>", "<title>"),
            correctAnswerIndex = 0,
            explanation = "The <html> tag is the root element of an HTML page. The <head> contains metadata of the HTML document like the <title> tag, while the <body> contains the visible content of the page."
        ),
        Question(
            text = "Which tag contains meta information about the HTML page?",
            options = listOf("<title>", "<head>", "<meta>", "<style>"),
            correctAnswerIndex = 1,
            explanation = "The <head> contains meta information like title, styles, and scripts."
        ),
        Question(
            text = "Which tag defines the HTML document's body?",
            options = listOf("<head>", "<body>", "<div>", "<main>"),
            correctAnswerIndex = 1,
            explanation = "The <body> defines the main content of the HTML document. It is where the UI and content of the page are displayed."
        ),
        Question(
            text = "HTML headings are defined with which tags?",
            options = listOf("<head>", "<heading>", "<h1> to <h6>", "<title>"),
            correctAnswerIndex = 2,
            explanation = "HTML headings use <h1>(Largest) to <h6>(Smallest) for different levels of headings."
        ),
        Question(
            text = "Which tag is used for a paragraph?",
            options = listOf("<p>", "<text>", "<para>", "<div>"),
            correctAnswerIndex = 0,
            explanation = "The <p> tag is used to define a paragraph."
        ),
        Question(
            text = "What is the correct HTML for inserting a line break?",
            options = listOf("<br>", "<lb>", "<break>", "<newline>"),
            correctAnswerIndex = 0,
            explanation = "<br> inserts a line break in the text."
        ),
        Question(
            text = "Which tag creates a hyperlink?",
            options = listOf("<link>", "<a>", "<url>", "<href>"),
            correctAnswerIndex = 1,
            explanation = "The <a> tag is used to create a hyperlink."
        ),
        Question(
            text = "Which attribute specifies an alternate text for an image?",
            options = listOf("alt", "title", "src", "name"),
            correctAnswerIndex = 0,
            explanation = "The alt attribute provides alternate text for images when they can’t be displayed."
        ),
        Question(
            text = "Is HTML case-sensitive?",
            options = listOf("Yes", "No", "Only for attributes", "Only for tags"),
            correctAnswerIndex = 1,
            explanation = "HTML is not case-sensitive, so tags can be written in uppercase or lowercase."
        )
    )
}
