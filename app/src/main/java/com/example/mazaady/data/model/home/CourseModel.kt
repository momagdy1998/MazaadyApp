package com.example.mazaady.data.model.home

data class CourseModel(
    val courseName: String,
    val courseImg: Int,
    val courseTitle: String,
    val instructorId: Int,
    val instructorName: String,
    val instructorTitle: String,
    val instructorImage: Int,
    val courseTime: String,
    val courseLessons: Int,
    val isFree: Boolean
) {
    fun getLessons() = "$courseLessons Lessons"
    fun isFreeCourse() = if (isFree)"Free" else "Paid"
}
