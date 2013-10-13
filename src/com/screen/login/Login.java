package com.screen.login;

import com.TwitterOnVaadin;
import com.screen.main.Main;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

import java.io.IOException;

/**
 * User: ASUS
 * Date: 10/3/13
 * Time: 9:00 AM
 */
public class Login extends VerticalLayout implements View {

    TextField mUserName;
    PasswordField mPassword;
    Button mLogin;
    Button mRegistration;
    Window mCtx;

     //<editor-fold desc="Constructor">
    public Login(){
        mUserName = new TextField();
        mPassword = new PasswordField();
        mLogin = new Button("Login", btn_login_click);
        mRegistration = new Button("Registration", btn_registration_click);
        init();
    }
    //</editor-fold>

    //<editor-fold desc="General Methods">
    public void init(){
        Panel panel = new Panel("Login");
        panel.setSizeUndefined();

        CustomLayout customLayout;
        try {
            customLayout = new CustomLayout(getClass().getResourceAsStream("Login.html"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        customLayout.addComponent(mUserName, "username");
        customLayout.addComponent(mPassword, "password");
        customLayout.addComponent(mLogin, "ok");
        customLayout.addComponent(mRegistration, "registration");

        panel.setContent(customLayout);

        setSizeFull();
        addComponent(panel);
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
    }
    //</editor-fold>

    //<editor-fold desc="Listeners">
    Button.ClickListener btn_registration_click = new Button.ClickListener(){
        @Override
        public void buttonClick(Button.ClickEvent clickEvent) {
           getUI().getNavigator().navigateTo(TwitterOnVaadin.REGISTRATION_SCREEN);
        }
    };
    Button.ClickListener btn_login_click = new Button.ClickListener(){
        @Override
        public void buttonClick(Button.ClickEvent clickEvent) {
            Main main = new Main(mCtx);
            mCtx.setContent(main.getMainScreen());
//            String yourAcc = String.format("Your name:%s Your password:%s",mUserName,mPassword);
//            mCtx.showNotification(yourAcc);
        }
    };

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
    //</editor-fold>
}
