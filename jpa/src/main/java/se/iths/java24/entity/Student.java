package se.iths.java24.entity;

import jakarta.persistence.*;
import se.iths.java24.repository.Grade;

@Entity
@Table(name = "Student", schema = "demo")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StudentID", nullable = false)
    private Integer id;

    @jakarta.validation.constraints.Size(max = 50)
    @Column(name = "Forename", length = 50)
    private String forename;

    @jakarta.validation.constraints.Size(max = 50)
    @Column(name = "Surname", length = 50)
    private String surname;

    @jakarta.validation.constraints.Size(max = 50)
    @Column(name = "Grade", length = 50)
    @Enumerated(EnumType.STRING)
    private Grade grade;




    @jakarta.validation.constraints.NotNull
    @Column(name = "StudentAge", nullable = false)
    private Integer studentAge;

    @jakarta.validation.constraints.Size(max = 50)
    @Column(name = "StudentMail", length = 50)
    private String studentMail;

    @jakarta.validation.constraints.NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "StudentClassYear", nullable = false)
    private ClassYear StudentClassYear;

    public Student() {}

    // Konstruktorn som du använder i din Main
    public Student(String forename, String surname, Integer age, String email, ClassYear studentClassYear) {
        this.forename = forename;
        this.surname = surname;
        this.studentAge = age;
        this.studentMail = email;
        this.StudentClassYear = studentClassYear;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Integer getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(Integer studentAge) {
        this.studentAge = studentAge;
    }

    public String getStudentMail() {
        return studentMail;
    }

    public void setStudentMail(String studentMail) {
        this.studentMail = studentMail;
    }

    public ClassYear getStudentClassYear() {
        return StudentClassYear;
    }

    public void setStudentClassYear(ClassYear studentClassYear) {
        this.StudentClassYear = studentClassYear;
    }

}
