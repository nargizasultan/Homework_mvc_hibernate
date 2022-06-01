package thymeleaf.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.MERGE;

@Entity
@Table(name = "groups")
@Getter
@Setter
@ToString
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String groupName;
    private String dateOfStart;
    private String dateOfFinish;
    @ManyToMany
    private List<Course> courses=new ArrayList<>();

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student>students=new ArrayList<>();

    public void setCourse(Course course){
        this.courses.add(course);
    }
    public void setStudent(Student student){
        this.students.add(student);
    }


}
