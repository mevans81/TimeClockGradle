package timeclockgradle;

import java.util.Calendar;

import javafx.application.Platform;
import javafx.geometry.HorizontalDirection;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
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
import javafx.util.Duration;
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
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Sleep extends Application {
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

        try {
            phantom = new Image(new FileInputStream("c:\\FRC Java Code\\Phantom2.png"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            firstLogo = new Image(new FileInputStream("c:\\FRC Java Code\\First Logo.png"));
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
        FirstLogo.setY(20);
        FirstLogo.setPreserveRatio(true);

        Pane canvas = new Pane();
        final Scene scene = new Scene(canvas, 800, 480);

        Node TempClock = Clock.getClock();
        //TempClock.setLayoutX(415);
        //TempClock.setLayoutY(-95);
        //TempClock.setScaleX(.6);TempClock.setScaleY(.6);

        TempClock.setScaleX(.6);
        TempClock.setScaleY(.6);

        TempClock.relocate(100, 100);

        final Timeline loop = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler < ActionEvent > () {

            double deltaX = 1;
            double deltaY = 1;

            @Override
            public void handle(final ActionEvent t) {
                TempClock.setLayoutX(TempClock.getLayoutX() + deltaX);
                TempClock.setLayoutY(TempClock.getLayoutY() + deltaY);

                final Bounds bounds = canvas.getBoundsInLocal();

                //System.out.println(TempClock.getLayoutX()+"  "+bounds.getMaxX()+"   "+ TempClock.getLayoutY()+"  "+bounds.getMaxY());

                final boolean atRightBorder = TempClock.getLayoutX() >= (bounds.getMaxX() - 240); // TempClock.getRadius());
                final boolean atLeftBorder = TempClock.getLayoutX() <= (bounds.getMinX() - 60); // TempClock.getRadius());
                final boolean atBottomBorder = TempClock.getLayoutY() >= (bounds.getMaxY() - 240); // TempClock.getRadius());
                final boolean atTopBorder = TempClock.getLayoutY() <= (bounds.getMinY() - 60); // TempClock.getRadius());

                if (atRightBorder || atLeftBorder) {
                    //System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                    deltaX *= -1;
                }
                if (atBottomBorder || atTopBorder) {
                    //System.out.println("---------------------------------------------------------------------------");
                    deltaY *= -1;
                }
            }
        }));

        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();

        canvas.setStyle("-fx-background-color: black;");
        canvas.getChildren().add(TempClock);

        //startup.getChildren().add(FirstLogo);
        //    startup.getChildren().add(Phantom);

        primaryStage.setScene(scene);
 //       primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();

    }
}