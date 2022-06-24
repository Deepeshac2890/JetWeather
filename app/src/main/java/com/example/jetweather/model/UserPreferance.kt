package com.example.jetweather.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "preference_tbl")
data class UserPreferences (
        @NonNull
        @PrimaryKey
        @ColumnInfo(name="id")
        val id:String = "UniqueIDForThisUser",
        @ColumnInfo(name = "unit")
        val unit: String)
