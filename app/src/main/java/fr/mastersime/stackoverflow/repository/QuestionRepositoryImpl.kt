package fr.mastersime.stackoverflow.repository

import android.content.Context
import android.os.Build
import android.telephony.SmsManager
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import dagger.assisted.Assisted
import dagger.hilt.android.qualifiers.ApplicationContext
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

    override fun sendSMS(context: Context) {

        val message = "HELLO"

        // on the below line we are creating a try and catch block
        try {
            val smsManager: SmsManager
            if (Build.VERSION.SDK_INT >= 23) {
                //if SDK is greater that or equal to 23 then
                //this is how we will initialize the SmsManager
                smsManager = context.getSystemService(SmsManager::class.java)
            } else {
                //if user's SDK is less than 23 then
                //SmsManager will be initialized like this
                smsManager = SmsManager.getDefault()
            }

            // on below line we are sending text message.
            smsManager.sendTextMessage("0648841933", null, message, null, null)

            // on below line we are displaying a toast message for message send,
            Toast.makeText(context, "Message Sent", Toast.LENGTH_LONG).show()

        } catch (e: Exception) {

            // on catch block we are displaying toast message for error.
            Toast.makeText(
                context,
                "Please enter all the data.." + e.message.toString(),
                Toast.LENGTH_LONG
            )
                .show()
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