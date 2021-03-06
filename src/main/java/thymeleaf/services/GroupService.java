package thymeleaf.services;

import org.springframework.stereotype.Service;
import thymeleaf.models.Course;
import thymeleaf.models.Group;
import thymeleaf.repositories.CourseRepository;
import thymeleaf.repositories.GroupRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;

    public GroupService(GroupRepository groupRepository, CourseRepository courseRepository) {
        this.groupRepository = groupRepository;
        this.courseRepository = courseRepository;
    }
    @Transactional
    public void save(Group group, Long courseId) {
        Course course = courseRepository.findById(courseId);
        group.setCourse(course);
        course.setGroup(group);
        groupRepository.save(group);
    }

    @Transactional
    public Group findById(Long groupID) {
        return groupRepository.findById(groupID);
    }

    @Transactional
    public List<Group> findAll() {
        return groupRepository.findAll();
    }
    @Transactional
    public void removeById(Long groupId) {
        groupRepository.removeById(groupId);
    }
    @Transactional
    public List<Group> findByCourseId(Long courseId) {
        return groupRepository.findByCourseId(courseId);
    }
    @Transactional
    public void update(Long groupId, Group group) {
        groupRepository.update(groupId, group);
    }
}
