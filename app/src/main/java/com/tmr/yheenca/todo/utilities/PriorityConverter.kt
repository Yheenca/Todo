package com.tmr.yheenca.todo.utilities

import androidx.room.TypeConverter
import com.tmr.yheenca.todo.data.model.Priority

class PriorityConverter {
    @TypeConverter
    fun toEnum(value: Int) = enumValues<Priority>()[value]

    @TypeConverter
    fun toInt(value: Priority) = value.ordinal
}