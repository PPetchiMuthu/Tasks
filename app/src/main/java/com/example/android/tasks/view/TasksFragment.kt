package com.example.android.tasks.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.tasks.databinding.FragmentTasksBinding
import com.example.android.tasks.model.TaskDatabase
import com.example.android.tasks.viewmodel.TaskViewModel
import com.example.android.tasks.viewmodel.TaskViewModelFactory

class TasksFragment : Fragment() {

    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = TaskDatabase.getInstance(application).taskDao
        val viewModelFactory = TaskViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(TaskViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}