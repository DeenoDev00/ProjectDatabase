package se.iths.java24.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ClassYear", schema = "demo")
public class ClassYear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClassYearID", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "ClassPeriod", length = 50)
    private String classPeriod;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ClassProgramID", nullable = false)
    private Program classProgramID;

    @OneToMany(mappedBy = "StudentClassYear", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student> students = new ArrayList<>();

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public ClassYear(String classPeriod, Program ClassProgramID) {
        this.classPeriod = classPeriod;
        this.classProgramID = ClassProgramID;
    }

    public ClassYear() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassPeriod() {
        return classPeriod;
    }

    public void setClassPeriod(String classPeriod) {
        this.classPeriod = classPeriod;
    }

    public Program getClassProgramID() {
        return classProgramID;
    }

    public void setClassProgramID(Program classProgramID) {
        this.classProgramID = classProgramID;
    }

}
