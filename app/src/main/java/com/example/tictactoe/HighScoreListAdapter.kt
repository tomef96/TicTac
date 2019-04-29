package com.example.tictactoe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tictactoe.data.HighScore

class HighScoreListAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<HighScoreListAdapter.HighScoreViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var highScores = emptyList<HighScore>()

    inner class HighScoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.highscore_recycler_view_item_name)
        val score: TextView = itemView.findViewById(R.id.highscore_recycler_view_item_score)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighScoreViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item_highscore, parent, false)
        return HighScoreViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HighScoreViewHolder, position: Int) {
        val current = highScores[position]
        holder.name.text = current.name
        holder.score.text = current.score.toString()
    }

    override fun getItemCount() = highScores.size

    internal fun setHighScores(highScores: List<HighScore>) {
        this.highScores = highScores
        notifyDataSetChanged()
    }
}