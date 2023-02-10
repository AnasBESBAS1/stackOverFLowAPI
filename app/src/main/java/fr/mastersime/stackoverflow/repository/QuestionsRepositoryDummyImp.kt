package fr.mastersime.stackoverflow.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import dagger.assisted.Assisted
import dagger.hilt.android.qualifiers.ApplicationContext
import fr.mastersime.stackoverflow.data.Question
import fr.mastersime.stackoverflow.data.RequestState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class QuestionsRepositoryDummyImp @Inject constructor(override val questionListFlow: Flow<List<Question>>,
                                                      override val requestStateFlow: MutableStateFlow<RequestState>,
) :
    QuestionsRepository {

    private val _questionListFlow: MutableStateFlow<List<Question>> =
        MutableStateFlow(emptyList())

    /* override suspend fun getQuestionsList(): List<Question> {
        delay(2000)
        return listOf(
            Question("How can i use css in kotlin", (1..20).random()),
            Question("How can i use css in kotlin", (1..20).random()),
            Question("How can i use css in kotlin", (1..20).random()),
            Question("How can i use css in kotlin", (1..20).random()),
            Question("How can i use css in kotlin", (1..20).random())
        )
    }*/
    override suspend fun updateQuestionList() {
        delay(500)
        _questionListFlow.emit(
            listOf(
                Question("How can i use css in kotlin", (1..20).random(),0),
                Question("How can i use css in kotlin", (1..20).random(),0),
                Question("How can i use css in kotlin", (1..20).random(),0),
                Question("How can i use css in kotlin", (1..20).random(),0),
                Question("How can i use css in kotlin", (1..20).random(),0)
            )
        )
    }

    override fun sendSMS(context: Context) {
    }
}