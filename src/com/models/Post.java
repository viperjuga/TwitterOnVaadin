package com.models;

/**
 * Created with IntelliJ IDEA.
 * TwitterUser: ASUS
 * Date: 10/24/13
 * Time: 11:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class Post {

    private String mAuthor;
    private String mPost;
    private String mDate;
    private String mUser;
    private int mId;

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

    public String getDate(){
        return mDate;
    }
    public void setDate(String details){
        mDate = details;
    }
    public String getUser(){
        return mUser;
    }
    public void setUser(String user){
        mUser = user;
    }
    public void setId(int Id){
        mId = Id;
    }
    public int getId(){
        return mId;
    }

}
