package timeclockgradle;

import java.util.Calendar;
import java.time.Duration;
import java.time.format.DateTimeFormatter;  

import javafx.application.Platform;
import javafx.geometry.HorizontalDirection;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javafx.geometry.Bounds;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.application.Application; 
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeClock extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private AnalogClock Clock;
    private Image phantom, firstLogo;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        Button btn = new Button();
        btn.setText("Phantom Robotics'");
        btn.setOnAction(new EventHandler < ActionEvent > () {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        Image phantom = null;
        Image firstLogo = null;
        Image prLogo = null;

        try {
            phantom = new Image(new FileInputStream("c:\\TimeClock\\SystemPics\\Phantom2.png"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            firstLogo = new Image(new FileInputStream("c:\\TimeClock\\SystemPics\\First Logo.png"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            prLogo = new Image(new FileInputStream("c:\\TimeClock\\SystemPics\\PR Logo.png"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        AnalogClock Clock = new AnalogClock();

        ImageView Phantom = new ImageView(phantom);
        Phantom.setX(0);
        Phantom.setFitWidth(400);
        Phantom.setY(195);
        Phantom.setPreserveRatio(true);

        ImageView FirstLogo = new ImageView(firstLogo);
        FirstLogo.setX(20);
        FirstLogo.setFitWidth(150);
        FirstLogo.setY(40);
        FirstLogo.setPreserveRatio(true);

        ImageView PRLogo = new ImageView(prLogo);
        PRLogo.setX(425);
        PRLogo.setFitWidth(300);
        PRLogo.setY(300);
        PRLogo.setPreserveRatio(true);


        Pane canvas = new Pane();
        final Scene scene = new Scene(canvas, 800, 480);

        Node TempClock = Clock.getClock();
        TempClock.setLayoutX(450);
        TempClock.setLayoutY(0);
        TempClock.setScaleX(1.0);
        TempClock.setScaleY(1.0);


            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEEE - MMM d, yyyy"); // H:mm:ss");  
            LocalDateTime now = LocalDateTime.now();  
 
            LocalDateTime start = LocalDateTime.of(2023, 11, 4, 4, 35);

         //   System.out.println(dtf.format(now));  

            Duration dur = Duration.between(start, now);
        
            String result = String.format("%d:%02d", dur.toHours(), dur.toMinutesPart());
            System.out.println(result);
     double timeWorked = (dur.toMinutes()/60.0);
            System.out.println(timeWorked);

    

        Text Date1 = new Text();

Date1.setX(20);
Date1.setY(25);
Date1.setText(now.format(dtf));
Date1.setFont(Font.font ("Verdana",FontWeight.BOLD, 30));


        canvas.getChildren().add(Date1);
        canvas.setStyle("-fx-background-color: white;");
        canvas.getChildren().add(TempClock);



        canvas.getChildren().add(FirstLogo);
        canvas.getChildren().add(Phantom);
        canvas.getChildren().add(PRLogo);

        primaryStage.setScene(scene);
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();

    }
}