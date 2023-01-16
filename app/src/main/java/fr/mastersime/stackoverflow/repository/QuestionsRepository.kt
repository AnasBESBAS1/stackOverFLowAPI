package fr.mastersime.stackoverflow.repository

import fr.mastersime.stackoverflow.data.Question

interface QuestionsRepository {

    suspend fun getQuestionsList(): List<Question>
}