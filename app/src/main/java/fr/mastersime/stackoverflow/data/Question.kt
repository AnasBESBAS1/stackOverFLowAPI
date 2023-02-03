package fr.mastersime.stackoverflow.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question_table")
data class Question(@PrimaryKey val title: String, val answerCount: Int, val last_activity_date : Int)


