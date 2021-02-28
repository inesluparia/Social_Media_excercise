package com.example.demo.Model;

import java.util.ArrayList;

public class Post {

    public String title;
    public String content;
    public String private_public;
    public String stringDate;
    //LocalDate date;

    public static ArrayList<Post> posts = new ArrayList<>();

    public Post(String title, String content, String private_public, String date){
        this.title=title;
        this.content= content;
        this.private_public= private_public;
        stringDate = date;
        //this.date=LocalDate.parse(date);
    }
}
