package com;

import com.vaadin.Application;
import com.vaadin.ui.*;
/**
 * User: ASUS
 * Date: 9/18/13
 * Time: 9:45 PM
 */
public class MyTwitter extends Application {
    Window mWindow;
    TextField mUserName;
    PasswordField mPassword;
    Button mLogin;

    //<editor-fold desc="Constructor">
    public MyTwitter(){
        mWindow = new Window("Twitter Application");
        mUserName = new TextField("Username");
        mPassword = new PasswordField("Password");
        mLogin = new Button("Login",btn_login_click);
    }
    //</editor-fold>

    //<editor-fold desc="General method">
    @Override
    public void init() {
        mWindow.addComponent(mUserName);
        mWindow.addComponent(mPassword);
        mWindow.addComponent(mLogin);
        setMainWindow(mWindow);
    }
    //</editor-fold>

    //<editor-fold desc="Listeners">
    Button.ClickListener btn_login_click = new Button.ClickListener(){
        @Override
        public void buttonClick(Button.ClickEvent clickEvent) {
            String yourAcc = String.format("Your name:%s Your password:%s",mUserName,mPassword);
            mWindow.showNotification(yourAcc);
        }
    };
    //</editor-fold>
}
