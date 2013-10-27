package com.utils;

import com.models.User;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ASUS
 * Date: 10/24/13
 * Time: 10:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class Utils {
    public static String MAIN_SCREEN = "main";
    public static String LOGIN_SCREEN = "login";
    public static String REGISTRATION_SCREEN = "registation";
    public static String FIND_USERS = "find_users";

    public static ArrayList<User> getHardcodedUsers(){
     ArrayList<User> hardcodedUser = new ArrayList<User>();
        for(int i=0; i<10; i++){
            User hardUser = new User();
            hardUser.setAge(String.valueOf(20 + i));
            hardUser.setSurname("Pupkin");
            hardUser.setForename("Vasja " + i);
            hardUser.setUsername("user " + i);
            hardcodedUser.add(hardUser);
        }
        return hardcodedUser;
    }
}
