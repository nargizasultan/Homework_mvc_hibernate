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



}
