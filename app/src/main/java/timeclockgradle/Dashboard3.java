package timeclockgradle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.HashMap;

import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.GaugeBuilder;

import eu.hansolo.medusa.Section;

import eu.hansolo.medusa.TickMarkType;
import eu.hansolo.medusa.Gauge.NeedleShape;
import eu.hansolo.medusa.Gauge.NeedleSize;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.application.Application;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Dashboard3 extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  private Image swervedrive;
 private RobotData robotData;
     private TextArea textArea = new TextArea();
    private VBox smartDashboardVBox = new VBox();
    private VBox checkBoxVBox = new VBox();  // Class-level variable
    private Map<String, CheckBox> createdCheckBoxes = new HashMap<>();
    private Map<String, Gauge> gauges = new HashMap<>();
  
  

  @Override
  public void start(Stage primaryStage) {

     // Initialize RobotData
        robotData = new RobotData("10.20.28.2", 1735);

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            Map<String, Object> data = robotData.fetchData();
            Platform.runLater(() -> {
            //    updateCheckBoxes(data);
                   updateSmartDashboard(data);
            }); 
        }, 0, 20, TimeUnit.MILLISECONDS);


        Image swervedirve = null;

        try {
            swervedrive = new Image(new FileInputStream("c:\\TimeClock\\DashboardPics\\Swervedrive.png"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ImageView SwerveDrive = new ImageView(swervedrive);
        SwerveDrive.setX(350);
        SwerveDrive.setFitWidth(200);
        SwerveDrive.setY(40);
        SwerveDrive.setPreserveRatio(true);


    StackPane root = new StackPane(SwerveDrive);

    Pane canvas = new Pane();
    final Scene scene = new Scene(root, 800, 480);

    canvas.setStyle("-fx-background-color: black;");

    primaryStage.setScene(scene);
    //primaryStage.initStyle(StageStyle.UNDECORATED);
    primaryStage.show();

  }

  private void updateSmartDashboard(Map<String, Object> data) {
    GridPane gridPane = new GridPane();
    int row = 0, col = 0;
    StringBuilder textOutput = new StringBuilder();

    // Handle Buttons and Gauges
    for (Map.Entry<String, Object> entry : data.entrySet()) {
        String key = entry.getKey();
        Object value = entry.getValue();
        String shortKey = key.replace("SmartDashboard/", "");
        FRCConfig.UIType uiType = FRCConfig.getUIType(shortKey);
        
        String rootTable = key.split("/")[0];
        CheckBox checkBox = createdCheckBoxes.get(rootTable);
    }


////Voltage.setValue(robotData.getDoubleValue("BatV")); 


  }
}