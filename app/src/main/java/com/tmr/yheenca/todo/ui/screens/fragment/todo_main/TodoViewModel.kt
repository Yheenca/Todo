package com.tmr.yheenca.todo.ui.screens.fragment.todo_main

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.tmr.yheenca.todo.data.repository.Repository

class TodoViewModel @ViewModelInject constructor(private val repo: Repository) : ViewModel(){

    val todo = repo.getTodoList().asLiveData()

    fun clickHandler(v: View){

    }

}