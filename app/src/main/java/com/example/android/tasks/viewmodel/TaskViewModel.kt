package com.example.android.tasks.viewmodel

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.tasks.model.Task
import com.example.android.tasks.model.TaskDao
import kotlinx.coroutines.launch

class TaskViewModel(val dao: TaskDao) : ViewModel() {
    var newTaskName = ""

    private val tasks = dao.getAll()
    val tasksString = Transformations.map(tasks) { tasks ->
        formatTasks(tasks)
    }

    fun addTask() {
        viewModelScope.launch {
            val task = Task()
            task.taskName = newTaskName
            dao.insert(task)
        }
    }

    private fun formatTasks(tasks: List<Task>?): String {
        return tasks!!.fold("") { str, item ->
            str + '\n' + formatTask(item)
        }

    }

    private fun formatTask(task: Task): String {
        var str = "ID:${task.taskId}"
        str += '\n' + "Name : ${task.taskName}"
        str += '\n' + "Complete : ${task.taskDone}"
        return str
    }
}