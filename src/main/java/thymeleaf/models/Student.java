package thymeleaf.models;

import lombok.Getter;
import lombok.Setter;
import thymeleaf.enums.StudyFormat;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String firstName;

    private String email;
    private String lastName;
    private StudyFormat studyFormat;
    @ManyToOne
    private Group group;

}
