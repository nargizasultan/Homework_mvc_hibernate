package thymeleaf.services;

import org.springframework.stereotype.Service;
import thymeleaf.models.Company;
import thymeleaf.models.Course;
import thymeleaf.repositories.CompanyRepository;
import thymeleaf.repositories.CourseRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;

    public CourseService(CourseRepository courseRepository, CompanyRepository companyRepository) {
        this.courseRepository = courseRepository;
        this.companyRepository = companyRepository;
    }
    @Transactional
    public void save(Course course, UUID companyId) {
        Company company = companyRepository.findById(companyId);
        company.setCourse(course);
        course.setCompany(company);
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


    public List<Course> findByCompanyId(UUID companyId) {
        return courseRepository.findByCompanyId(companyId);
    }



}
