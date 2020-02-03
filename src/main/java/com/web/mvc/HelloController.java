package com.web.mvc;

import com.web.mvc.beans.User;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
@RequestMapping("/hello")
public class HelloController {
    
    @Autowired
    private HelloRepository hr;
            
    @RequestMapping("/hi")
    @ResponseBody
    public String sayHi(){
        return "Say Hi";
    }
    
    @RequestMapping("/yes")
    @ResponseBody
    public String sayYes(){
        return "Say Yes";
    }
    
    @RequestMapping("/page")
    public ModelAndView callHelloJspPage(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("name", "John");
        mv.setViewName("hello");
        return mv;
    }
    
    @RequestMapping("/page2")
    public String callHelloJspPage2(Model model){
        model.addAttribute("name", "Haruka");
        return "hello";
    }
    
     /*
    @RequestMapping(value = "路徑")
    ?  匹配 1 個字符  /testPaths? -> /testPathsA
    ?? 匹配 2 個字符  /testPaths?? -> /testPathsAA
    *  匹配任意字符   /testPaths/* -> /testPaths/ABCDEFG...
    *  匹配任意字符   /testPaths/ * /ok -> /testPaths/ttt/ok
    ** 可含有任意多層路徑 /testPaths/** -> /testPaths/aa/bb/cc
    ** 可含有任意多層路徑 /testPaths/ ** /ok -> /testPaths/aa/bb/cc/ok
    */
    @RequestMapping("/testPaths?")
    public String testPaths(Model model){
        model.addAttribute("name", "Path");
        return "hello";
    }
    
    @RequestMapping("/testPaths/{name}/{age}")
    public String testPaths2(Model model,@PathVariable("name") String name,@PathVariable Integer age){
        model.addAttribute("name", name + " " + age);
        return "hello";
    }
    
    @RequestMapping("/testParam") //testParm?name=xxx&age=xxx
    public String testParam(Model model,@RequestParam("name") String name,@RequestParam("age") Integer age){
        model.addAttribute("name", name + " " + age);
        return "hello";
    }
    
    @RequestMapping(value = "/testParam2",params = {"age=18"}) 
    public String testParam2(Model model){
        model.addAttribute("name", "Adult");
        return "hello";
    }
    
    @RequestMapping("/testCookie")
    public String testCookie(Model model,@CookieValue("JSESSIONID") String sessionid){
        model.addAttribute("name", sessionid);
        return "hello";
    }
    
    @RequestMapping("/testHeader")
    public String testHeader(Model model,@RequestHeader(value = "User-Agent") String userAgent){
        model.addAttribute("name", userAgent);
        return "hello";
    }
    
    @RequestMapping("/user/add") //../user/add?name=Johm&age=18&num.id=1
    public String addUser(Model model,User user){
        hr.addUser(user);
        model.addAttribute("name", user);
        return "hello";
    }

    @RequestMapping("/user/query") 
    public String queryUser(Model model,User user){
        model.addAttribute("name", hr.query());
        return "hello";
    }
    
    @RequestMapping("/user/get/{id}") 
    public String getUser(Model model,User user,@PathVariable("id") Integer id){
        user = hr.getUser(id);
        model.addAttribute("name", user);
        return "hello";
    }
    
    @RequestMapping("/user/update/{id}") //../user/update/10?name=Johm&age=18
    public String updateUser(Model model,User user,@PathVariable("id") Integer id){
        hr.updateUser(id, user);
        model.addAttribute("name", "updateUser");
        return "hello";
    }
    
    @RequestMapping("/user/remove/{id}") 
    public String removeUser(Model model,@PathVariable("id") Integer id){
        hr.removeUser(id);
        model.addAttribute("name", hr.query());
        return "hello";
    }
    
    // 利用 Map 容器收集資料
    @RequestMapping("/map/add") //../map/add?name=John&age=18
    public String addMap(Model model,@RequestParam Map<String,Object> map){
        System.out.println(map);
        model.addAttribute("name", map);
        return "hello";
    }
    
    // 新版 @RequestMapping 搭配 @EnableWebMvc
    @GetMapping("/test/method")
    public String testMethod(Model model){
        model.addAttribute("name", "method test");
        return "hello";
    }
}
