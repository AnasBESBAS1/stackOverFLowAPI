package fr.mastersime.stackoverflow.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import fr.mastersime.stackoverflow.data.Question
import fr.mastersime.stackoverflow.databinding.FragmentQuestionListBinding
import fr.mastersime.stackoverflow.viewModel.QuestionListViewModel


class QuestionsListFragment : Fragment() {
    private lateinit var binding: FragmentQuestionListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

        /*
        // Testing
        questionListAdapter.submitList(
            listOf(
                Question("hellllllllo", 4),
            )
        )*/
        val questionsListViewModel: QuestionListViewModel by viewModels()


    }
}