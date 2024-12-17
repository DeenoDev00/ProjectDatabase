package se.iths.java24.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Course", schema = "demo")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CourseID", nullable = false)
    private Integer id;

    @jakarta.validation.constraints.Size(max = 50)
    @Column(name = "CourseName", length = 50)
    private String courseName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

}