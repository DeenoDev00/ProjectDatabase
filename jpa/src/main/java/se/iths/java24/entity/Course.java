package se.iths.java24.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Course", schema = "demo")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CourseID", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "CourseName", length = 50)
    private String courseName;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CourseProgramID", nullable = false)
    private Program Program;


    public Course(String courseName) {
        this.courseName = courseName;
    }

    public Course() {

    }

    public Program getProgram() {
        return Program;
    }
    public void setProgram(Program Program) {
        this.Program = Program;
    }

    public Integer getCourseId(){
        return id;
    }

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

//    public Program getCourseProgramID() {
//        return courseProgramID;
//    }
//
//    public void setCourseProgramID(Program courseProgramID) {
//        this.courseProgramID = courseProgramID;
//    }

}