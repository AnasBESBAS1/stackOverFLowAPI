package fr.mastersime.stackoverflow.webService

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import fr.mastersime.stackoverflow.data.ListQuestionsJson
import fr.mastersime.stackoverflow.data.Question
import fr.mastersime.stackoverflow.data.QuestionJson

class QuestionMoshiAdapter {

    @FromJson
    fun fromJson(listQuestionsJson: ListQuestionsJson): List<Question> {

        return listQuestionsJson.items.map { questionJson ->
            Question(questionJson.title, questionJson.answer_count)
        }
    }

    @ToJson
    fun toJson(listQuestions: List<Question>): ListQuestionsJson {
        return ListQuestionsJson(
            listQuestions.map { question ->
                QuestionJson(question.answerCount, question.title)
            }
        )
    }
}