package thymeleaf.models;

import lombok.Getter;
import lombok.Setter;
import thymeleaf.enums.StudyFormat;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String email;
    private String lastName;
    private StudyFormat studyFormat;
    @ManyToOne(cascade = MERGE)
    private Group group;

}
