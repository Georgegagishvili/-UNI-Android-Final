package com.example.finalexam.ui.game_end

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.finalexam.R
import com.example.finalexam.databinding.FragmentGameEndBinding
import com.example.finalexam.db.AppDatabase
import com.example.finalexam.db.Result
import com.example.finalexam.services.notifications.NotificationHelper
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GameEndFragment : Fragment() {
    private lateinit var binding: FragmentGameEndBinding
    private val args: GameEndFragmentArgs by navArgs()
    private lateinit var appDB: AppDatabase
    private var correctAnswerAmount: Int = 0
    private var userName = ""
    private var category = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGameEndBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        addButtonListeners()
        saveData()
        createCongratulationNotification()
    }

    @SuppressLint("SetTextI18n")
    private fun init() {
        val prefs: SharedPreferences? =
            activity?.getSharedPreferences("User", Context.MODE_PRIVATE)
        correctAnswerAmount = args.correctAnswerAmount
        category = args.category
        binding.userName.text = "Nice try $userName!"
        binding.correctAnswers.text = "$correctAnswerAmount/10"
        userName = prefs!!.getString(getString(R.string.username), null).toString()
    }

    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("SetTextI18n")
    private fun saveData() {
        // Add points to the user in db
        if (correctAnswerAmount > 2) {
            binding.resultText.text = "Good Job"
            appDB = AppDatabase.getDatabase(requireContext())
            var newResult = Result(
                null, userName, correctAnswerAmount
            )
            GlobalScope.launch(Dispatchers.IO) {
                val result: Result? = appDB.getResultDao().selectByUser(userName)

                if (result != null) {
                    val newScore = result.score + correctAnswerAmount
                    newResult = Result(result.id, userName, newScore)
                    appDB.getResultDao().update(newResult)
                } else {
                    appDB.getResultDao().insert(newResult)
                }
            }
        }
    }

    private fun addButtonListeners() {
        // Buttons
        binding.menuButton.setOnClickListener {
            val action =
                GameEndFragmentDirections.actionGameEndFragmentToMenuFragment2()
            binding.root.findNavController().navigate(action)
        }

        binding.restartButton.setOnClickListener {
            val action =
                GameEndFragmentDirections.actionGameEndFragmentToGameFragment(category)
            binding.root.findNavController().navigate(action)
        }
    }

    private fun createCongratulationNotification() {
        val notificationTitle: String?
        val notificationText: String?

        if (correctAnswerAmount > 2) {
            notificationTitle = "Congratulations"
            notificationText =
                "You have scored more than 2 points, you can view your result at leaderboard."
        } else {
            notificationTitle = "Nice try $userName"
            notificationText =
                "If you want your name to be displayed in leaderboards, score more than 2 points!"
        }

        NotificationHelper(binding.root.context).createNotification(
            notificationTitle,
            notificationText
        )
    }
}

