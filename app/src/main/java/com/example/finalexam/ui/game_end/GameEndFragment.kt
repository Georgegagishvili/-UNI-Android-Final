package com.example.finalexam.ui.game_end

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.finalexam.R
import com.example.finalexam.databinding.FragmentGameBinding
import com.example.finalexam.databinding.FragmentGameEndBinding

class GameEndFragment : Fragment() {
    private lateinit var binding: FragmentGameEndBinding
    private val args : GameEndFragmentArgs by navArgs()

    companion object {
        fun newInstance() = GameEndFragment()
    }

    private lateinit var viewModel: GameEndViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding =  FragmentGameEndBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GameEndViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val correctAnswerAmount = args.correctAnswerAmount
        binding.correctAnswers.text = correctAnswerAmount.toString()
    }

}