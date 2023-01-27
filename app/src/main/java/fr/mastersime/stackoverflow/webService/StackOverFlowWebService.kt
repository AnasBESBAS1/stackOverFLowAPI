package fr.mastersime.stackoverflow.webService

import fr.mastersime.stackoverflow.data.ListQuestionsJson
import fr.mastersime.stackoverflow.data.Question
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StackOverFlowWebService {

    @GET("questions?pagesize=20")
    suspend fun getQuestionsList(
        @Query("order") order: String = "desc",
        @Query("sort") sort: String = "activity",
        @Query("site") site: String = "stackoverflow"
    ): List<Question>
}