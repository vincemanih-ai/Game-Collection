package com.example.myvideogames

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class GamesAdapter(
    private val onItemClick: (Game) -> Unit,
    private val onLongClick: (Game) -> Unit
) : ListAdapter<Game, GamesAdapter.GameViewHolder>(GameDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_game, parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textTitle: TextView = itemView.findViewById(R.id.text_game_title)
        private val textPlatform: TextView = itemView.findViewById(R.id.text_game_platform)
        private val textStatus: TextView = itemView.findViewById(R.id.text_game_status)

        fun bind(game: Game) {
            textTitle.text = game.title
            textPlatform.text = game.platform ?: "Non renseigné"
            textStatus.text = game.status ?: "Non renseigné"

            itemView.setOnClickListener { onItemClick(game) }
            itemView.setOnLongClickListener {
                onLongClick(game)
                true
            }
        }
    }

    class GameDiffCallback : DiffUtil.ItemCallback<Game>() {
        override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem == newItem
        }
    }
}