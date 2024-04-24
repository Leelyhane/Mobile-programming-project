package com.example.taskmanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment


class SubtaskDialogFragment : DialogFragment() {

    interface SubtaskListener {
        fun onSubtaskAdded(subtaskName: String)
    }

    var subtaskListener: SubtaskListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.subtask_dialog_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val addButton = view.findViewById<Button>(R.id.buttonAddSubtask)
        addButton.setOnClickListener {
            val subtaskName = view.findViewById<EditText>(R.id.editTextSubtaskName).text.toString()
            subtaskListener?.onSubtaskAdded(subtaskName)
            dismiss()
        }
    }
}
