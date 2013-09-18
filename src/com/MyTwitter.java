package com;

import com.vaadin.Application;
import com.vaadin.ui.*;
/**
 * User: ASUS
 * Date: 9/18/13
 * Time: 9:45 PM
 */
public class MyTwitter extends Application {

    @Override
    public void init() {
        Window mainWindow = new Window("Twitter Application");
        Label label = new Label("Hello, it is start point of Twitter application");
        mainWindow.addComponent(label);
        setMainWindow(mainWindow);
    }
}
