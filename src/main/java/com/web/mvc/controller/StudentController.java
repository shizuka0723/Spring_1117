package com.web.mvc.controller;

import com.web.mvc.beans.Student;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {
    public static List<Student> students = new ArrayList<>();
    
    @RequestMapping("/input")
    public String input(Model model){
        Student student = new Student();
//        student.setId(1);
//        student.setName("Haruka");
//        student.setAge(20);
        model.addAttribute("student", student);
        model.addAttribute("students", students);
        model.addAttribute("action", "add");
        return "student";
    }
    
    @RequestMapping("/add")
    public String add(@ModelAttribute Student student){
        int id = 1;
        if(students.size() != 0){
            id = students.stream().mapToInt(s -> s.getId()).max().getAsInt()+1;
        }
        student.setId(id);
        students.add(student);
        System.out.println(students);
        return "redirect:./input";
    }
    
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        Student student = students.stream().filter(s->s.getId() == id).findFirst().get();
        if(student != null) students.remove(student);
        return "redirect:../input";
    }
    
    @RequestMapping("/get/{id}")
    public String get(@PathVariable("id") Integer id,Model model){
        Student student = students.stream().filter(s->s.getId() == id).findFirst().get();
        model.addAttribute("student", student);
        model.addAttribute("students", students);
        model.addAttribute("action", "update");
        return "student";
    }
    
    @RequestMapping("/update")
    public String update(@ModelAttribute Student student){
        Student o_Student = students.stream().filter(s->s.getId() == student.getId()).findFirst().get();
        o_Student.setName(student.getName());
        o_Student.setAge(student.getAge());
        return "redirect:./input";
    }
}
