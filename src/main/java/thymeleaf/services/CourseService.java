package thymeleaf.services;

import org.springframework.stereotype.Service;
import thymeleaf.models.Company;
import thymeleaf.models.Course;
import thymeleaf.repositories.CompanyRepository;
import thymeleaf.repositories.CourseRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;

    public CourseService(CourseRepository courseRepository, CompanyRepository companyRepository) {
        this.courseRepository = courseRepository;
        this.companyRepository = companyRepository;
    }
    @Transactional
    public void save(Course course, Long companyId) {
        Company company = companyRepository.findById(companyId);
        company.setCourse(course);
        course.setCompany(company);
        courseRepository.save(course);
    }
    @Transactional
    public Course findById(Long courseId) {
        return courseRepository.findById(courseId);
    }
    @Transactional
    public List<Course> findAll() {
        return courseRepository.findAll();
    }
    @Transactional
    public void deleteById(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    @Transactional
    public List<Course> findByCompanyId(Long companyId) {
        return courseRepository.findByCompanyId(companyId);
    }

    @Transactional
    public void update(Long courseID, Course course) {
         courseRepository.update(courseID,course );
    }
}
