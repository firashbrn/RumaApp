package com.example.ruma.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("priorities")
data class PrirityEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

)
