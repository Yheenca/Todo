package com.tmr.yheenca.todo.adapter.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tmr.yheenca.todo.R
import com.tmr.yheenca.todo.data.model.Todo
import com.tmr.yheenca.todo.databinding.TodoItemBinding

class TodoAdapter : ListAdapter<Todo, TodoAdapter.TodoViewHolder>(TODO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding: TodoItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.todo_item,
            parent,
            false
        )
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = getItem(position)
        holder.bind(todo)
        holder.binding.executePendingBindings()
    }

    class TodoViewHolder(val binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo){
            binding.apply {
                todoItem = todo
            }
        }
    }

    companion object {
        val TODO_COMPARATOR = object : DiffUtil.ItemCallback<Todo>() {
            override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean
                    = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean
                    = oldItem == newItem
            
        }
    }

}