package com.tmr.yheenca.todo.data.repository

import com.tmr.yheenca.todo.data.database.TodoDao
import com.tmr.yheenca.todo.data.model.Todo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val dao: TodoDao) {
    suspend fun insert(todo: Todo) = dao.insert(todo)
    suspend fun update(todo: Todo) = dao.update(todo)
    suspend fun delete(todo: Todo) = dao.delete(todo)
    fun getTodoList() = dao.getTodoList()
}