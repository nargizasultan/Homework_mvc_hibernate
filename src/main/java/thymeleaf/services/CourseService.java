package thymeleaf.services;

import org.springframework.stereotype.Service;
import thymeleaf.models.Course;
import thymeleaf.repositories.CourseRepository;

import java.util.List;
import java.util.UUID;
@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    public void save(Course course) {
        courseRepository.save(course);
    }

    public Course findById(UUID courseId) {
        return courseRepository.findById(courseId);
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public void deleteById(UUID courseId) {
        courseRepository.deleteById(courseId);
    }
}
