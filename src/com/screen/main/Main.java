package com.screen.main;

import com.models.Post;
import com.models.SelectedUser;
import com.models.User;
import com.service.ServiceProxy;
import com.utils.Utils;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ClassResource;
import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import org.apache.james.mime4j.field.datetime.DateTime;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * TwitterUser: ASUS
 * Date: 10/11/13
 * Time: 12:03 AM
 */
public class Main extends VerticalLayout implements View {
    ArrayList<Post> mPosts;
    User mCurrentUser;
    //<editor-fold desc="Constructor">
    public Main(){
        //init();
        mPosts = new ArrayList<Post>();
    }
    //</editor-fold>

    public void init(){
        mCurrentUser = (User)UI.getCurrent().getSession().getAttribute("CurrentUser");

        Panel panel = new Panel("Profile");
        panel.setSizeUndefined();

        CustomLayout customLayout;
        try {
            customLayout = new CustomLayout(getClass().getResourceAsStream("Main.html"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String profileInfo = String.format("Forename: %s\nSurname: %s\nAge: %s",mCurrentUser.getForename(),mCurrentUser.getSurname(),mCurrentUser.getAge());
        Label profile = new Label(profileInfo);
//        //Image image = new Image(user.getUsername(),new ClassResource("D:\\Downloads\\webDownloads\\lUugMYLfJP0.png"));
//        image.setHeight("30");
//        image.setWidth("30");

        // Instruct browser not to cache the image
        Table news = createTable(mCurrentUser.getId());
        MenuBar menu = createMenu();

        customLayout.addComponent(news, "wall");
       // customLayout.addComponent(image, "image");
        customLayout.addComponent(menu,"menu");
        customLayout.addComponent(profile, "profile");

        panel.setContent(customLayout);
        setSizeFull();
        addComponent(panel);
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
    }

    private MenuBar createMenu(){
        MenuBar menuBar = new MenuBar();
        menuBar.addItem("Find users",find_user);
        menuBar.addItem("Add post", add_post);
        menuBar.addItem("Blocked users", blocked_users);
        menuBar.addItem("Friends", friends);
        menuBar.setWidth("400");
        return menuBar;
    }

    private Table createTable(int userId) {
        Table newsTable = new Table("News");
        newsTable.addContainerProperty("User", Label.class, null);
        newsTable.addContainerProperty("Post", Button.class, null);
        newsTable.addContainerProperty("Details", Label.class, null);
          ServiceProxy proxy = new ServiceProxy();

        try {
            //TODO: getUserID to send in method
            mPosts = proxy.requestGetPostForUser(String.valueOf(userId));
        } catch (RemoteException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
/* Add a few items in the table. */
        for (int i=0; i<mPosts.size(); i++) {
            // Create the fields for the current table row
            ArrayList<User> users = null;
            try {
                users = proxy.requestGetAllUsers();
            } catch (RemoteException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

            User author = Utils.getUserFromListById(users, Integer.valueOf(mPosts.get(i).getAuthor()));
            Label sumField = new Label(author.getUsername());


            Integer itemId = new Integer(i);

            // Create a button and handle its click. A Button does not
            // know the item it is contained in, so we have to store the
            // item ID as user-defined data.
            Button postField = new Button("See post");
            postField.setData(itemId);
            postField.addClickListener(show_details_click);
            postField.addStyleName("link");
            Label detailField = new Label(mPosts.get(i).getDate());

            // Create the table row.
            newsTable.addItem(new Object[]{sumField, postField, detailField},
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

    MenuBar.Command find_user = new MenuBar.Command(){

        @Override
        public void menuSelected(MenuBar.MenuItem menuItem) {
            getUI().getNavigator().navigateTo(Utils.FIND_USERS);
        }
    };
    MenuBar.Command blocked_users = new MenuBar.Command(){

        @Override
        public void menuSelected(MenuBar.MenuItem menuItem) {
            getUI().getNavigator().navigateTo(Utils.BLOCKED_USERS);
        }
    };
    MenuBar.Command friends = new MenuBar.Command(){

        @Override
        public void menuSelected(MenuBar.MenuItem menuItem) {
            getUI().getNavigator().navigateTo(Utils.FRIENDS);
        }
    };
    MenuBar.Command add_post = new MenuBar.Command(){

        @Override
        public void menuSelected(MenuBar.MenuItem menuItem) {
            ServiceProxy service = new ServiceProxy();

            Post temp = new Post();
            temp.setAuthor("0");
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            temp.setDate(sdf.format(date));
            temp.setPost("HIIII");
            temp.setUser("0");
            try {
                service.requestAddPost(temp);
            } catch (RemoteException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    };

    Button.ClickListener show_details_click = new Button.ClickListener(){
        @Override
        public void buttonClick(Button.ClickEvent clickEvent) {
            Integer iid = (Integer)clickEvent.getButton().getData();
          UI.getCurrent().showNotification(mPosts.get(iid).getPost());
        }
    };

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
             removeAllComponents();
             init();
    }
    //</e
    //</editor-fold>
}
