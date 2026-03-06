package com.example.myvideogames

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ConsoleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name: TextView = itemView.findViewById(R.id.console_name)
    val platform: TextView = itemView.findViewById(R.id.console_platform)
    val manufacturer: TextView = itemView.findViewById(R.id.console_manufacturer)
    val status: TextView = itemView.findViewById(R.id.console_status)
}