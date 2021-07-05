package com.example.graphtheorytrivia

data class QuestionClass(

    val questionText: String = "",
    val answerText: List<String> = emptyList()

)

//mock data
val questions2: MutableList<QuestionClass> = mutableListOf(
    QuestionClass(
        questionText = "What is my name",
        answerText = listOf("Mohammed", "Saleh", "Ahmed", "Omar")
    ),
    QuestionClass(
        questionText = "What Is my age",
        answerText = listOf("24", "23", "22", "21")
    ),
    QuestionClass(
        questionText = "What is my major",
        answerText = listOf("CS", "IS", "IT", "NS")
    ),
    QuestionClass(
        questionText = "What is my gender",
        answerText = listOf("Male", "Female", "Not Sure", "All")
    )
)


//empty constructor
val questions3: QuestionClass = QuestionClass()