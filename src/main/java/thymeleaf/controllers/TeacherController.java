package thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thymeleaf.models.Student;
import thymeleaf.models.Teacher;
import thymeleaf.services.TeacherService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
    @ModelAttribute("teachers")
    public List<Teacher>teachers(){
        return teacherService.findAll();
    }
    @GetMapping("/find/by/{courseId}")
    public String findTeacherByCourseId(@PathVariable UUID courseId, Model model){
        List<Teacher> teacher = teacherService.findByCourseId(courseId);
        model.addAttribute("courseId", courseId);
        model.addAttribute("teacher", teacher);
        return "teachers/all-teachers";
    }
    @GetMapping("/save/{courseId}")
    public String showTeacherSavePage(@PathVariable UUID courseId, Model model){
        model.addAttribute("courseId", courseId);
        model.addAttribute("emptyTeacher", new Teacher());
        return "teachers/save-new-teacher";
    }
    @PostMapping("/save/{courseId}")
    public String saveTeacher(@PathVariable UUID courseId, Teacher teacher){
        teacherService.save(teacher, courseId);
        return "redirect:/api/teachers/find/by/"+courseId;
    }

    @GetMapping("/update/{teacherId}")
    public String updateTeacher(Model model, @PathVariable UUID teacherId){
        Teacher teacher = teacherService.findById(teacherId);

        model.addAttribute("updateTeacher", teacher);
        return "teachers/update-teacher";
    }

    @PostMapping ("/update/{teacherId}")
    public String update(Teacher teacher, @PathVariable UUID teacherId){

        Teacher byId = teacherService.findById(teacherId);

        UUID id = byId.getCourse().getId();

        teacherService.update(teacherId, teacher);

        return "redirect:/api/teachers/find/by/" + id;
    }

    @GetMapping("/delete/{teacherId}")
    public String delete(@PathVariable UUID teacherId){
        Teacher byId = teacherService.findById(teacherId);
        UUID id = byId.getCourse().getId();
        teacherService.removeById(teacherId);
        return "api/teachers/find/by/"+id;

    }


}
