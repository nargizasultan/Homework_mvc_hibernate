package thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thymeleaf.models.Student;
import thymeleaf.models.Teacher;
import thymeleaf.services.TeacherService;

import java.util.List;


@Controller
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/find/by/{courseId}")
    public String findTeacherByCourseId(@PathVariable Long courseId, Model model){
        List<Teacher> teacher = teacherService.findByCourseId(courseId);
        model.addAttribute("courseId", courseId);
        model.addAttribute("teacher", teacher);
        return "teachers/all-teachers";
    }
    @GetMapping("/save/{courseId}")
    public String showTeacherSavePage(@PathVariable Long courseId, Model model){
        model.addAttribute("courseId", courseId);
        model.addAttribute("emptyTeacher", new Teacher());
        return "teachers/save-new-teacher";
    }
    @PostMapping("/save/{courseId}")
    public String saveTeacher(@PathVariable Long courseId, Teacher teacher){
        teacherService.save(teacher, courseId);
        return "redirect:/api/teachers/find/by/"+courseId;
    }

    @GetMapping("/update/{teacherId}")
    public String updateTeacher(Model model, @PathVariable Long teacherId){
        Teacher teacher = teacherService.findById(teacherId);

        model.addAttribute("updateTeacher", teacher);
        return "teachers/update-teacher";
    }

    @PostMapping ("/update/{teacherId}")
    public String update(Teacher teacher, @PathVariable Long teacherId){

        Teacher byId = teacherService.findById(teacherId);

        Long id = byId.getCourse().getId();

        teacherService.update(teacherId, teacher);

        return "redirect:/api/teachers/find/by/" + id;
    }

    @GetMapping("/delete/{teacherId}")
    public String delete(@PathVariable Long teacherId){
        Teacher byId = teacherService.findById(teacherId);
        Long id = byId.getCourse().getId();
        teacherService.removeById(teacherId);
        return "redirect:/api/teachers/find/by/"+id;

    }


}
