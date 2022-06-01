package thymeleaf.services;

import org.springframework.stereotype.Service;
import thymeleaf.models.Group;
import thymeleaf.models.Student;
import thymeleaf.repositories.GroupRepository;
import thymeleaf.repositories.StudentRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public StudentService(StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }
    @Transactional
    public void save(Student student, UUID groupId) {
        Group group = groupRepository.findById(groupId);
        group.setStudent(student);
        student.setGroup(group);
        studentRepository.save(student);
    }

    @Transactional
    public Student findById(UUID studentId) {
        return studentRepository.findById(studentId);
    }

    @Transactional
    public List<Student> findAll() {
        return studentRepository.findAll();
    }
    @Transactional
    public void removeById(UUID studentId) {
        studentRepository.removeById(studentId);
    }
    @Transactional
    public List<Student> findByGroupId(UUID groupId) {
        return studentRepository.findByGroupId(groupId);
    }
    @Transactional
    public void update(UUID studentId, Student student) {
        studentRepository.update(studentId,student );
    }
}
