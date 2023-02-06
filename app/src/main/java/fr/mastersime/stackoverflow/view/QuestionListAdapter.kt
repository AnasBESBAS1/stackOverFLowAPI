


package fr.mastersime.stackoverflow.view

import android.Manifest
import android.content.pm.PackageManager
import android.telephony.SmsManager
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fr.mastersime.stackoverflow.data.Question
import fr.mastersime.stackoverflow.data.QuestionDiffCallback
import fr.mastersime.stackoverflow.databinding.ViewHolderQuestionsBinding

class QuestionListAdapter ( val sendMessage : () -> Unit) : ListAdapter<Question, QuestionsViewHolder>(QuestionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionsViewHolder {
        val binding = ViewHolderQuestionsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return QuestionsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuestionsViewHolder, position: Int) {
        val context = holder.binding.root.context
        val item = getItem(position)
        holder.binding.title.text = item.title
        holder.binding.answerCount.text = item.answerCount.toString()
        holder.binding.sendBtn.setOnClickListener {
            sendMessage()
        }

    }

}

