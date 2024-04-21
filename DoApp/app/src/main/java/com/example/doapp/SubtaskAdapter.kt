package com.example.doapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SubtaskAdapter(private val subtasks: List<Subtask>) : RecyclerView.Adapter<SubtaskAdapter.SubtaskViewHolder>() {

    inner class SubtaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val subtaskNameTextView: TextView = itemView.findViewById(R.id.subtaskName)
        val subtaskCheckBox: CheckBox = itemView.findViewById(R.id.addSubtaskButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubtaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.subtask_details, parent, false)
        return SubtaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubtaskViewHolder, position: Int) {
        val subtask = subtasks[position]
        holder.subtaskNameTextView.text = subtask.name
        holder.subtaskCheckBox.isChecked = subtask.isCompleted
    }

    override fun getItemCount(): Int {
        return subtasks.size
    }
}
