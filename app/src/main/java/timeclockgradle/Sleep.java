package timeclockgradle;

import java.util.Calendar;

import javafx.scene.Node;
import javafx.scene.Scene;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.geometry.Bounds;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class Sleep extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private AnalogClock Clock;

    @Override
    public void start(Stage primaryStage) {        
 
        AnalogClock Clock = new AnalogClock();

        Pane canvas = new Pane();
        final Scene scene = new Scene(canvas, 800, 480);

        Node TempClock = Clock.getClock();

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
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();

    }
}