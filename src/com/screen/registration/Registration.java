package com.screen.registration;

import com.TwitterOnVaadin;
import com.utils.Utils;
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

        TextField surname = new TextField();
        TextField forename = new TextField();
        TextField age = new TextField();
        TextField username = new TextField();
        PasswordField password = new PasswordField();
        PasswordField repPassword = new PasswordField();
        Button ok = new Button("Ok",btn_ok_click);
        Button cancel = new Button("Cancel",btn_cancel_click);

        customLayout.addComponent(forename, "forename");
        customLayout.addComponent(surname, "surname");
        customLayout.addComponent(age,"age");
        customLayout.addComponent(username, "username");
        customLayout.addComponent(password, "password");
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
            Notification.show("Now you can try login!");
            getUI().getNavigator().navigateTo(Utils.LOGIN_SCREEN);
        }
    };

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
    //</editor-fold>


}
