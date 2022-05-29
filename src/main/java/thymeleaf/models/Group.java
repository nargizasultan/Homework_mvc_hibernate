package thymeleaf.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    private Date dateOfStart;
    private Date dateOfFinish;
    @ManyToMany()
    private List<Course> courses=new ArrayList<>();
    @OneToMany(mappedBy = "group")
    private List<Student>students=new ArrayList<>();

    public void setCourse(Course course){
        this.courses.add(course);
    }


}
