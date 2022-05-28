package thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import thymeleaf.models.Group;
import thymeleaf.services.GroupService;

import java.util.List;

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
    @GetMapping
    public String findAll(){
        return "all-groups";
    }
    @GetMapping("/save")
    public String saveCompany(Model model){
        model.addAttribute("emptyGroup", new Group());
        return "save-new-group";
    }
    @PostMapping("/save")
    public String save(Group group){
        groupService.save(group);
        return "redirect:/api/groups";
    }
}
