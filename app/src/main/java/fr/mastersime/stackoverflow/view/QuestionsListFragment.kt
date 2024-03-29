package fr.mastersime.stackoverflow.view

import android.Manifest
import android.Manifest.permission.SEND_SMS
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.telephony.SmsManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
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

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val questionsListViewModel: QuestionListViewModel by viewModels()

        val requestPermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    context?.let { questionsListViewModel.sendSMS(it) }
                } else {
                    Snackbar.make(
                        binding.recyclerView,
                        " Permission needed ",
                        Snackbar.LENGTH_LONG
                    ).setAction(" Go to settings ") {
                        startActivity(
                            Intent(
                                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.parse("package:" + requireActivity().packageName)
                            )
                        )
                    }.show()
                }
            }
        val questionListAdapter = QuestionListAdapter() {
            Log.d("param", "sent")
            when {
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    SEND_SMS
                ) == PackageManager.PERMISSION_GRANTED
                -> {
                    context?.let { questionsListViewModel.sendSMS(it) }
                }
                shouldShowRequestPermissionRationale(SEND_SMS) -> {
                    Snackbar.make(
                        binding.recyclerView,
                        "Permission is needed",
                        Snackbar.LENGTH_LONG
                    ).setAction("Allow") {
                        requestPermissionLauncher.launch(SEND_SMS)
                    }.show()
                }
                else -> {
                    requestPermissionLauncher.launch(SEND_SMS)
                }
            }
        }

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = questionListAdapter
        }
        // Testing
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
            } else {
                binding.swipeRefresh.isRefreshing = false
                val text = "Request Error!"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(context, text, duration)
                toast.show()
            }
        }


    }
}