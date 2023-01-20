package fr.mastersime.stackoverflow.webService

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StackOverFlowWebService {

    @GET("questions?pagesize=20")
    fun getQuestionsList(
        @Query("order") order: String = "desc",
        @Query("sort") sort: String = "activity",
        @Query("site") site: String = "stackoverflow"
    ): Call<String>
}