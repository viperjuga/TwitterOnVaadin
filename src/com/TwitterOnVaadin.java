package com;

import com.screen.login.Login;
import com.screen.main.Main;
import com.screen.registration.Registration;
import com.utils.Utils;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 * User: ASUS
 * Date: 9/18/13
 * Time: 9:45 PM
 */
public class TwitterOnVaadin extends UI {

    Navigator mNavigator;



    @Override
    protected void init(VaadinRequest request) {
        mNavigator = new Navigator(this,this);
        mNavigator.addView("", new Login());
        mNavigator.addView(Utils.LOGIN_SCREEN, new Login());
        mNavigator.addView(Utils.REGISTRATION_SCREEN, new Registration());
        mNavigator.addView(Utils.MAIN_SCREEN, new Main());
    }

}
