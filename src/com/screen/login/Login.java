package com.screen.login;

import com.TwitterOnVaadin;
import com.screen.main.Main;
import com.utils.Utils;
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

     //<editor-fold desc="Constructor">
    public Login(){
        init();
    }
    //</editor-fold>

    //<editor-fold desc="General Methods">
    public void init(){
        Panel panel = new Panel("Login");
        panel.setSizeUndefined();

        initComponents();

        panel.setContent(createLogLayout());

        setSizeFull();
        addComponent(panel);
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
    }

    private void initComponents(){
        mUserName = new TextField();
        mPassword = new PasswordField();
        mLogin = new Button("Login", btn_login_click);
        mRegistration = new Button("Registration", btn_registration_click);
    }

    private CustomLayout createLogLayout(){
        CustomLayout customLayout;
        try {
            customLayout = new CustomLayout(getClass().getResourceAsStream("Login.html"));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        customLayout.addComponent(mUserName, "username");
        customLayout.addComponent(mPassword, "password");
        customLayout.addComponent(mLogin, "ok");
        customLayout.addComponent(mRegistration, "registration");

        return customLayout;
    }

    //</editor-fold>

    //<editor-fold desc="Listeners">
    Button.ClickListener btn_registration_click = new Button.ClickListener(){
        @Override
        public void buttonClick(Button.ClickEvent clickEvent) {
           getUI().getNavigator().navigateTo(Utils.REGISTRATION_SCREEN);
        }
    };
    Button.ClickListener btn_login_click = new Button.ClickListener(){
        @Override
        public void buttonClick(Button.ClickEvent clickEvent) {
           getUI().getNavigator().navigateTo(Utils.MAIN_SCREEN);
//            String yourAcc = String.format("Your name:%s Your password:%s",mUserName,mPassword);
//            mCtx.showNotification(yourAcc);
        }
    };

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
    //</editor-fold>
}
