package thymeleaf.models;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "courses")
@Getter
@Setter
@ToString
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String courseName;

    private int duration;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Company company;
    @ManyToMany(mappedBy = "courses", cascade = {PERSIST,DETACH,REMOVE})
    private List<Group> groups = new ArrayList<>();

    @OneToOne(mappedBy = "course",
            cascade = {PERSIST,MERGE,DETACH,REFRESH,REMOVE},
            orphanRemoval = true)
    private Teacher teacher;

    public void setGroup(Group group) {
        this.groups.add(group);
    }
}


