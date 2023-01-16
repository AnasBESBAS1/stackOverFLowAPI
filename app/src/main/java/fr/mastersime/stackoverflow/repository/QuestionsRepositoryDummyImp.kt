package fr.mastersime.stackoverflow.repository

import fr.mastersime.stackoverflow.data.Question
import kotlinx.coroutines.delay
import javax.inject.Inject

class QuestionsRepositoryDummyImp @Inject constructor() : QuestionsRepository {
    override suspend fun getQuestionsList(): List<Question> {
        delay(2000)
        return listOf(
            Question("How can i use css in kotlin", (1..20).random()),
            Question("How can i use css in kotlin", (1..20).random()),
            Question("How can i use css in kotlin", (1..20).random()),
            Question("How can i use css in kotlin", (1..20).random()),
            Question("How can i use css in kotlin", (1..20).random())
        )
    }
}