package com.example.finalexam.ui.menu

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.finalexam.R

class MenuFragment : Fragment() {

    companion object {
        fun newInstance() = MenuFragment()
    }

    private lateinit var viewModel: MenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val startButton = view.findViewById<TextView>(R.id.start_button);
        val leaderboardButton = view.findViewById<TextView>(R.id.leaderboard_button)

        startButton.setOnClickListener{
           val action = MenuFragmentDirections.actionMenuFragmentToLevelSelectionFragment()
            view.findNavController().navigate(action)
        }

        leaderboardButton.setOnClickListener{
            val action = MenuFragmentDirections.actionMenuFragmentToLeaderboardFragment()
            view.findNavController().navigate(action)
        }
    }

}