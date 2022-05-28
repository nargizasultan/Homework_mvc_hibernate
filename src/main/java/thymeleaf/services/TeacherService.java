package thymeleaf.services;


import thymeleaf.models.Teacher;
import thymeleaf.repositories.TeacherRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
    public void save(Teacher teacher) {
        teacherRepository.save(teacher);
    }


    public Teacher findById(UUID teacherId) {
        return teacherRepository.findById(teacherId);
    }


    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    public void removeById(UUID teacherId) {
        teacherRepository.removeById(teacherId);
    }
}
