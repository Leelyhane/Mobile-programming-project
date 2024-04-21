package com.example.doapp

data class Task(
    val name: String,
    val startDate: String,
    val endDate: String,
    var isCompleted: Boolean = false,
    val subtasks: MutableList<Subtask> = mutableListOf() // List to hold subtasks
)


