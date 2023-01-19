package com.example.finalexam.ui.level_selection

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.finalexam.databinding.FragmentLevelSelectionBinding

class LevelSelectionFragment : Fragment(), View.OnClickListener {
    private lateinit var currentView: View
    private lateinit var binding: FragmentLevelSelectionBinding
    private lateinit var scienceBtn: TextView
    private lateinit var geographyBtn: TextView
    private lateinit var historyBtn: TextView
    private lateinit var musicBtn: TextView
    private lateinit var artsBtn: TextView
    private lateinit var moviesBtn: TextView
    private lateinit var btnList: List<TextView>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLevelSelectionBinding.inflate(layoutInflater, container, false)
        setCategoryListeners()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentView = view

        binding.menuButton.setOnClickListener {
            val action = LevelSelectionFragmentDirections.actionLevelSelectionFragmentToMenuFragment()
            view.findNavController().navigate(action)
        }
    }

    private fun onCategoryClick(category: String) {
        val action =
            LevelSelectionFragmentDirections.actionLevelSelectionFragmentToGameFragment(category)
        currentView.findNavController().navigate(action)
    }

    private fun setCategoryListeners() {
        scienceBtn = binding.science
        geographyBtn = binding.geography
        historyBtn = binding.history
        musicBtn = binding.music
        artsBtn = binding.arts
        moviesBtn = binding.movies
        btnList = listOf(scienceBtn, geographyBtn, historyBtn, musicBtn, artsBtn, moviesBtn)
        btnList.forEach {
            it.setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        if (v is TextView) {
            onCategoryClick(v.text.toString())
        }
    }

}