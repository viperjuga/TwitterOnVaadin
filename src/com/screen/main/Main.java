package com.screen.main;

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

    Label mForename;
    Label mSurname;
    Table mNews;
    Embedded mPhoto;
    MenuBar mMenu;

    //<editor-fold desc="Constructor">
    public Main(){
        mForename = new Label();
        mSurname = new Label();
        mNews = new Table("News");
        mMenu = new MenuBar();
        //mPhoto = new Embedded("Image", new ThemeResource("D:\\Downloads\\webDownloads\\lUugMYLfJP0.jpg"));
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

        mNews.addContainerProperty("User", Label.class, null);
        mNews.addContainerProperty("Post", Button.class, null);

/* Add a few items in the table. */
        for (int i=0; i<100; i++) {
            // Create the fields for the current table row
            Label sumField = new Label("User " + i);

            Integer itemId = new Integer(i);

            // Create a button and handle its click. A Button does not
            // know the item it is contained in, so we have to store the
            // item ID as user-defined data.
            Button detailsField = new Button("Show post");
            detailsField.setData(itemId);
            detailsField.addClickListener(show_details_click);
            detailsField.addStyleName("link");

            // Create the table row.
            mNews.addItem(new Object[] {sumField, detailsField},
                    itemId);
        }

// Show just three rows because they are so high.
        mNews.setPageLength(5);

        mNews.setWidth("400");
        mNews.setHeight("400");

        mMenu.addItem("Change profile", null);
        mMenu.addItem("Find user", null);
        mMenu.addItem("Add post", null);
        mMenu.setWidth("400");
        Image image = new Image("",new ClassResource("D:\\Downloads\\webDownloads\\lUugMYLfJP0.png"));
        image.setHeight("30");
        image.setWidth("30");

// Instruct browser not to cache the image

// Display the image
        customLayout.addComponent(mNews, "wall");
        customLayout.addComponent(image, "profile");
//        customLayout.addComponent(mPhoto, "profile");
        customLayout.addComponent(mMenu,"menu");
//        customLayout.addComponent(mPhoto, "profile");

        panel.setContent(customLayout);
        setSizeFull();
        addComponent(panel);
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
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
