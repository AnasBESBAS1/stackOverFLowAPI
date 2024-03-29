package fr.mastersime.stackoverflow.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.mastersime.stackoverflow.data.Question
import fr.mastersime.stackoverflow.data.RequestState
import fr.mastersime.stackoverflow.repository.QuestionsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionListViewModel @Inject constructor(
    private val repository: QuestionsRepository
) : ViewModel() {
    private val _isUpdating: MutableLiveData<Boolean> = MutableLiveData(false)
    private val _questionsList: MutableLiveData<List<Question>> = MutableLiveData(emptyList())
    val questionList: LiveData<List<Question>> = repository.questionListFlow.asLiveData()
    val isUpdating: LiveData<Boolean> = _isUpdating

    val requestState: LiveData<RequestState> = repository.requestStateFlow.asLiveData()

    fun sendSMS(context: Context){
        repository.sendSMS(context)
    }

    fun updateList() {
        Log.d("here updateList", "dsc")
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateQuestionList()

            /*
            _isUpdating.postValue(true)
            // _questionsList.postValue(repository.getQuestionsList())
            repository.updateQuestionList()
            _isUpdating.postValue(false)
            */
        }
    }
}