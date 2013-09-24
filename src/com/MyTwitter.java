package com;

import com.vaadin.Application;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.*;

import java.io.IOException;

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
        mUserName = new TextField();
        mPassword = new PasswordField();
        mLogin = new Button("Login",btn_login_click);
    }
    //</editor-fold>

    //<editor-fold desc="General method">
    @Override
    public void init() {

        GridLayout gridLayout = new GridLayout(3,3);
        gridLayout.setWidth(60, Sizeable.UNITS_PERCENTAGE);
        gridLayout.setHeight(60, Sizeable.UNITS_PERCENTAGE);

        Panel panel = new Panel("Login");
        panel.setSizeUndefined();

        CustomLayout customLayout = null;
        try {
            customLayout = new CustomLayout(getClass().getResourceAsStream("LoginScreen.html"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        customLayout.addComponent(mUserName, "username");
        customLayout.addComponent(mPassword, "password");
        customLayout.addComponent(mLogin, "okbutton");

        panel.setContent(customLayout);

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponent(panel);
        verticalLayout.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

        gridLayout.addComponent(verticalLayout,2,2);

        mWindow.setLayout(gridLayout);

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
