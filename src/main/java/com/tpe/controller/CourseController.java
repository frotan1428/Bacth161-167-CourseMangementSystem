package com.tpe.controller;


import com.tpe.domain.Course;
import com.tpe.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/courses")//http://localhost:8080/MvcPractice/courses
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/form")//http://localhost:8080/MvcPractice/courses/form

    public String showFormCourse(@ModelAttribute("course") Course course){
        return "courseForm";

    }

    @PostMapping("/saveCourse")////http://localhost:8080/MvcPractice/courses/saveCourse

    public String createCourse(@Valid @ModelAttribute Course course , BindingResult bindingResult ){

        if (bindingResult.hasErrors()){
            return "courseForm";
        }

        courseService.saveCourse(course);

        return  "redirect:/courses";

    }

    @GetMapping////http://localhost:8080/MvcPractice/courses

    public ModelAndView getCourses(){
      List<Course> courseList = courseService.getAllCourse();

      ModelAndView mav=new ModelAndView();
      mav.addObject("courses" ,courseList);
      mav.setViewName("courses");
      return mav;

    }




}
