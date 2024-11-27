package com.example.attendenslistvideo

object DataManager {
    val students = mutableListOf<Student>()

    init {
        createMockData()
    }

    fun createMockData() {
        students.add(Student("Frida", "APP24", true))
        students.add(Student("David", "APP24"))
        students.add(Student("Simon", "APP24"))
        students.add(Student("Kim", "APP24", true))
        students.add(Student("Robin", "APP24"))
        students.add(Student("Magnus", "APP24"))
    }
}
