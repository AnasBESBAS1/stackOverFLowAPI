package fr.mastersime.stackoverflow.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fr.mastersime.stackoverflow.databinding.FragmentQuestionListBinding


class QuestionsFragment : Fragment(){
    private lateinit var binding: FragmentQuestionListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuestionListBinding.inflate(inflater)
        return binding.root
    }
}