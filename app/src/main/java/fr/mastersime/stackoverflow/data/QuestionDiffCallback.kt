package fr.mastersime.stackoverflow.data

import androidx.recyclerview.widget.DiffUtil

class QuestionDiffCallback : DiffUtil.ItemCallback<Question>() {
    override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
        return oldItem.answerCount == newItem.answerCount
    }

    override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
        return oldItem.title == newItem.title
    }
}