package fr.mastersime.stackoverflow.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import fr.mastersime.stackoverflow.data.RequestState
import fr.mastersime.stackoverflow.databinding.FragmentQuestionListBinding
import fr.mastersime.stackoverflow.viewModel.QuestionListViewModel

@AndroidEntryPoint
class QuestionsListFragment : Fragment() {
    private lateinit var binding: FragmentQuestionListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuestionListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val questionListAdapter = QuestionListAdapter()

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = questionListAdapter
        }
        // Testing
        val questionsListViewModel: QuestionListViewModel by viewModels()
        binding.swipeRefresh.setOnRefreshListener {
            questionsListViewModel.updateList()
        }
        questionsListViewModel.questionList.observe(viewLifecycleOwner) { value ->
            questionListAdapter.submitList(value)
        }

        /*  questionsListViewModel.isUpdating.observe(viewLifecycleOwner) { value ->
              binding.swipeRefresh.isRefreshing = value
          }*/

        questionsListViewModel.requestState.observe(viewLifecycleOwner) { value ->
            if (value == RequestState("ok", true)) {
                binding.swipeRefresh.isRefreshing = true
                questionsListViewModel.updateList()
                binding.swipeRefresh.isRefreshing = false
            } else if (value == RequestState("IOException", false)) {
                binding.swipeRefresh.isRefreshing = false
                val text = "Network Error!"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(context, text, duration)
                toast.show()
            }
        }
    }
}