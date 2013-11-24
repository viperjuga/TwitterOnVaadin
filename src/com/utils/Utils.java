package com.utils;

import com.models.User;
import com.vaadin.ui.Label;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * TwitterUser: ASUS
 * Date: 10/24/13
 * Time: 10:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class Utils {
    public static String MAIN_SCREEN = "main";
    public static String LOGIN_SCREEN = "login";
    public static String REGISTRATION_SCREEN = "registation";
    public static String FIND_USERS = "find_users";
    public static String TWITTER_USER = "twitter_user";
    public static String FRIENDS = "friends";
    public static String BLOCKED_USERS = "blocked_users";

    public static boolean isUserInList(ArrayList<User> list, User checkedUser){
        for(User user : list){
            if(user.getUsername().equals(checkedUser.getUsername())){
                return true;
            }
        }
        return false;
    }
    public static User getUserFromListById(ArrayList<User> users, int checkedUserId){
        for(User temp : users){
            if(temp.getId() == checkedUserId){
                return temp;
            }
        }
        return new User();
    }
}
