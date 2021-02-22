package com.example.demo.controllers;

import com.example.demo.Services.Post;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

import static com.example.demo.Services.Post.posts;

@Controller
public class FormExample {
    // Showing how to create a form using thymeleaf
    @GetMapping(value = "/form")
    public String renderForm() {
        return "form";
    }

    @PostMapping("/create-post")
    @ResponseBody
    public String createNewPost(@RequestParam("title") String title, @RequestParam ("post_content") String content,
                               @RequestParam ("private-public") String pp, @RequestParam ("date") String date){
        Post post = new Post(title,content,pp,date);
        posts.add(post);
        return "redirect:/success";
    }

    @GetMapping("/success")
    @ResponseBody
    public String newPostSuccess(){
        return "Your post has been created";
    }

    @GetMapping(value="/list")
    @ResponseBody
    public ArrayList renderList() {
        return Post.posts;
    }
}
