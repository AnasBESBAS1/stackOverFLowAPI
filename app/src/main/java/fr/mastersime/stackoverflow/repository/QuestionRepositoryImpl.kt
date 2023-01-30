package fr.mastersime.stackoverflow.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import fr.mastersime.stackoverflow.data.Question
import fr.mastersime.stackoverflow.data.RequestState
import fr.mastersime.stackoverflow.db.QuestionDAO
import fr.mastersime.stackoverflow.webService.StackOverFlowWebService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class QuestionRepositoryImpl @Inject constructor(
    private val stackOverFlowWebService: StackOverFlowWebService,
    private val questionDAO: QuestionDAO
) : QuestionsRepository {

    override val questionListFlow: Flow<List<Question>> = questionDAO.getQuestionsListFlow()

    override var requestStateFlow: MutableStateFlow<RequestState> =
        MutableStateFlow(RequestState("ok", true))


    override suspend fun updateQuestionList() {
        try { // request valid

            requestStateFlow.emit(
                RequestState("ok", true)
            )
            /*  requestStateFlow.apply {
                  MutableStateFlow(RequestState("ok",true))
              }*/
            val list = stackOverFlowWebService
                .getQuestionsList()
            questionDAO.insertAll(list)
        } catch (ex: IOException) {

            requestStateFlow.emit(
                RequestState("IOException", false)
            )
            Log.d("ErrorReq", "${ex.stackTrace}")
        } catch (ex: HttpException) {
            requestStateFlow.emit(
                RequestState("HttpException", false)
            )
        }
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