package com.tmr.yheenca.todo.ui.screens.fragment.add_todo

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tmr.yheenca.todo.databinding.FragmentAddTodoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTodoFragment : BottomSheetDialogFragment(){

    companion object {
        fun newInstance() = AddTodoFragment()

        const val TAG = "com.tmr.yheenca.todo.ui.screens.fragment.add_todo"

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding: FragmentAddTodoBinding = FragmentAddTodoBinding.bind(view)

        binding.apply {
            todoTimePicker.setIs24HourView(true)
        }

    }
}