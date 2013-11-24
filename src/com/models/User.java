package com.models;

/**
 * Created with IntelliJ IDEA.
 * TwitterUser: ASUS
 * Date: 10/24/13
 * Time: 11:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class User {

    private int mId;
    private String mForename;
    private String mSurname;
    private String mAge;
    private String mUserName;
    private String mPassword;
    private String mPhoto;
    private boolean mBan;
    private boolean mAdmin;


    public int getId(){
        return mId;
    }
    public void setId(int id){
        mId = id;
    }
    public String getForename(){
        return mForename;
    }
    public void setForename(String name){
        mForename = name;
    }

    public String getSurname(){
        return mSurname;
    }
    public void setSurname(String surname){
        mSurname = surname;
    }
    public void setAge(String age){
        mAge = age;
    }
    public String getAge(){
        return mAge;
    }
    public String getUsername(){
        return mUserName;
    }
    public void setUsername(String username){
        mUserName = username;
    }
    public String getPhoto(){
        return mPhoto;
    }
    public void setPhoto(String photo){
        mPhoto = photo;
    }
    public String getPassword(){
     return mPassword;
    }
    public void setPassword(String password){
        mPassword = password;
    }
    public boolean isBanned(){
        return mBan;
    }
    public void setBan(boolean ban){
        mBan = ban;
    }
    public boolean isAdmin(){
        return mAdmin;
    }
    public void setAdmin(boolean admin){
        mAdmin = admin;
    }
}
