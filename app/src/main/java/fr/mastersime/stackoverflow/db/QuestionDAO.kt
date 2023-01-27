package fr.mastersime.stackoverflow.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.mastersime.stackoverflow.data.Question
import java.util.concurrent.Flow
import javax.inject.Inject

@Dao
interface QuestionDAO { // Data Access Object interface
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<Question>)

    @Query("SELECT * FROM question_table")
    fun getQuestionsListFlow(): kotlinx.coroutines.flow.Flow<List<Question>>

}