package fr.mastersime.stackoverflow.repository

import fr.mastersime.stackoverflow.data.Question
import kotlinx.coroutines.flow.Flow

interface QuestionsRepository {
    val questionListFlow: Flow<List<Question>>
    suspend fun updateQuestionList()
}