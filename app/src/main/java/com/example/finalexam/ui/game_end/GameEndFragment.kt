package com.example.finalexam.ui.game_end

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.finalexam.R
import com.example.finalexam.databinding.FragmentGameBinding
import com.example.finalexam.databinding.FragmentGameEndBinding
import com.example.finalexam.ui.game.GameFragmentDirections

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
        val category = args.category
        binding.correctAnswers.text = correctAnswerAmount.toString()

        binding.menuButton.setOnClickListener {
            val action =
                GameEndFragmentDirections.actionGameEndFragmentToMenuFragment2()
            view.findNavController().navigate(action)
        }

        binding.restartButton.setOnClickListener {
            val action =
                GameEndFragmentDirections.actionGameEndFragmentToGameFragment(category)
            view.findNavController().navigate(action)
        }

    }

}