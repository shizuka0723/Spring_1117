package com.web.mvc;

import com.web.mvc.beans.User;
import java.util.List;
import java.util.Vector;
import org.springframework.stereotype.Repository;

@Repository
public class HelloRepository {
    private List<User> users = new Vector<>();
    
    public void addUser(User user){
        users.add(user);
    }
    
    public User getUser(Integer id){
        return users.stream().filter(u->u.getNum().getId()==id).findFirst().get();
    }
    
    public List<User> query(){
        return users;
    }
    
    public void removeUser(Integer id){
        users.remove(getUser(id));
    }
    
    public void updateUser(Integer id,User user){
        User u = getUser(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
    }
}
