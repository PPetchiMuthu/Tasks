package com.example.android.tasks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.tasks.model.TaskDao

@Suppress("UNCHECKED_CAST")
class TaskViewModelFactory(private val dao: TaskDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            return TaskViewModel(dao) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel")
    }
}