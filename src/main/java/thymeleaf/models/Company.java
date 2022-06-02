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
@Table(name = "companies")
@Getter
@Setter
@ToString
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String locatedCountry;

    @OneToMany(mappedBy = "company", cascade = {REMOVE}, orphanRemoval = true)
    private List<Course> courses = new ArrayList<>();

    public void setCourse(Course course) {
        this.courses.add(course);
    }

}
