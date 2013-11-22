package com.screen.find;

import com.models.SelectedUser;
import com.models.User;
import com.screen.main.Main;
import com.service.ServiceProxy;
import com.utils.Utils;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ASUS
 * Date: 10/25/13
 * Time: 10:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class TwitterUsers extends VerticalLayout implements View {
    ArrayList<User> mUsers;
    //<editor-fold desc="Constructor">
    public TwitterUsers(){

        mUsers = new ArrayList<User>();
    }
    //</editor-fold>

    //<editor-fold desc="General Methods">
    public void init(){
        Panel panel = new Panel("Find users");
        panel.setSizeUndefined();

        panel.setContent(createTwitterUsersLayout());

        setSizeFull();
        addComponent(panel);
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
    }

    private CustomLayout createTwitterUsersLayout(){
        CustomLayout customLayout;
        try {
            customLayout = new CustomLayout(getClass().getResourceAsStream("TwitterUsers.html"));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        MenuBar menu = new MenuBar();
        menu.addItem("Main", main);
        menu.setWidth("400");

        ComboBox fields = new ComboBox();
        TextField search = new TextField();

        customLayout.addComponent(fields, "fields");
        customLayout.addComponent(menu, "menu");
        customLayout.addComponent(search, "search");
        customLayout.addComponent(createTable(), "list");

        return customLayout;
    }
    private Table createTable(){
        Table newsTable = new Table();
        newsTable.addContainerProperty("User", Button.class, null);
        ServiceProxy proxy = new ServiceProxy();

        try {
             mUsers = proxy.requestGetAllUsers();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        for (int i=0; i < mUsers.size(); i++) {

            Integer itemId = new Integer(i);

            // Create a button and handle its click. A Button does not
            // know the item it is contained in, so we have to store the
            // item ID as user-defined data.
            Button user = new Button(mUsers.get(i).getUsername());
            user.setData(itemId);
            user.addClickListener(show_user);
            user.addStyleName("link");

            // Create the table row.
            newsTable.addItem(new Object[]{user},
                    itemId);
        }

// Show just three rows because they are so high.
        newsTable.setPageLength(5);

        newsTable.setWidth("400");
        newsTable.setHeight("400");

        return newsTable;
    }
    //</editor-fold>

    //<editor-fold desc="Listeners">
    MenuBar.Command main = new MenuBar.Command(){

        @Override
        public void menuSelected(MenuBar.MenuItem menuItem) {
           getUI().getNavigator().navigateTo(Utils.MAIN_SCREEN);
        }
    };
    Button.ClickListener show_user = new Button.ClickListener(){

        @Override
        public void buttonClick(Button.ClickEvent clickEvent) {
            Integer iid = (Integer)clickEvent.getButton().getData();

            User user = mUsers.get(iid);

UI.getCurrent().getSession().setAttribute("CurrentUser", user);
            getUI().getNavigator().navigateTo(Utils.MAIN_SCREEN);





        }
    };

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        removeAllComponents();
        init();
    }
    //</editor-fold>
}
