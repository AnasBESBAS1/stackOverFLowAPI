package fr.mastersime.stackoverflow.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.mastersime.stackoverflow.databinding.ViewHolderQuestionsBinding

class QuestionListAdapter : RecyclerView.Adapter<QuestionsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionsViewHolder {
        val binding = ViewHolderQuestionsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return QuestionsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuestionsViewHolder, position: Int) {
        val context = holder.binding.root.context
        holder.binding.title.text = "How can i use binding in kotlin and how to set it up ?"
        holder.binding.answerCount.text = "4"
    }

    override fun getItemCount(): Int {
        return 200
    }
}