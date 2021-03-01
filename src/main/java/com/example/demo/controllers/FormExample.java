package com.example.demo.controllers;

import com.example.demo.Model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

import static com.example.demo.Model.Post.posts;
//testing push on gh
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
    public String newPostSuccess(Model successMdl){
        int index = Post.posts.size()-1;
        Post post = Post.posts.get(index);
        successMdl.addAttribute("YourPost",post);
        return "Your post has been created\n"+post.title;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model){
        ArrayList<Post>publicPosts = new ArrayList<>();
        publicPosts.add(new Post("Title","this is the shit","Public","some date"));
        publicPosts.add(new Post("Title 2","I have so much to share with the world. cjiocre aeriv cjioac o cioa c ocai ifd fisdo vcafe.","Public","22/07/14"));
        publicPosts.add(new Post("3","this is the shit","Public","22/05/17"));
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
