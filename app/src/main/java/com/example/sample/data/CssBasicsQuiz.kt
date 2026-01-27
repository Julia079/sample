package com.example.sample.data

import com.example.sample.models.Question

object CssBasicsQuiz {
    val questions = listOf(
        Question(
            text = "What does CSS stand for?",
            options = listOf("Creative Style System", "Cascading Style Sheets", "Computer Styling Script", "Colorful Style Syntax"),
            correctAnswerIndex = 1,
            explanation = "Cascading Style Sheets is the abbreviation for CSS."
        ),
        Question(
            text = "Which property changes the text color?",
            options = listOf("font-color", "text-color", "color", "font-style"),
            correctAnswerIndex = 2,
            explanation = "This property is standard and supported in all browsers. For demonstration you can copy this code: <p style=\"color:red\">test text</p><p>test2</p> on a note pad save it as 'sample.html' open the file once save."
        ),
        Question(
            text = "Which property controls the size of the text?",
            options = listOf("font-size", "text-size", "size", "font-style"),
            correctAnswerIndex = 0,
            explanation = "font-size controls the size of the text."
        ),
        Question(
            text = "What is the default display value for a <div>?",
            options = listOf("inline", "block", "inline-block", "flex"),
            correctAnswerIndex = 1,
            explanation = "<div> is a 'block-level' element by default in HTML."
        ),
        Question(
            text = "Which property is used to add space inside an element’s border?",
            options = listOf("margin", "padding", "border", "spacing"),
            correctAnswerIndex = 1,
            explanation = "Padding adds space inside an element, between the content and the border."
        ),
        Question(
            text = "Which CSS property is used to change the background color?",
            options = listOf("background-style", "bg-color", "background-color", "color"),
            correctAnswerIndex = 2,
            explanation = "It fills the inside area of the element with the color you choose."
        ),
        Question(
            text = "What does the CSS property margin control?",
            options = listOf("Space inside the element", "Space outside the element", "The element’s border thickness", "Text spacing"),
            correctAnswerIndex = 1,
            explanation = "The margin property creates space outside the element’s border."
        ),
        Question(
            text = "Which property is used to make text bold?",
            options = listOf("font-weight", "text-bold", "font-bold", "weight"),
            correctAnswerIndex = 0,
            explanation = "To make text bold, you can use font-weight"
        ),
        Question(
            text = "Which symbol is used to select a class in CSS?",
            options = listOf("# - Hash", ". - Dot", "@ - At sign", "* - Asterisk"),
            correctAnswerIndex = 1,
            explanation = "'. - Dot' is used to style elements that belong to that class. It is commonly seen inside the <style> in the <head> of an HTML document or an external CSS file."
        ),
        Question(
            text = "Which CSS property controls the width of an element?",
            options = listOf("size", "width", "element-width", "box-size"),
            correctAnswerIndex = 1,
            explanation = "The width property is used to set how wide an element will be."
        )
    )
}
