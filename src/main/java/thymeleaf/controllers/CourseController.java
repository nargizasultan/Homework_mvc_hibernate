package thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thymeleaf.models.Course;
import thymeleaf.services.CourseService;

import java.util.List;
import java.util.UUID;

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
    @GetMapping("find/by/{companyId}")
    public String findAllCoursesByCompanyId(@PathVariable UUID companyId, Model model) {

        List<Course> courses = courseService.findByCompanyId(companyId);
        model.addAttribute("courses", courses);
        model.addAttribute("companyId", companyId);
        return "all-courses";
    }

    @GetMapping("/save/{companyId}")
    public String showCourseSavePage(@PathVariable UUID companyId, Model model) {
        model.addAttribute("companyId", companyId);
        model.addAttribute("emptyCourse", new Course());
        return "save-new-course";


    }
    @PostMapping("/save/{companyId}")
    public String saveCourse(Course course, @PathVariable UUID companyId){
        courseService.save(course, companyId);
        return "redirect:/api/courses/find/by/"+companyId;

    }






}
