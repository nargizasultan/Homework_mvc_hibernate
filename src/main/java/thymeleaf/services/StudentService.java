package thymeleaf.services;

import thymeleaf.models.Student;
import thymeleaf.repositories.StudentRepository;

import java.util.List;
import java.util.UUID;

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public void save(Student student) {
        studentRepository.save(student);
    }


    public Student findById(UUID studentId) {
        return studentRepository.findById(studentId);
    }


    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public void removeById(UUID studentId) {
        studentRepository.removeById(studentId);
    }
}
