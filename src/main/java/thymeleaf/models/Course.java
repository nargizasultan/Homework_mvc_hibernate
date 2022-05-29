package thymeleaf.models;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "courses")
@Getter
@Setter
@ToString
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String courseName;

    private int  duration;
    @ManyToOne
    private Company company;
    @ManyToMany(mappedBy = "courses")
    private List<Group> groups=new ArrayList<>();
    @OneToOne(mappedBy = "course")
    private Teacher teacher;
    public void setGroup(Group group){
        this.groups.add(group);
    }


}


