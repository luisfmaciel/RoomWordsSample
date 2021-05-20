package br.edu.infnet.roomwordssample.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "word_table"
)
data class Word(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "word") val mWord: String
)