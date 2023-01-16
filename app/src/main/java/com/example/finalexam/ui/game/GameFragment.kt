package com.example.finalexam.ui.game

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.finalexam.databinding.FragmentGameBinding
import com.example.finalexam.models.Question
import com.example.finalexam.services.api.RestClient
import com.example.finalexam.ui.menu.MenuFragmentDirections
import retrofit2.Call
import retrofit2.Response

class GameFragment : Fragment() {
    private lateinit var questions: ArrayList<Question>
    private lateinit var binding: FragmentGameBinding
    private var currentQuestionIdx: Int = 0
    private lateinit var currentView: View


    companion object {
        fun newInstance() = GameFragment()
    }

    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGameBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        currentView = view
    }

    private fun init() {
        RestClient.quizService.getQuestions().enqueue(
            object : retrofit2.Callback<ArrayList<Question>> {
                override fun onResponse(
                    call: Call<ArrayList<Question>>,
                    response: Response<ArrayList<Question>>,
                ) {
                    val responseQuestions = response.body()
                    questions = responseQuestions ?: ArrayList<Question>()
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

    private fun setupQuestion() {
        val currentQuestion: Question = questions[currentQuestionIdx]
        val allAnswers: ArrayList<String> = arrayListOf()
        allAnswers.addAll(currentQuestion.incorrectAnswers)
        allAnswers.add(currentQuestion.correctAnswer)
        allAnswers.shuffle()

        binding.questionText.text = currentQuestion.question

        binding.answerTopleft.text = allAnswers[0]
        binding.answerTopright.text = allAnswers[1]
        binding.answerBottomleft.text = allAnswers[2]
        binding.answerBottomright.text = allAnswers[3]


        binding.answerTopleft.setOnClickListener {
            onAnswer(allAnswers[0])
        }
        binding.answerTopright.setOnClickListener {
            onAnswer(allAnswers[1])
        }
        binding.answerBottomleft.setOnClickListener {
            onAnswer(allAnswers[2])
        }
        binding.answerBottomright.setOnClickListener {
            onAnswer(allAnswers[3])
        }

    }

    private fun onAnswer(text: String) {
        val currentQuestion: Question = questions[currentQuestionIdx]
        if (text == currentQuestion.correctAnswer) {
            ///ToDo..
        }
        currentQuestionIdx += 1;
        if(currentQuestionIdx == 10) {
            val action = GameFragmentDirections.actionGameFragmentToGameEndFragment()
            currentView.findNavController().navigate(action)
        } else {
            setupQuestion();
        }
    }
}