package timeclockgradle;

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

public class Dashboard1 extends Application {
  public static void main(String[] args) {
    launch(args);
  }

 private RobotData robotData;
     private TextArea textArea = new TextArea();
    private VBox smartDashboardVBox = new VBox();
    private VBox checkBoxVBox = new VBox();  // Class-level variable
    private Map<String, CheckBox> createdCheckBoxes = new HashMap<>();
    private Map<String, Gauge> gauges = new HashMap<>();
  private Gauge Voltage, Current, Gyro, Temp;

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


    Gyro = GaugeBuilder.create()
      .skinType(Gauge.SkinType.GAUGE)
      .backgroundPaint(Color.BLACK)
      .foregroundBaseColor(Color.WHITE)
      .prefSize(380, 400)
      .minValue(0)
      .maxValue(360)

      .startAngle(180)
      .angleRange(360)
      .autoScale(false)

      .valueVisible(true)
      .minorTickMarksVisible(false)
      .majorTickMarkType(TickMarkType.BOX)
      .mediumTickMarkType(TickMarkType.BOX)
      .title("Gyro")
      .needleShape(NeedleShape.ROUND)
      .needleSize(NeedleSize.THICK)
      .needleColor(Color.RED)
      .knobColor(Color.WHITE)
      .customTickLabelsEnabled(true)
      .customTickLabelFontSize(35)
      .customTickLabels("0", "45", "90", "135", "180", "225", "270", "315")
      // .animated(true)
      .build();

    Voltage = GaugeBuilder.create()
      .skinType(Gauge.SkinType.HORIZONTAL)
      .backgroundPaint(Color.BLACK)
      .foregroundBaseColor(Color.WHITE)
      .prefSize(380, 400)
      .startAngle(290)
      .angleRange(220)
      .minValue(7)
      .maxValue(13)
      .valueVisible(true)
      .minorTickMarksVisible(false)
      .majorTickMarkType(TickMarkType.BOX)
      .mediumTickMarkType(TickMarkType.BOX)
      .title("Battery\nVoltage")
      .needleShape(NeedleShape.ROUND)
      .needleSize(NeedleSize.THICK)
      .needleColor(Color.RED)
      .knobColor(Color.WHITE)
      .customTickLabelsEnabled(true)
      .customTickLabelFontSize(35)
      .customTickLabels("7", "8", "9", "10", "11", "12", "13")
      .sections(new Section(7, 9, Color.RED),
        new Section(9, 11, Color.YELLOW),
        new Section(11, 13, Color.FORESTGREEN))
      .sectionsVisible(true)
      .animated(true)
      .build();

    Temp = GaugeBuilder.create()
      .skinType(Gauge.SkinType.HORIZONTAL)
      .backgroundPaint(Color.BLACK)
      .foregroundBaseColor(Color.WHITE)
      .prefSize(380, 400)
      //   .startAngle(270)
      .angleRange(180)
      .minValue(60)
      .maxValue(260)
      .valueVisible(true)
      .minorTickMarksVisible(false)

      .majorTickMarkType(TickMarkType.BOX)
      //.mediumTickMarkType(TickMarkType.BOX)
      .title("Motor\nTemp")
      .needleShape(NeedleShape.ROUND)
      .needleSize(NeedleSize.THICK)
      .needleColor(Color.RED)
      .knobColor(Color.WHITE)
      .customTickLabelsEnabled(true)
      .customTickLabelFontSize(35)
      .customTickLabels("60", " ", "100", " ", "140", " ", "180",
        " ", "220", " ", "260")
      .sections(new Section(50, 100, Color.GREEN),
        new Section(100, 200, Color.YELLOW),
        new Section(200, 300, Color.RED))
      .sectionsVisible(true)
      .animated(false)
      .build();

    Current = GaugeBuilder.create()
      .skinType(Gauge.SkinType.HORIZONTAL)
      .backgroundPaint(Color.BLACK)
      .foregroundBaseColor(Color.WHITE)
      .prefSize(380, 400)
      .startAngle(290)
      .angleRange(220)
      .minValue(0)
      .maxValue(80)
      .valueVisible(true)
      .minorTickMarksVisible(false)
      .majorTickMarkType(TickMarkType.BOX)
      .mediumTickMarkType(TickMarkType.BOX)
      .title("Battery\nCurrent")
      .needleShape(NeedleShape.ROUND)
      .needleSize(NeedleSize.THICK)
      .needleColor(Color.RED)
      .knobColor(Color.WHITE)
      .customTickLabelsEnabled(true)
      .customTickLabelFontSize(35)
      .customTickLabels("0", "10", "20", "30", "40", "50", "60", "70", "80")
      .sectionsVisible(true)
      .animated(true)
      .build();

//    Voltage.setValue(11.8);
//    Current.setValue(30.14);

    HBox displayHBox = new HBox(Voltage, Current, Gyro);

    StackPane root = new StackPane(displayHBox);

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


Voltage.setValue(robotData.getDoubleValue("BatV")); 
Current.setValue(robotData.getDoubleValue("BatI"));   
Gyro.setValue((robotData.getDoubleValue("BotA")%360)*1.111111); 

  }
}