package thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import thymeleaf.models.Student;
import thymeleaf.services.StudentService;

import java.util.List;


@Controller
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @ModelAttribute("students")
    public List<Student> courses(){
        return studentService.findAll();
    }
    @GetMapping
    public String findAllStudents(){
        return "all-student";
    }
    @GetMapping("/save")
    public String saveStudent(Model model){
        model.addAttribute("emptyStudent", new Student());
        return "courses/save-new-course";
    }
    @PostMapping("/save")
    public String save(Student student){
        studentService.save(student);
        return "redirect:/api/students";
    }
}
