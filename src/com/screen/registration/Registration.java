package com.screen.registration;

import com.TwitterOnVaadin;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

import java.io.IOException;

/**
 * User: ASUS
 * Date: 10/3/13
 * Time: 9:10 AM
 */
public class Registration extends VerticalLayout implements View {

    TextField mSurname;
    TextField mForename;
    TextField mAge;
    TextField mUserName;
    PasswordField mPassword;
    PasswordField mRepPassword;
    Button mOk;
    Button mCancel;
    Window mCtx;


    //<editor-fold desc="Constructor">
    public Registration(){
        mSurname = new TextField();
        mForename = new TextField();
        mAge = new TextField();
        mUserName = new TextField();
        mPassword = new PasswordField();
        mRepPassword = new PasswordField();
        mOk = new Button("Ok",btn_ok_click);
        mCancel = new Button("Cancel",btn_cancel_click);
    }
    //</editor-fold>

    //<editor-fold desc="General Methods">
    public void init(){
        Panel panel = new Panel("Registration");
        panel.setSizeUndefined();

        CustomLayout customLayout;
        try {
            customLayout = new CustomLayout(getClass().getResourceAsStream("Registration.html"));
        } catch (IOException e) {
            return;
        }

        customLayout.addComponent(mForename, "forename");
        customLayout.addComponent(mSurname, "surname");
        customLayout.addComponent(mAge,"age");
        customLayout.addComponent(mUserName, "username");
        customLayout.addComponent(mPassword, "password");
        customLayout.addComponent(mRepPassword,"reppassword");
        customLayout.addComponent(mOk, "ok");
        customLayout.addComponent(mCancel, "cancel");

        panel.setContent(customLayout);

        addComponent(panel);
        setSizeFull();
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
    }
    //</editor-fold>

    //<editor-fold desc="Listeners">
    Button.ClickListener btn_cancel_click = new Button.ClickListener(){

        @Override
        public void buttonClick(Button.ClickEvent clickEvent) {
           getUI().getNavigator().navigateTo(TwitterOnVaadin.LOGIN_SCREEN);
        }
    };
    Button.ClickListener btn_ok_click = new Button.ClickListener(){

        @Override
        public void buttonClick(Button.ClickEvent clickEvent) {
            Notification.show("Now you can try login!");
            getUI().getNavigator().navigateTo(TwitterOnVaadin.LOGIN_SCREEN);
        }
    };

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
    //</editor-fold>


}
