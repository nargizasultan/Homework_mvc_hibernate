package thymeleaf.services;

import org.springframework.stereotype.Service;
import thymeleaf.models.Group;
import thymeleaf.repositories.GroupRepository;

import java.util.List;
import java.util.UUID;
@Service
public class GroupService {
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }
    public void save(Group group) {
        groupRepository.save(group);
    }


    public Group findById(UUID groupID) {
        return groupRepository.findById(groupID);
    }


    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    public void removeById(UUID groupId) {
        groupRepository.removeById(groupId);
    }
}
