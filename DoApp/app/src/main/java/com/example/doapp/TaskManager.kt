package com.example.doapp

object TaskManager {
    // A list to hold tasks
    private val tasksList: MutableList<Task> = mutableListOf()

    // Add a task to the list
    fun addTask(task: Task) {
        tasksList.add(task)
        // Log statement to indicate that a task has been added
        println("Task added: $task")
    }

    // Get all tasks
    fun getTasks(): List<Task> {
        // Log statement to indicate that the function is called
        println("getTasks() called")
        return tasksList
    }
    fun addSubtask(taskIndex: Int, subtask: String) {
        tasksList[taskIndex].subtasks.add(subtask)
    }

    // Remove a subtask from a task
    fun removeSubtask(taskIndex: Int, subtaskIndex: Int) {
        tasksList[taskIndex].subtasks.removeAt(subtaskIndex)
    }
}

private fun <E> MutableList<E>.add(element: String) {

}
