package thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thymeleaf.models.Student;
import thymeleaf.services.StudentService;

import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @ModelAttribute("studentList")
    public List<Student> courses(){
        return studentService.findAll();
    }
    @GetMapping("find/by/{groupId}")
    public String findAllStudentsByGroupId(@PathVariable UUID groupId, Model model){
        List<Student> students = studentService.findByGroupId(groupId);
        model.addAttribute("students", students);
        model.addAttribute("groupId", groupId);
        return "all-students";
    }
    @GetMapping("/save/{groupId}")
    public String showStudentSavePage(@PathVariable UUID groupId, Model model){
        model.addAttribute("emptyStudent", new Student());
        model.addAttribute("groupId", groupId);
        return "save-new-student";

    }
    @PostMapping("/save/{groupId}")
    public String saveStudent(@PathVariable UUID groupId, Student student){
        studentService.save(student, groupId);
        return "redirect:/api/students/find/by/"+groupId;

    }


}
