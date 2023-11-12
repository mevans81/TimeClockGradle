
package timeclockgradle;

import java.util.Calendar;


//import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.networktables.*;    // .ValueEventData;
import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.GaugeBuilder;
import eu.hansolo.medusa.GaugeDesign;
import eu.hansolo.medusa.LcdDesign;
import eu.hansolo.medusa.LcdFont;
import eu.hansolo.medusa.Marker;
import eu.hansolo.medusa.Section;
import eu.hansolo.medusa.TickLabelLocation;
import eu.hansolo.medusa.TickLabelOrientation;
import eu.hansolo.medusa.TickMarkType;
import eu.hansolo.medusa.Gauge.KnobType;
import eu.hansolo.medusa.Gauge.LedType;
import eu.hansolo.medusa.Gauge.NeedleShape;
import eu.hansolo.medusa.Gauge.NeedleSize;
import eu.hansolo.medusa.Gauge.ScaleDirection;
import eu.hansolo.medusa.Gauge.SkinType;
import eu.hansolo.medusa.skins.ModernSkin;

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
import javafx.scene.layout.HBox;
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

public class Dashboard2 extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  private Gauge Voltage, Current, Gyro, Temp;

  @Override
  public void start(Stage primaryStage) {

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
    // Stage setup

    Voltage.setValue(11.8);
    Current.setValue(30.14);

    HBox displayHBox = new HBox(Temp);

    StackPane root = new StackPane(displayHBox);

    Pane canvas = new Pane();
    final Scene scene = new Scene(root, 800, 480);

    canvas.setStyle("-fx-background-color: white;");

    primaryStage.setScene(scene);
    //primaryStage.initStyle(StageStyle.UNDECORATED);
    primaryStage.show();

  }
}