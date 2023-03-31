package com.example.newsarticleapi;

public class ArticleModel {
    String title,author ,description,imageurl;

    public ArticleModel() {
    }

    public ArticleModel(String title, String author, String description, String imageurl) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.imageurl = imageurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
