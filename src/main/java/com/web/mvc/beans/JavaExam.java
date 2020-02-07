package com.web.mvc.beans;

public class JavaExam {
    private Integer id;
    private Student student;
    private Integer score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "JavaExam{" + "id=" + id + ", student=" + student + ", score=" + score + '}';
    }
    
    
}
