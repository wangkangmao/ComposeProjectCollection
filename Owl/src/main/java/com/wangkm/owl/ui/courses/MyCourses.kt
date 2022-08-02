package com.wangkm.owl.ui.courses

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wangkm.owl.model.Course
import com.wangkm.owl.model.courses
import com.wangkm.owl.ui.common.CourseListItem
import com.wangkm.owl.ui.theme.BlueTheme

/**
 * @author: created by wangkm
 * @time: 2022/07/27 13:17
 * @desc：
 * @email: 1240413544@qq.com
 */

@Composable
fun MyCourses(
    courses: List<Course>,
    selectCourse: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        item {
            Spacer(Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
        }
        item {
            CoursesAppBar()
        }
        itemsIndexed(
            items = courses,
            key = { _, course -> course.id }
        ) { index, course ->
            MyCourse(
                course = course,
                index = index,
                selectCourse = selectCourse
            )
        }
    }
}

@Composable
fun MyCourse(
    course: Course,
    index: Int,
    selectCourse: (Long) -> Unit
) {
    Row(modifier = Modifier.padding(bottom = 8.dp)) {
        val stagger = if (index % 2 == 0) 72.dp else 16.dp
        Spacer(modifier = Modifier.width(stagger))
        CourseListItem(
            course = course,
            onClick = { selectCourse(course.id) },
            shape = RoundedCornerShape(topStart = 24.dp),
            modifier = Modifier.height(96.dp)
        )
    }
}

@Preview(name = "My Courses")
@Composable
private fun MyCoursesPreview() {
    BlueTheme {
        MyCourses(
            courses = courses,
            selectCourse = { }
        )
    }
}
