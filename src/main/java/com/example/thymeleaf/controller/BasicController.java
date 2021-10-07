package com.example.thymeleaf.controller;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {
    @GetMapping("text-basic")
    public String textBasic(Model model){
        model.addAttribute("data", "Hello Spring");
        return "basic/text-basic";
    }

    /**
     *  기본적으로 escape 처리를 해줘야 한다.
     * */
    @GetMapping("text-unescaped")
    public String unescaped(Model model){
        model.addAttribute("data", "Hello <b>Spring</b>");
        return "basic/text-unescaped";
    }

    @GetMapping("variable")
    public String variable(Model model){
        User userA = new User("James", 30);
        User userB = new User("Alice", 25);

        List<User> lst = new ArrayList<>();
        lst.add(userA);
        lst.add(userB);

        Map<String, User> map = new HashMap<>();
        map.put("userA", userA);
        map.put("userB", userB);
        model.addAttribute("userA", userA);
        model.addAttribute("lst", lst);
        model.addAttribute("map", map);

        return "basic/variables";
    }

    @Data
    static class User{
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
