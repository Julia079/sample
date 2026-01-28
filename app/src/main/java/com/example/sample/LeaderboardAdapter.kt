package com.example.sample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LeaderboardAdapter(private val leaderboardData: List<Pair<String, Int>>) : RecyclerView.Adapter<LeaderboardAdapter.LeaderboardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.leaderboard_item, parent, false)
        return LeaderboardViewHolder(view)
    }

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {
        val (nickname, score) = leaderboardData[position]
        holder.rankTextView.text = (position + 1).toString()
        holder.nicknameTextView.text = nickname
        holder.scoreTextView.text = "Score: $score"
    }

    override fun getItemCount() = leaderboardData.size

    class LeaderboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rankTextView: TextView = itemView.findViewById(R.id.rank_text_view)
        val nicknameTextView: TextView = itemView.findViewById(R.id.nickname_text_view)
        val scoreTextView: TextView = itemView.findViewById(R.id.score_text_view)
    }
}
