package thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import thymeleaf.models.Company;
import thymeleaf.models.Course;
import thymeleaf.services.CourseService;

import java.util.List;

@Controller
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @ModelAttribute("courses")
    public List<Course>courses(){
        return courseService.findAll();
    }
    @GetMapping
    public String findAllCourses(){
        return "all-courses";
    }
    @GetMapping("/save")
    public String saveCourse(Model model){
        model.addAttribute("emptyCourse", new Course());
        return "save-new-course";
    }
    @PostMapping("/save")
    public String save(Course course){
        courseService.save(course);
        return "redirect:/api/courses";
    }





}
