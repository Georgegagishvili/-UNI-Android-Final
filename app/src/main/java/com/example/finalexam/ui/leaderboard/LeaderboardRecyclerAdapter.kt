package com.example.finalexam.ui.leaderboard
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class LeaderboardRecyclerAdapter(private val data: Array<String>) :
    RecyclerView.Adapter<LeaderboardRecyclerAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
        }
    }

    override fun onCreateViewHolder(view: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

    }

    override fun getItemCount() = data.size
}