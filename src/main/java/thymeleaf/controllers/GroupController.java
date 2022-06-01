package thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import thymeleaf.models.Course;
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
    @PostMapping("/saveGroup/{courseId}")
    public String saveGroup(Group group, @PathVariable UUID courseId){
        groupService.save(group, courseId);
        return "redirect:/api/groups/find/by/"+courseId;
    }

    @GetMapping("/update/{groupId}")
    public String updateGroup(Model model, @PathVariable UUID groupId){
        Group group = groupService.findById(groupId);
        model.addAttribute("updateGroup", group);
        return "groups/update-group";
    }

    @PostMapping ("/update/{groupId}")
    public String update(Group group, @PathVariable UUID groupId){

        Group byId = groupService.findById(groupId);
        UUID id = byId.getCourses().get(0).getId();
        groupService.update(groupId, group);

        return "redirect:/api/groups/find/by/" + id;
    }
}