package com.example.graphtheorytrivia

data class QuestionClass(

    //https://stackoverflow.com/questions/37873995/how-to-create-empty-constructor-for-data-class-in-kotlin-android

    val questionText: String = "",
    val answerText: List<String> = emptyList()

)

