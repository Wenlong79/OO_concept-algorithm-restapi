package com.wl.model;

public class Article {

    private String title;
    private String url;
    private String author;
    private Integer num_comments;
    private Integer story_id;
    private String  story_title;
    private String story_url;
    private Integer parent_id;
    private String  created_at;

    public Article() {
    }

    public Article(String title, String url, String author, Integer num_comments, Integer story_id, String story_title, String story_url, Integer parent_id, String created_at) {
        this.title = title;
        this.url = url;
        this.author = author;
        this.num_comments = num_comments;
        this.story_id = story_id;
        this.story_title = story_title;
        this.story_url = story_url;
        this.parent_id = parent_id;
        this.created_at = created_at;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getNum_comments() {
        return num_comments;
    }

    public void setNum_comments(Integer num_comments) {
        this.num_comments = num_comments;
    }

    public Integer getStory_id() {
        return story_id;
    }

    public void setStory_id(Integer story_id) {
        this.story_id = story_id;
    }

    public String getStory_title() {
        return story_title;
    }

    public void setStory_title(String story_title) {
        this.story_title = story_title;
    }

    public String getStory_url() {
        return story_url;
    }

    public void setStory_url(String story_url) {
        this.story_url = story_url;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Article --> [" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", author='" + author + '\'' +
                ", num_comments=" + num_comments +
                ", story_id=" + story_id +
                ", story_title='" + story_title + '\'' +
                ", story_url='" + story_url + '\'' +
                ", parent_id=" + parent_id +
                ", created_at='" + created_at + '\'' +
                ']';
    }
}
