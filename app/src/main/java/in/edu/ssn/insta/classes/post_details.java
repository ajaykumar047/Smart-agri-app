package in.edu.ssn.insta.classes;

import android.net.Uri;

public class post_details {
    private String username,desc;
    private String user_img,post_img,document_id;


    public post_details(String username, String desc, String user_img, String post_img,String document_id) {
        this.username = username;
        this.desc = desc;
        this.user_img = user_img;
        this.post_img = post_img;
        this.document_id = document_id;

    }

    public post_details() {
    }

    public String getDocument_id() {
        return document_id;
    }

    public void setDocument_id(String document_id) {
        this.document_id = document_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUser_img() {
        return user_img;
    }

    public void setUser_img(String user_img) {
        this.user_img = user_img;
    }

    public String getPost_img() {
        return post_img;
    }

    public void setPost_img(String post_img) {
        this.post_img = post_img;
    }
}
