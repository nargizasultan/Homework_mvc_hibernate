package thymeleaf.services;


import org.springframework.stereotype.Service;
import thymeleaf.models.Course;
import thymeleaf.models.Teacher;
import thymeleaf.repositories.CourseRepository;
import thymeleaf.repositories.TeacherRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    public TeacherService(TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }
    @Transactional
    public void save(Teacher teacher, UUID courseId) {
        Course course = courseRepository.findById(courseId);
        course.setTeacher(teacher);
        teacher.setCourse(course);
        teacherRepository.save(teacher);
    }

    @Transactional
    public Teacher findById(UUID teacherId) {
        return teacherRepository.findById(teacherId);
    }

    @Transactional
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }
    @Transactional
    public void removeById(UUID teacherId) {
        teacherRepository.removeById(teacherId);
    }
    @Transactional
    public List<Teacher> findByCourseId(UUID courseId) {
        return teacherRepository.findByCourseId(courseId);
    }
    @Transactional
    public void update(UUID teacherId, Teacher teacher) {
        teacherRepository.update(teacherId, teacher);
    }
}
