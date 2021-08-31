package com.singh.rupesh.asyncdemo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
@JsonIgnoreProperties annotation tells Spring to ignore any attributes not listed in the class.
This makes it easy to make REST calls and produce domain objects.
*/

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private String name;
    private String blog;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", blog='" + blog + '\'' +
                '}';
    }
}
