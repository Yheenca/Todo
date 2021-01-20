package com.tmr.yheenca.todo.ui.screens.fragment.todo_main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tmr.yheenca.todo.R
import com.tmr.yheenca.todo.adapter.recyclerview.TodoAdapter
import com.tmr.yheenca.todo.databinding.FragmentTodoBinding
import com.tmr.yheenca.todo.utilities.CustomDate
import dagger.hilt.android.AndroidEntryPoint

private const val FRAGMENT_ARGS = "dates"

@AndroidEntryPoint
class TodoFragment : Fragment(R.layout.fragment_todo) {

    private val dates = CustomDate.dates

    private val viewModel: TodoViewModel by viewModels()
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentTodoBinding.bind(view)

        arguments?.takeIf {
            it.containsKey(FRAGMENT_ARGS).apply {

            }
        }

        val layoutManager = LinearLayoutManager(requireContext())
        val adapter = TodoAdapter()

        binding.apply {
            taskRv.layoutManager = layoutManager
            taskRv.adapter = adapter
        }

        viewModel.todo.observe(viewLifecycleOwner){

        }

    }

}