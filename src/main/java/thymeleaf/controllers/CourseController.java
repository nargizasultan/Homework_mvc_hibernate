package thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thymeleaf.models.Company;
import thymeleaf.models.Course;
import thymeleaf.services.CompanyService;
import thymeleaf.services.CourseService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;
    private final CompanyService companyService;

    public CourseController(CourseService courseService, CompanyService companyService) {
        this.courseService = courseService;
        this.companyService = companyService;
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
        return "courses/all-courses";
    }

    @GetMapping("/save/{companyId}")
    public String showCourseSavePage(@PathVariable UUID companyId, Model model) {
        model.addAttribute("companyId", companyId);
        model.addAttribute("emptyCourse", new Course());
        return "courses/save-new-course";
    }
    @PostMapping("/save/{companyId}")
    public String saveCourse(Course course, @PathVariable UUID companyId){
        courseService.save(course, companyId);
        return "redirect:/api/courses/find/by/"+companyId;

    }
    @GetMapping("/update/{courseId}")
    public String updateCourse(Model model, @PathVariable UUID courseId){
        Course course = courseService.findById(courseId);
        model.addAttribute("updateCourse", course);
        return "courses/update-course";
    }

    @PostMapping ("/update/{courseId}")
    public String update(Course course, @PathVariable UUID courseId){
        Course byId = courseService.findById(courseId);
        UUID id = byId.getCompany().getId();
        courseService.update(courseId, course);
        return "redirect:/api/courses/find/by/" + id;
    }
    @GetMapping("/delete/{courseId}")
    public String delete(@PathVariable UUID courseId){
        Course course = courseService.findById(courseId);
        UUID id = course.getCompany().getId();
        courseService.deleteById(courseId);
        return "redirect:/api/courses/find/by/"+id;
    }
}
