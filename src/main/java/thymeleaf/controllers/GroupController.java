package thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import thymeleaf.models.Group;
import thymeleaf.services.GroupService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/groups")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }


    @ModelAttribute("groups")
    public List<Group> findAllGroups(){
        return groupService.findAll();
    }
    @GetMapping("find/by/{courseId}")
    public String findAllGroupsByCourseId(@PathVariable UUID courseId, Model model) {

        List<Group> groups = groupService.findByCourseId(courseId);
        model.addAttribute("courses", groups);
        model.addAttribute("courseId", courseId);
        return "groups/all-groups";
    }

    @GetMapping("/save/{courseId}")
    public String showCourseSavePage(@PathVariable UUID courseId, Model model) {
        model.addAttribute("courseId", courseId);
        model.addAttribute("emptyGroup", new Group());
        return "groups/save-new-group";


    }
    @PostMapping("/save/{courseId}")
    public String saveCourse(Group group, @PathVariable UUID courseId){
        groupService.save(group, courseId);
        return "redirect:/api/groups/find/by/"+courseId;

    }




}
