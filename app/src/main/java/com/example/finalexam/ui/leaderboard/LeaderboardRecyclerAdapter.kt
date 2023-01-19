package com.example.finalexam.ui.leaderboard
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalexam.R
import com.example.finalexam.db.Result
import com.squareup.picasso.Picasso

class LeaderboardRecyclerAdapter(private val results: List<Result>) :
    RecyclerView.Adapter<LeaderboardRecyclerAdapter.ResultViewHolder>() {

    override fun getItemCount() = results.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ResultViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val result = results[position]
        holder.setData(result)
    }

    inner class ResultViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val userTextView = itemView.findViewById<TextView>(R.id.user)
        private val pointTextView = itemView.findViewById<TextView>(R.id.points)

        @SuppressLint("SetTextI18n")
        fun setData(result: Result) {
            userTextView.text = result.user
            pointTextView.text = result.score.toString() + " Points"
        }
    }
}