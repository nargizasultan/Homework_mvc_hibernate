package thymeleaf.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "teachers")
@Getter
@Setter
@ToString
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String email;
    private String lastName;
    @OneToOne
    private Course course;


}
