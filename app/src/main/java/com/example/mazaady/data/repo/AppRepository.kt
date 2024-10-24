package com.example.mazaady.data.repo

import com.example.mazaady.R
import com.example.mazaady.data.remote.ApiService
import com.example.mazaady.data.model.home.Course
import com.example.mazaady.data.model.home.CourseModel
import com.example.mazaady.data.model.home.LiveStream
import com.example.mazaady.utils.Constants
import com.example.mazaady.utils.wrapResponseWithFlow
import com.example.mazaady.utils.wrapWithFlow
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun getAllCoursesNames() = wrapWithFlow { getAllCoursesAsList() }
    fun getAllPeoples() = wrapWithFlow { getAllPeopleAsList() }
    fun getAllCoursesDetails() = wrapWithFlow { getAllCoursesDetailsAsList() }

    private fun getAllCoursesAsList(): List<Course> {
        val courses = listOf(
            Course(1, "All"),
            Course(2, "Ui/Ux"),
            Course(3, "Web"),
            Course(4, "Android"),
            Course(5, "Java"),
            Course(6, "Network")
        )
        return (courses)
    }

    private fun getAllPeopleAsList(): List<LiveStream> {
        return listOf(
            LiveStream(1, R.drawable.avatar_1),
            LiveStream(2, R.drawable.avatar_2),
            LiveStream(3, R.drawable.avatar_3),
            LiveStream(4, R.drawable.avatar_4),
            LiveStream(5, R.drawable.avatar_1),
            LiveStream(6, R.drawable.avatar_3)
        )
    }

    private fun getAllCoursesDetailsAsList(): List<CourseModel> {
        return listOf(
            CourseModel(
                courseName = "Paython",
                instructorId = 1,
                courseImg = R.drawable.img_test,
                courseLessons = 6,
                courseTitle = "Step design sprint for beginner",
                instructorImage = R.drawable.img_test,
                instructorName = "Mr-Michel Ana",
                instructorTitle = "Developer",
                courseTime = "5h:30m", isFree = true
            ),
            CourseModel(
                courseName = "UI/UX",
                instructorId = 2,
                courseImg = R.drawable.img_test,
                courseLessons = 6,
                courseTitle = "Step design sprint for beginner",
                instructorImage = R.drawable.img_test,
                instructorName = "Mr-Hossma Khedr",
                instructorTitle = "Engineer",
                courseTime = "15h:30m", isFree = true
            ),
            CourseModel(
                courseName = "Android",
                instructorId = 3,
                courseImg = R.drawable.img_test,
                courseLessons = 6,
                courseTitle = "Step design sprint for beginner",
                instructorImage = R.drawable.img_test,
                instructorName = "Mr-Mohamed Khaled",
                instructorTitle = "Web Developer",
                courseTime = "23h:30m", isFree = true
            ),
            CourseModel(
                courseName = "Java",
                instructorId = 4,
                courseImg = R.drawable.img_test,
                courseLessons = 2,
                courseTitle = "Step design sprint for beginner",
                instructorImage = R.drawable.img_test,
                instructorName = "Eng-Ahmed Sayed",
                instructorTitle = "Engineer",
                courseTime = "10h:30m", isFree = true
            )
        )

    }


    fun getAllCategories() =
        wrapResponseWithFlow { apiService.getAllCategories(Constants.PRIVATE_KEY) }

    fun getAllProperties(id: Int) = wrapResponseWithFlow {
        apiService.getAllProperties(
            subCategoriesId = id,
            Constants.PRIVATE_KEY
        )
    }

    fun getChildOptions(optionId: Int) =
        wrapResponseWithFlow { apiService.getChildOptions(optionId, Constants.PRIVATE_KEY) }


}