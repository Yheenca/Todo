package com.tmr.yheenca.todo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class Todo (
    val title: String,
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val priority: Priority = Priority.NORMAL,
    val completed: Boolean = false,
    val date: Long = System.currentTimeMillis()
)