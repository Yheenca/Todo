package com.tmr.yheenca.todo.data.database

import androidx.room.*
import com.tmr.yheenca.todo.data.model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Todo)

    @Update
    suspend fun update(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)

    @Query("SELECT * FROM todo_table WHERE date IN (:date)")
    fun getTodoList(date: Long): Flow<List<Todo>>

    @Query("SELECT * FROM todo_table")
    fun getTodoList(): Flow<List<Todo>>
}