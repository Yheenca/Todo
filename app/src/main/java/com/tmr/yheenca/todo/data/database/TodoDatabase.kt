package com.tmr.yheenca.todo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.tmr.yheenca.todo.data.model.Priority
import com.tmr.yheenca.todo.data.model.Todo
import com.tmr.yheenca.todo.di.Scope
import com.tmr.yheenca.todo.utilities.PriorityConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Todo::class], version = 1, exportSchema = false)
@TypeConverters(PriorityConverter::class)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDado(): TodoDao

    class Callback @Inject constructor(
        private val database: Provider<TodoDatabase>,
        @Scope private val coroutineScope: CoroutineScope
    ) : RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().todoDado()

            coroutineScope.launch {
                dao.insert(Todo("Call mum", completed = true))
                dao.insert(Todo("Buy groceries", priority = Priority.CRITICAL))
                dao.insert(Todo("Take a nap", completed = true))
                dao.insert(Todo("play game", priority = Priority.URGENT))
            }

        }
    }

}