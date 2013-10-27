package com.screen.login;

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


     //<editor-fold desc="Constructor">
    public Login(){
        init();
    }
    //</editor-fold>

    //<editor-fold desc="General Methods">
    public void init(){
        Panel panel = new Panel("Login");
        panel.setSizeUndefined();

        panel.setContent(createLoginLayout());

        setSizeFull();
        addComponent(panel);
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
    }

    private CustomLayout createLoginLayout(){
        CustomLayout customLayout;
        try {
            customLayout = new CustomLayout(getClass().getResourceAsStream("Login.html"));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        TextField username = new TextField();
        PasswordField password = new PasswordField();
        Button login = new Button("Login", btn_login_click);
        Button registration = new Button("Registration", btn_registration_click);

        customLayout.addComponent(username, "username");
        customLayout.addComponent(password, "password");
        customLayout.addComponent(login, "ok");
        customLayout.addComponent(registration, "registration");

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
