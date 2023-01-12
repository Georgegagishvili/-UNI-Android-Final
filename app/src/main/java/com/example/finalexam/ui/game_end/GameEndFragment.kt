package com.example.finalexam.ui.game_end

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.finalexam.R

class GameEndFragment : Fragment() {

    companion object {
        fun newInstance() = GameEndFragment()
    }

    private lateinit var viewModel: GameEndViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_game_end, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GameEndViewModel::class.java)
        // TODO: Use the ViewModel
    }

}