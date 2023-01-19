package com.example.finalexam.ui.game

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.finalexam.R
import com.example.finalexam.databinding.FragmentGameBinding
import com.example.finalexam.models.Question
import com.example.finalexam.services.api.RestClient
import retrofit2.Call
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class GameFragment : Fragment(), View.OnClickListener {
    private lateinit var currentView: View
    private lateinit var questions: ArrayList<Question>
    private lateinit var binding: FragmentGameBinding
    private lateinit var category: String
    private lateinit var btn1: TextView
    private lateinit var btn2: TextView
    private lateinit var btn3: TextView
    private lateinit var btn4: TextView
    private lateinit var btnList: List<TextView>
    private var currentQuestionIdx = 0
    private var correctAnswerAmount = 0
    private var amountOfQuestions = 10
    private var isWaiting = false
    private val whiteColor = Color.parseColor("#FFFFFF")
    private val blackColor = Color.parseColor("#000000")
    private val roundedContainerRed = R.drawable.rounded_container_red
    private val roundedContainerGreen = R.drawable.rounded_container_green
    private val roundedContainer = R.drawable.rounded_container



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGameBinding.inflate(layoutInflater, container, false)
        setAnswerListeners()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        currentView = view
    }

    private fun init() {
        registerBackButtonListener()
        category = arguments?.getString("category").toString()

        RestClient.quizService.getQuestions(category).enqueue(
            object : retrofit2.Callback<ArrayList<Question>> {
                override fun onResponse(
                    call: Call<ArrayList<Question>>,
                    response: Response<ArrayList<Question>>,
                ) {
                    val responseQuestions = response.body()
                    questions = responseQuestions ?: ArrayList()
                    if (questions.isNotEmpty()) {
                        setupQuestion()
                    }
                }

                override fun onFailure(call: Call<ArrayList<Question>>, t: Throwable) {
                    Log.d("TAG", t.toString())
                }
            },
        )
    }

    private fun setAnswerListeners() {
        btn1 = binding.root.findViewById(R.id.answer_topleft)
        btn2 = binding.root.findViewById(R.id.answer_topright)
        btn3 = binding.root.findViewById(R.id.answer_bottomleft)
        btn4 = binding.root.findViewById(R.id.answer_bottomright)
        btnList = listOf(btn1, btn2, btn3, btn4)
        btnList.forEach {
            it.setOnClickListener(this)
        }
    }

    private fun setupQuestion() {
        if (currentQuestionIdx < amountOfQuestions) {
            val currentQuestion: Question = questions[currentQuestionIdx]
            val allAnswers: ArrayList<String> = arrayListOf()
            allAnswers.addAll(currentQuestion.incorrectAnswers)
            allAnswers.add(currentQuestion.correctAnswer)
            allAnswers.shuffle()

            binding.questionText.text = currentQuestion.question

            for (i in 0..3) {
                btnList[i].text = allAnswers[i]
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun onAnswer(view: View) {
        if (isWaiting || !this::questions.isInitialized) return

        if (view is TextView) {
            isWaiting = true

            val text = view.text.toString()
            val currentQuestion: Question = questions[currentQuestionIdx]
            view.setTextColor(whiteColor)
            var correctAnswerBtnIdx = 0
            if (text == currentQuestion.correctAnswer) {
                correctAnswerAmount += 1
                view.setBackgroundResource(roundedContainerGreen)
            } else {
                view.setBackgroundResource(roundedContainerRed)
                btnList.forEach {
                    if (it.text.toString() == questions[currentQuestionIdx].correctAnswer) {
                        it.setBackgroundResource(roundedContainerGreen)
                        correctAnswerBtnIdx = btnList.indexOf(it)
                    }
                }

            }
            // Display next question
            handleDelayedQuestionDisplay(correctAnswerBtnIdx, view)


        }
    }

    @SuppressLint("SetTextI18n")
    private fun handleDelayedQuestionDisplay(correctAnswerBtnIdx: Int, view: TextView) {
        Handler().postDelayed(
            object : TimerTask() {
                override fun run() {
                    currentQuestionIdx += 1
                    if (currentQuestionIdx < amountOfQuestions) {
                        binding.textLevels.text =
                            "Question ${currentQuestionIdx + 1}/$amountOfQuestions"
                    }
                    isWaiting = false
                    view.setBackgroundResource(roundedContainer)
                    view.setTextColor(blackColor)
                    btnList[correctAnswerBtnIdx].setBackgroundResource(roundedContainer)
                    tryEndGame()
                    setupQuestion()
                }
            },
            1000,
        )
    }

    private fun tryEndGame() {
        if (currentQuestionIdx == amountOfQuestions) {
            val action =
                GameFragmentDirections.actionGameFragmentToGameEndFragment(
                    category,
                    correctAnswerAmount,
                )
            currentView.findNavController().navigate(action)
            return
        }
    }

    // On back button click navigates to menu
    private fun registerBackButtonListener() {
        binding.btnBack.setOnClickListener {
            val action =
                GameFragmentDirections.actionGameFragmentToLevelSelectionFragment()
            currentView.findNavController().navigate(action)
        }
    }

    override fun onClick(v: View?) {
        if (v is TextView) {
            onAnswer(v)
        }
    }
}