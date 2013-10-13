package com;

import com.screen.login.Login;
import com.screen.registration.Registration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 * User: ASUS
 * Date: 9/18/13
 * Time: 9:45 PM
 */
public class TwitterOnVaadin extends UI {
    public static String MAIN_SCREEN = "main";
    public static String LOGIN_SCREEN = "login";
    public static String REGISTRATION_SCREEN = "registation";
    Navigator mNavigator;



    @Override
    protected void init(VaadinRequest request) {
           mNavigator = new Navigator(this,this);
        mNavigator.addView("", new Login());
        mNavigator.addView(REGISTRATION_SCREEN, new Registration());
    }

}
