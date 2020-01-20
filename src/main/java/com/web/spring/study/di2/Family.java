package com.web.spring.study.di2;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Family {
    
    @Value("John")
    private String name;
    
    @Value("#{${members : {'A','B','B','C'}}}")
    private Set<String> member;
    
    @Value("#{${hobbies : {'A','B','B','C'}}}")
    private List<String> hobbies;
    
    @Value("#{${height : {A:190 , B:180}}}")
    private Map<String,Integer> height;
  
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getMember() {
        return member;
    }

    public void setMember(Set<String> member) {
        this.member = member;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public Map<String, Integer> getHeight() {
        return height;
    }

    public void setHeight(Map<String, Integer> height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Family{" + "name=" + name + ", member=" + member + ", hobbies=" + hobbies + ", height=" + height + '}';
    }
    
    
    
}
