package fr.mastersime.stackoverflow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.mastersime.stackoverflow.data.Question
import fr.mastersime.stackoverflow.repository.QuestionsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionListViewModel @Inject constructor(
    private val repository: QuestionsRepository
) : ViewModel() {

    private val _questionsList: MutableLiveData<List<Question>> = MutableLiveData(emptyList())
    val questionList: LiveData<List<Question>> = _questionsList

    fun updateList() {
        viewModelScope.launch(Dispatchers.IO) {
            _questionsList.postValue(repository.getQuestionsList())
        }
    }
}