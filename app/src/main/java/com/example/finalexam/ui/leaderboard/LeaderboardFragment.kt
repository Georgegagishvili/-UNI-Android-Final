package com.example.finalexam.ui.leaderboard

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalexam.R
import com.example.finalexam.databinding.FragmentGameBinding
import com.example.finalexam.databinding.FragmentLeaderboardBinding
import com.example.finalexam.db.AppDatabase
import com.example.finalexam.ui.level_selection.LevelSelectionFragmentDirections
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LeaderboardFragment : Fragment() {
    private lateinit var binding: FragmentLeaderboardBinding
    private lateinit var appDB: AppDatabase
    private lateinit var recyclerView: RecyclerView


    companion object {
        fun newInstance() = LeaderboardFragment()
    }

    private lateinit var viewModel: LeaderboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentLeaderboardBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LeaderboardViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Menu button
        binding.menuButton.setOnClickListener {
            val action = LeaderboardFragmentDirections.actionLeaderboardFragmentToMenuFragment()
            view.findNavController().navigate(action)
        }

        fillScreenWithResults()
    }

    private fun fillScreenWithResults() {
        val recyclerView = binding.recyclerView
        var adapter: LeaderboardRecyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Read results from db
        appDB = AppDatabase.getDatabase(requireContext())
        val results = appDB.getResultDao().selectAll()

        adapter = LeaderboardRecyclerAdapter(results)
        recyclerView.adapter = adapter
    }

}