package fr.mastersime.stackoverflow.db

import androidx.room.Database
import fr.mastersime.stackoverflow.data.Question

@Database(
    entities = [Question::class],
    version = 1,
    exportSchema = false
)
abstract class QuestionsRoomDatabase {
    abstract fun questionDao(): QuestionDAO
}