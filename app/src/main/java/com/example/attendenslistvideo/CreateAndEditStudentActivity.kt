package com.example.attendenslistvideo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

const val STUDENT_POSITION_KEY = "STUDENT_POSITION"
const val POSITION_NOT_SET = -1

class CreateAndEditStudentActivity : AppCompatActivity() {

    lateinit var nameEditText : EditText
    lateinit var classEditText : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_and_edit_student)

        nameEditText = findViewById(R.id.nameEditText)
        classEditText = findViewById(R.id.classEditText)
        val studentPosition = intent.getIntExtra(STUDENT_POSITION_KEY, POSITION_NOT_SET)
        val saveButton = findViewById<Button>(R.id.saveButton)

        if(studentPosition != POSITION_NOT_SET) {
            displayStudent(studentPosition)
            saveButton.setOnClickListener {
                editStudent(studentPosition)
            }
        } else {
            saveButton.setOnClickListener {
                addNewStudent()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        }

    fun displayStudent(position : Int) {
        val student = DataManager.students[position]

        nameEditText.setText(student.name)
        classEditText.setText(student.className)
    }
    fun editStudent(position : Int) {
        DataManager.students[position].name = nameEditText.text.toString()
        DataManager.students[position].className = classEditText.text.toString()

        finish()
    }

    fun addNewStudent() {
        val name = nameEditText.text.toString()
        val className = classEditText.text.toString()

        val student = Student(name, className)
        DataManager.students.add(student)
        finish()
    }
}