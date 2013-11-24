package com.models;

/**
 * Created with IntelliJ IDEA.
 * TwitterUser: ASUS
 * Date: 10/25/13
 * Time: 11:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class SelectedUser {

    public SelectedUser(User user){
        mForename = user.getForename();
        mSurname = user.getSurname();
        mAge = user.getAge();
        mUserName = user.getUsername();
    }

    private String mForename;
    private String mSurname;
    private String mAge;
    private String mUserName;

    public String getForename(){
        return mForename;
    }

    public String getSurname(){
        return mSurname;
    }
    public String getAge(){
        return mAge;
    }
    public String getUsername(){
        return mUserName;
    }
}
