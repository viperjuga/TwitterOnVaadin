package com;

import com.screen.blockedusers.BlockedUsers;
import com.screen.find.TwitterUsers;
import com.screen.friends.Friends;
import com.screen.login.Login;
import com.screen.main.Main;
import com.screen.registration.Registration;
import com.screen.user.TwitterUser;
import com.utils.Utils;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 * TwitterUser: ASUS
 * Date: 9/18/13
 * Time: 9:45 PM
 */
public class TwitterOnVaadin extends UI {
    @Override
    protected void init(VaadinRequest request) {
        Navigator navigator = new Navigator(this,this);
        navigator.addView("", new Login());
        navigator.addView(Utils.LOGIN_SCREEN, new Login());
        navigator.addView(Utils.REGISTRATION_SCREEN, new Registration());
        navigator.addView(Utils.MAIN_SCREEN, new Main());
        navigator.addView(Utils.FIND_USERS, new TwitterUsers());
        navigator.addView(Utils.TWITTER_USER, new TwitterUser());
        navigator.addView(Utils.BLOCKED_USERS, new BlockedUsers());
        navigator.addView(Utils.FRIENDS, new Friends());
    }

}
