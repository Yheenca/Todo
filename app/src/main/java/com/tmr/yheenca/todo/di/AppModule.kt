package com.tmr.yheenca.todo.di

import android.app.Application
import androidx.room.Room
import com.tmr.yheenca.todo.data.database.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(
        context: Application,
        callback: TodoDatabase.Callback
    ) = Room.databaseBuilder(context, TodoDatabase::class.java, "todo")
        .fallbackToDestructiveMigration()
        .addCallback(callback)
        .build()

    @Provides
    fun provideTodoDao(db: TodoDatabase) = db.todoDado()

    @Scope
    @Provides
    @Singleton
    fun provideScope() = CoroutineScope(SupervisorJob())

}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class Scope