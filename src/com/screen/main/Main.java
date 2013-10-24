package com.screen.main;

import com.models.User;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ClassResource;
import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;

import java.io.IOException;

/**
 * User: ASUS
 * Date: 10/11/13
 * Time: 12:03 AM
 */
public class Main extends VerticalLayout implements View {

    //<editor-fold desc="Constructor">
    public Main(){
        init();
    }
    //</editor-fold>

    public void init(){

        Panel panel = new Panel("Profile");
        panel.setSizeUndefined();

        CustomLayout customLayout;
        try {
            customLayout = new CustomLayout(getClass().getResourceAsStream("Main.html"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        User user = new User();
        String profileInfo = String.format("Forename: %s\nSurname: %s\nAge: %s",user.getForename(),user.getSurname(),user.getAge());
        Label profile = new Label(profileInfo);
        Image image = new Image(user.getUsername(),new ClassResource("D:\\Downloads\\webDownloads\\lUugMYLfJP0.png"));
        image.setHeight("30");
        image.setWidth("30");

// Instruct browser not to cache the image
        Table news = createTable();
        MenuBar menu = createMenu();

        customLayout.addComponent(news, "wall");
        customLayout.addComponent(image, "image");
        customLayout.addComponent(menu,"menu");
        customLayout.addComponent(profile, "profile");

        panel.setContent(customLayout);
        setSizeFull();
        addComponent(panel);
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
    }

    private MenuBar createMenu(){
        MenuBar menuBar = new MenuBar();
        menuBar.addItem("Main", null);
        menuBar.addItem("Find users", null);
        menuBar.addItem("Add post", null);
        menuBar.addItem("Blocked users", null);
        menuBar.addItem("Friends", null);
        menuBar.setWidth("400");
        return menuBar;
    }

    private Table createTable(){
        Table newsTable = new Table("News");
        newsTable.addContainerProperty("User", Label.class, null);
        newsTable.addContainerProperty("Post", Button.class, null);
        newsTable.addContainerProperty("Details", Label.class, null);

/* Add a few items in the table. */
        for (int i=0; i<100; i++) {
            // Create the fields for the current table row
            Label sumField = new Label("User " + i);

            Integer itemId = new Integer(i);

            // Create a button and handle its click. A Button does not
            // know the item it is contained in, so we have to store the
            // item ID as user-defined data.
            Button postField = new Button("See post");
            postField.setData(itemId);
            postField.addClickListener(show_details_click);
            postField.addStyleName("link");
            Label detailField = new Label("Date");

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
    Button.ClickListener show_details_click = new Button.ClickListener(){
        @Override
        public void buttonClick(Button.ClickEvent clickEvent) {
            Integer iid = (Integer)clickEvent.getButton().getData();
          //  mCtx.showNotification("Link " +
               //     iid.intValue() + " clicked.");
        }
    };

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
    //</e
    //</editor-fold>
}
