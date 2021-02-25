package com.example.demo.controllers;

import com.example.demo.Services.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

import static com.example.demo.Services.Post.posts;

@Controller
public class FormExample {




    @GetMapping(value = "/form")
    public String renderForm() {
        return "form";
    }

    @PostMapping("/create-post")
    public String createNewPost(@RequestParam("title") String title, @RequestParam ("post_content") String content,
                               @RequestParam ("private-public") String pp, @RequestParam ("date") String date){
        Post post = new Post(title,content,pp,date);
        posts.add(post);
        return "redirect:/success";
    }

    @GetMapping("/success")
    @ResponseBody
    public String newPostSuccess(){
        int index = Post.posts.size()-1;
        Post post = Post.posts.get(index);
        return "Your post has been created\n"+post.title;

    }


    @GetMapping("/dashboard")
    public String dashboard(Model model){
        ArrayList<Post>publicPosts = new ArrayList<>();
        for (Post post : posts){
            if (post.private_public.equals("Public")){
                publicPosts.add(post);
            }
        }

        model.addAttribute("publicPosts",publicPosts);

        return "dashboard.html";
    }

    @GetMapping(value="/list")
    @ResponseBody
    public ArrayList renderList() {
        return Post.posts;
    }

    
}
