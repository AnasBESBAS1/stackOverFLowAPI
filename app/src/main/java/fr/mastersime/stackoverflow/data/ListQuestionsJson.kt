package fr.mastersime.stackoverflow.data

data class ListQuestionsJson(
    val items: List<QuestionJson>
)

data class QuestionJson(
    val answer_count: Int,
    val title: String
)

