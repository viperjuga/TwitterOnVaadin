package com.models;

/**
 * Created with IntelliJ IDEA.
 * User: ASUS
 * Date: 10/24/13
 * Time: 11:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class Post {

    private String mAuthor;
    private String mPost;
    private String mDetails;

    public String getAuthor(){
        return mAuthor;
    }
    public void setAuthor(String author){
        mAuthor = author;
    }

    public String getPost(){
        return mPost;
    }
    public void setPost(String post){
        mPost = post;
    }

    public String getDetails(){
        return mDetails;
    }
    public void setDetails(String details){
        mDetails = details;
    }

}