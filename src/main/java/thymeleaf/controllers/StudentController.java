package thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thymeleaf.models.Group;
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

    @GetMapping("find/by/{groupId}")
    public String findAllStudentsByGroupId(@PathVariable Long groupId, Model model){
        List<Student> students = studentService.findByGroupId(groupId);
        model.addAttribute("students", students);
        model.addAttribute("groupId", groupId);
        return "students/all-students";
    }
    @GetMapping("/save/{groupId}")
    public String showStudentSavePage(@PathVariable Long groupId, Model model){
        model.addAttribute("emptyStudent", new Student());
        model.addAttribute("groupId", groupId);
        return "students/save-new-student";

    }
    @PostMapping("/save/{groupId}")
    public String saveStudent(@PathVariable Long groupId, Student student){
        studentService.save(student, groupId);
        return "redirect:/api/students/find/by/"+groupId;

    }

    @GetMapping("/update/{studentId}")
    public String updateStudent(Model model, @PathVariable Long studentId){
        Student student = studentService.findById(studentId);
        model.addAttribute("updateStudent", student);
        return "students/update-student";
    }

    @PostMapping ("/update/{studentId}")
    public String update(Student student, @PathVariable Long studentId){
        Student byId = studentService.findById(studentId);
        Long id = byId.getGroup().getId();

        studentService.update(studentId, student);

        return "redirect:/api/students/find/by/" + id;
    }
    @GetMapping ("/delete/{studentId}")
        public String delete(@PathVariable Long studentId){
        Student byId = studentService.findById(studentId);
        Long id = byId.getGroup().getId();
        studentService.removeById(studentId);
        return "redirect:/api/students/find/by/"+id;
    }


}
