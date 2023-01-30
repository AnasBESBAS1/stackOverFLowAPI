package fr.mastersime.stackoverflow.repository

import androidx.lifecycle.MutableLiveData
import fr.mastersime.stackoverflow.data.Question
import fr.mastersime.stackoverflow.data.RequestState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface QuestionsRepository {
    val questionListFlow: Flow<List<Question>>

    val requestStateFlow : MutableStateFlow<RequestState>

    suspend fun updateQuestionList()
}