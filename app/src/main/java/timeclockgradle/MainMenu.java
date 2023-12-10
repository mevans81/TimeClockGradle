package timeclockgradle;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu extends Application {

    public Sleep sleep = new Sleep();
    public TimeClock timeclock = new TimeClock();
    public Dashboard1 dashboard1 = new Dashboard1();
    public Dashboard2 dashboard2 = new Dashboard2();
    public Dashboard3 dashboard3 = new Dashboard3();
    public LogInOut logInOut = new LogInOut();
    


    // launch the application 
    public void start(Stage s) {

        MenuBar menuBar = new MenuBar();

        // set title for the stage 
        s.setTitle("Team 2028 TimeClock");

        Menu fileMenu = new Menu("_File");
        MenuItem openItem = new MenuItem("_Open");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem exitItem = new MenuItem("Exit");
        fileMenu.getItems().addAll(openItem, saveItem, new SeparatorMenuItem(), exitItem);

        Menu testMenu = new Menu("_Test Routines");
        MenuItem sleepItem = new MenuItem("_Sleep");
        MenuItem mainScreenItem = new MenuItem("_Main Screen");
        MenuItem dashboard1Item = new MenuItem("_Dashboard1");
        MenuItem dashboard2Item = new MenuItem("_Dashboard2"); 
        MenuItem dashboard3Item = new MenuItem("_Dashboard3");
        MenuItem timeStamptItem = new MenuItem("_Time Stamp");
        testMenu.getItems().addAll(sleepItem, mainScreenItem, dashboard1Item, dashboard2Item, dashboard3Item, timeStamptItem);

        Menu editMenu = new Menu("Edit");
        MenuItem cutItem = new MenuItem("Cut");
        MenuItem copyItem = new MenuItem("Copy");
        MenuItem pasteItem = new MenuItem("Paste");
        editMenu.getItems().addAll(cutItem, copyItem, pasteItem);

        Menu helpMenu = new Menu("Help");
        MenuItem aboutItem = new MenuItem("About");
        helpMenu.getItems().addAll(aboutItem);

        menuBar.getMenus().addAll(fileMenu, testMenu, editMenu, helpMenu);

        // label to display events 
        Label l = new Label("\t\t\t\t" + "no menu item selected");

        // action event 
        EventHandler < ActionEvent > aboutevent = new EventHandler < ActionEvent > () {
            public void handle(ActionEvent e) {
                l.setText("\n\n\n\n\n\t\t\t\t  This is version 1.0 of the Team 2028's Javafx based" +
                    "\n\n\t\t\t\t  TimeClock");
            }
        };

        EventHandler < ActionEvent > sleepevent = new EventHandler < ActionEvent > () {
            public void handle(ActionEvent e) {
                sleep.start(s);
            }
        };


        EventHandler < ActionEvent > mainScreenevent = new EventHandler < ActionEvent > () {
            public void handle(ActionEvent e) {
                timeclock.start(s);
            }
        };


        EventHandler < ActionEvent > dashboard1event = new EventHandler < ActionEvent > () {
            public void handle(ActionEvent e) {
                dashboard1.start(s);
            }
        };

        EventHandler < ActionEvent > dashboard2event = new EventHandler < ActionEvent > () {
            public void handle(ActionEvent e) {
                dashboard2.start(s);
            }
        };

        EventHandler < ActionEvent > dashboard3event = new EventHandler < ActionEvent > () {
            public void handle(ActionEvent e) {
                dashboard3.start(s);
            }
        };


        EventHandler < ActionEvent > timeStampevent = new EventHandler < ActionEvent > () {
            public void handle(ActionEvent e) {
                logInOut.start(s);
                
            }
        };


        // add event 
        aboutItem.setOnAction(aboutevent);
        sleepItem.setOnAction(sleepevent);
        mainScreenItem.setOnAction(mainScreenevent);
        dashboard1Item.setOnAction(dashboard1event);
        dashboard2Item.setOnAction(dashboard2event);
        dashboard3Item.setOnAction(dashboard3event);
        timeStamptItem.setOnAction(timeStampevent);

        // create a VBox 
        VBox vb = new VBox(menuBar, l);

///////////////////////



////////////////////////
        // create a scene 
        Scene sc = new Scene(vb, 800, 480);

        // set the scene 
        s.setScene(sc);

        s.show();
    }

    public static void main(String args[]) {
        // launch the application 
        launch(args);
    }
}