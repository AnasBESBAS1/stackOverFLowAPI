package fr.mastersime.stackoverflow.repository

import android.util.Log
import fr.mastersime.stackoverflow.data.Question
import fr.mastersime.stackoverflow.db.QuestionDAO
import fr.mastersime.stackoverflow.webService.StackOverFlowWebService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionRepositoryImpl @Inject constructor(
    private val stackOverFlowWebService: StackOverFlowWebService,
    private val questionDAO: QuestionDAO
) : QuestionsRepository {

    override val questionListFlow: Flow<List<Question>> = questionDAO.getQuestionsListFlow()
    override suspend fun updateQuestionList() {
        val list = stackOverFlowWebService
            .getQuestionsList()
        questionDAO.insertAll(list)

    }
    /*{
        return stackOverFlowWebService
            .getQuestionsList()
        /*
        val list = stackOverFlowWebService.getQuestionsList()
        Log.d("webService","list : $list")
       stackOverFlowWebService
            .getQuestionsList()
            .enqueue(
                object : Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        Log.d("WebService", "OK : $(response.body())")
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Log.d("WebService", "Error : $(t.message)")
                    }
                }
            )
        return emptyList()*/
    }*/
}