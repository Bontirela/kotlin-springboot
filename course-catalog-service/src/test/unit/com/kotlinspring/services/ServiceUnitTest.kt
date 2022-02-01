package com.kotlinspring.services

import com.kotlinspring.entity.Course
import com.kotlinspring.entity.Instructor
import com.kotlinspring.repository.CourseRepository
import com.kotlinspring.service.CourseService
import com.kotlinspring.service.InstructorService
import com.kotlinspring.util.courseDTO
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

@ExtendWith(MockKExtension::class)
class ServiceUnitTest(){

    @Mock
    var courseRepository: CourseRepository=mockk<CourseRepository>(relaxUnitFun = true)

    @Mock
    var instructorService: InstructorService=mockk<InstructorService>(relaxUnitFun = true)

    var courseService=CourseService(courseRepository,instructorService)


    @Test
    fun `add course service`(){
        //GIVEN
        val instructor=Instructor(id = 1, name = "MARK")
        val course=Course(id = 1,"SPRINGBOOT","PROGRAMMING",instructor)
        every{instructorService.findInstructorById(any())} returns  Optional.ofNullable(instructor)
        every { courseRepository.save(any())} returns course

        //WHEN

        //   val result= courseService.addCourse2(courseDTO(name = course.name, category = course.category, instructorId = course.instructor?.id))

        val result = CourseService(courseRepository,instructorService).addCourse(courseDTO(name = course.name, category = course.category, instructorId = course.instructor?.id))

        //THEN
        assertEquals(1,result.id)
    }
}