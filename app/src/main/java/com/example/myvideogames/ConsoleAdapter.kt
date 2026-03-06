package com.example.myvideogames

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ConsolesAdapter(
    private val onItemClick: (Console) -> Unit,
    private val onLongClick: (Console) -> Unit
) : ListAdapter<Console, ConsolesAdapter.ConsoleViewHolder>(ConsoleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsoleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_console, parent, false)
        return ConsoleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ConsoleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ConsoleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textName: TextView = itemView.findViewById(R.id.text_console_name)
        private val textModel: TextView = itemView.findViewById(R.id.text_console_model)
        private val textStatus: TextView = itemView.findViewById(R.id.text_console_status)

        fun bind(console: Console) {
            textName.text = console.name
            textModel.text = "${console.model ?: ""} • ${console.manufacturer ?: ""}".trim()
            textStatus.text = console.status ?: "Non renseigné"

            itemView.setOnClickListener { onItemClick(console) }
            itemView.setOnLongClickListener {
                onLongClick(console)
                true
            }
        }
    }

    class ConsoleDiffCallback : DiffUtil.ItemCallback<Console>() {
        override fun areItemsTheSame(oldItem: Console, newItem: Console): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Console, newItem: Console): Boolean = oldItem == newItem
    }
}