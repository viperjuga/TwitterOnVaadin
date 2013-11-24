package com.screen.registration;

import com.TwitterOnVaadin;
import com.models.User;
import com.service.ServiceProxy;
import com.utils.Utils;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * TwitterUser: ASUS
 * Date: 10/3/13
 * Time: 9:10 AM
 */
public class Registration extends VerticalLayout implements View {
    TextField mSurname;
    TextField mForename;
    TextField mAge;
    TextField mUsername;
    PasswordField mPassword;

    //<editor-fold desc="Constructor">
    public Registration(){
        init();
    }
    //</editor-fold>

    //<editor-fold desc="General Methods">
    public void init(){
        Panel panel = new Panel("Registration");
        panel.setSizeUndefined();

        panel.setContent(createRegLayout());

        addComponent(panel);
        setSizeFull();
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
    }

    private CustomLayout createRegLayout(){
        CustomLayout customLayout;
        try {
            customLayout = new CustomLayout(getClass().getResourceAsStream("Registration.html"));
        } catch (IOException e) {
            return null;
        }

        mSurname = new TextField();
        mForename = new TextField();
        mAge = new TextField();
        mUsername = new TextField();
        mPassword = new PasswordField();
        PasswordField repPassword = new PasswordField();
        Button ok = new Button("Ok",btn_ok_click);
        Button cancel = new Button("Cancel",btn_cancel_click);

        customLayout.addComponent(mForename, "forename");
        customLayout.addComponent(mSurname, "surname");
        customLayout.addComponent(mAge,"age");
        customLayout.addComponent(mUsername, "username");
        customLayout.addComponent(mPassword, "password");
        customLayout.addComponent(repPassword,"reppassword");
        customLayout.addComponent(ok, "ok");
        customLayout.addComponent(cancel, "cancel");

        return customLayout;
    }
    //</editor-fold>

    //<editor-fold desc="Listeners">
    Button.ClickListener btn_cancel_click = new Button.ClickListener(){

        @Override
        public void buttonClick(Button.ClickEvent clickEvent) {
           getUI().getNavigator().navigateTo(Utils.LOGIN_SCREEN);
        }
    };
    Button.ClickListener btn_ok_click = new Button.ClickListener(){

        @Override
        public void buttonClick(Button.ClickEvent clickEvent) {
            ServiceProxy service = new ServiceProxy();
             User user = new User();
            user.setForename(String.valueOf(mForename));
            user.setUsername(String.valueOf(mUsername));
            user.setPassword(String.valueOf(mPassword));
            user.setSurname(String.valueOf(mSurname));
            user.setAge(String.valueOf(mAge));
            try {
                service.requestAddNewUser(user);
            } catch (RemoteException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            getUI().getNavigator().navigateTo(Utils.LOGIN_SCREEN);
        }
    };

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
       removeAllComponents();
        init();
    }
    //</editor-fold>


}
