package timeclockgradle;

//import java.util.Calendar;
//import java.util.Date;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.time.LocalDateTime;    
import java.time.Period;
import java.time.Duration;
import java.time.format.DateTimeFormatter;  

import eu.hansolo.medusa.Gauge;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.application.Application;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import java.io.File;
import java.util.Scanner;


public class CAN_Bus_Health extends Application {
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

    
        try (Scanner scanner = new Scanner(new File("C:\\TimeClockGradle\\app\\src\\main\\java\\timeclockgradle\\FP_Names.txt"))) {

        while (scanner.hasNext())
            {   String testString = new String();
                testString = scanner.nextLine();
               // System.out.println(testString); 
                String[] tokens = testString.split(",");
               // int CAN_ID = Integer.parseInt(tokens[0]);
               // double longitude = Double.parseDouble(tokens[1]);
            
               System.out.println(tokens[0]+"  "+tokens[2]+"  "+tokens[4]);
            }

            
         
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d MMM yyyy H:mm:ss");  
            LocalDateTime now = LocalDateTime.now();  
 
            LocalDateTime start = LocalDateTime.of(2023, 11, 4, 4, 35);

         //   System.out.println(dtf.format(now));  

            Duration dur = Duration.between(start, now);
        
            String result = String.format("%d:%02d", dur.toHours(), dur.toMinutesPart());
            System.out.println(result);
     double timeWorked = (dur.toMinutes()/60.0);
            System.out.println(timeWorked);



    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    
     ImageView SwerveDrive = new ImageView(swervedrive);

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