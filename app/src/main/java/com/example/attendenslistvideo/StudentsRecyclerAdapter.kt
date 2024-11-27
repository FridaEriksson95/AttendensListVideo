package com.example.attendenslistvideo

import android.content.ClipData.Item
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentsRecyclerAdapter(val context: Context, val students : List<Student>) : RecyclerView.Adapter<StudentsRecyclerAdapter.ViewHolder>() {

    var layoutinflater = LayoutInflater.from(context)

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)
        val classTextView = itemView.findViewById<TextView>(R.id.classTextView)
        val presentButton = itemView.findViewById<CheckBox>(R.id.checkBox)
        val deleteButton = itemView.findViewById<ImageButton>(R.id.deleteButton)
        var studentPosition = 0

        init {
            itemView.setOnClickListener {
                val intent = Intent(context,CreateAndEditStudentActivity::class.java)
                intent.putExtra(STUDENT_POSITION_KEY, studentPosition)

                context.startActivity(intent)
            }
            presentButton.setOnClickListener {
                DataManager.students[studentPosition].present = presentButton.isChecked
            }
            deleteButton.setOnClickListener{
                removeStudent(studentPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutinflater.inflate(R.layout.list_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun getItemCount() = students.size

    fun removeStudent(position: Int) {
        DataManager.students.removeAt(position)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = students[position]

        holder.nameTextView.text = student.name
        holder.classTextView.text = student.className
        holder.presentButton.isChecked = student.present
        holder.studentPosition = position
    }
}