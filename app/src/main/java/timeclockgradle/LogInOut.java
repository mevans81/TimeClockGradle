package timeclockgradle;

//import java.util.Calendar;
//import java.util.Date;

import java.text.DecimalFormat;

import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat.Type;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.application.Application;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import java.io.File;
import java.util.Scanner;

public class LogInOut extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private Image swervedrive;
    // private RobotData robotData;

    //    private Map<String, CheckBox> createdCheckBoxes = new HashMap<>();
    //   private Map<String, Gauge> gauges = new HashMap<>();
    
    private String[] memberCatagory       = new String[75];
    private String[] memberName           = new String[75];
    private String[] memberPosition       = new String[75];
    private int[] memberPositionNumber    = new int[75];
    
    private TextArea textOutput = new TextArea();
    private ListView<String> memberLV = new ListView<>();

    private static final int[] TestData = new int[] {  24,  1, 3, 23,  20, 21,  22,  5,
                                                      14, 7, 8,  9,   10, 11, 13,
                                                       17, 19,  16, 25, 28,  29 };

    private static final SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-YYYY HH:mm:ss");
    
Scanner scanner, scanner1;

  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE dd-MMM-yyyy HH:mm:ss");

  DateTimeFormatter dtfClockIn = DateTimeFormatter.ofPattern("EEE,dd,MMM,yyyy,HH,mm,ss");

    @Override
    public void start(Stage primaryStage) {

        // Initialize RobotData
        //     robotData = new RobotData("10.20.28.2", 1735);
        //     ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        //     executor.scheduleAtFixedRate(() -> {
        //         Map<String, Object> data = robotData.fetchData();
        //         Platform.runLater(() -> {
        //         //    updateCheckBoxes(data);
        //                 updateSmartDashboard(data);
        //          }); 
        //      }, 0, 20, TimeUnit.MILLISECONDS);

        Image swervedirve = null;
        
        try {
            swervedrive = new Image(new FileInputStream("c:\\TimeClock\\DashboardPics\\Swervedrive.png"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
            try {
                scanner = new Scanner(new File("C:\\TimeClockGradle\\app\\src\\main\\java\\timeclockgradle\\Data\\FPNames.txt"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

                while (scanner.hasNext()) {
                    String testString = new String();
                    testString = scanner.nextLine();
                    // System.out.println(testString); 
                    String[] tokens = testString.split(",");
                    int Numbx = Integer.parseInt(tokens[0]);
                    memberCatagory[Numbx] = tokens[2];
                    memberName[Numbx] = tokens[4];   
                    memberPosition[Numbx] = tokens[6];
                    memberPositionNumber[Numbx] = Integer.parseInt(tokens[8]);

//                        System.out.println(tokens[0] + "  " + tokens[2] + "  " + tokens[4]);
                  }            
                  scanner.close();

   LocalDateTime now = LocalDateTime.now();

for (int t=0; t<20; t++)
{
  String TempStr1 = String.format("%s", TestData[t]);  
  String TempStr2 = String.format("%-20s", memberName[TestData[t]]);
//  System.out.println(TempStr1+","+TempStr2 +","+ dtf.format(now));
//  ClockIn(TempStr1+","+TempStr2 +","+ dtfClockIn.format(now));      
}

//ClockedInRemove(8);

/* 
ClockOut(19);
ClockOut(11);
ClockOut(14);

ClockOut(20);
ClockOut(9);
ClockOut(21);
ClockOut(25);
ClockOut(23);
ClockOut(28);
ClockOut(17);
ClockOut(3); 
ClockOut(1);
ClockOut(7);
ClockOut(5);
ClockOut(8);
*/

memberAllData(23);

//CatagoryOut("Programming");

                LocalDateTime start = LocalDateTime.of(2023, 11, 4, 4, 35);

                String timeStamp = new String();

       
        ImageView SwerveDrive = new ImageView(swervedrive);
        SwerveDrive.setScaleX(.5);
        SwerveDrive.setScaleY(.5);

        StackPane root = new StackPane(textOutput);


        Pane canvas = new Pane();
        final Scene scene = new Scene(root, 800, 480);

        canvas.setStyle("-fx-background-color: black;");

        primaryStage.setScene(scene);
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();

    }

    //  private void updateSmartDashboard(Map<String, Object> data) {
    //    GridPane gridPane = new GridPane();
    //    int row = 0, col = 0;
    //    StringBuilder textOutput = new StringBuilder();

    // Handle Buttons and Gauges
    //   for (Map.Entry<String, Object> entry : data.entrySet()) {
    //      String key = entry.getKey();
    //      Object value = entry.getValue();
    //      String shortKey = key.replace("SmartDashboard/", "");
    //      FRCConfig.UIType uiType = FRCConfig.getUIType(shortKey);

    //      String rootTable = key.split("/")[0];
    //      CheckBox checkBox = createdCheckBoxes.get(rootTable);
    //  }

    ////Voltage.setValue(robotData.getDoubleValue("BatV")); 


    //}

void memberAllData(int data)
{

  try {
                scanner1 = new Scanner(new File("C:\\TimeClockGradle\\app\\src\\main\\java\\timeclockgradle\\Data\\TimeStampData.txt"));
                
                while (scanner1.hasNext()) {
                  String testString = scanner1.nextLine();
                  String[] tokens = testString.split(",");
                  int Numbx = Integer.parseInt(tokens[0]);
                  if (Numbx == data) 
                    {

                String CO1 = (tokens[2]+" "+ tokens[3]+" "+ tokens[4]+" - "+ tokens[5]+"   ");
                String CO2 = (tokens[6]+ " hrs"+"\n");

                System.out.print(CO1+CO2);
                textOutput.appendText(CO1+CO2);
                    }
                 }            
             
                scanner1.close();
                
           
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

}


void CatagoryOut(String Target)
{

textOutput.setStyle("-fx-font: Courier; -fx-font-weight: bold; -fx-font-size: 20;");
textOutput.appendText("         "+Target+"\n");
for (int j = 1; j <=10; j++)
 {
    for (int k = 0; k <= 60; k++){
       if (memberPosition[k].equals(Target) && (memberPositionNumber[k] == j))
       {
        textOutput.appendText(memberPositionNumber[k] + "\t"+ memberName[k]+"\n");
       }  
   }




}



 
 
}

boolean IsClockedIn(int data)
 {
    boolean clockedIn = false;

            try {
                scanner1 = new Scanner(new File("C:\\TimeClockGradle\\app\\src\\main\\java\\timeclockgradle\\Data\\ClockInData.txt"));
                
                while (scanner1.hasNext()) {
                  String testString = scanner1.nextLine();
                  String[] tokens = testString.split(",");
                  int Numbx = Integer.parseInt(tokens[0]);
                  if (Numbx == data) clockedIn = true;
                 }            
                System.out.println(data + "  "  + clockedIn);  
                scanner1.close();
                
           
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
          return clockedIn;

}
//////////////////////////////////////////////////////////////////////////////////////////////
public void ClockOut(int data) {
    String tempFile = ("C:\\TimeClockGradle\\app\\src\\main\\java\\timeclockgradle\\Data\\TimeStampData.txt");                         
    String currentLine;
    
   try {
       FileWriter fw = new FileWriter(tempFile, true);
       BufferedWriter bw = new BufferedWriter(fw);
       PrintWriter pw = new PrintWriter(bw);

       FileReader fr = new FileReader("C:\\TimeClockGradle\\app\\src\\main\\java\\timeclockgradle\\Data\\ClockInData.txt");
       BufferedReader br = new BufferedReader(fr);

       while ((currentLine = br.readLine()) != null)
           {  String[] tokens = currentLine.split(",");
              int Numbx = Integer.parseInt(tokens[0]);
              if (data == Numbx)  
                {
               // token    6 hour   7 min    8 sec
                LocalDateTime now3 = LocalDateTime.now();
    
                double hoursWorked = now3.getHour()-Integer.parseInt(tokens[6]);
                double minsWorked  = now3.getMinute()-Integer.parseInt(tokens[7]);
                double secsWorked  = now3.getSecond()-Integer.parseInt(tokens[8]);

                hoursWorked = hoursWorked + (minsWorked/60)+ (secsWorked/3600);
     
                String CO1 = (tokens[0]+", "+tokens[1]+","+tokens[2]+","+ tokens[3]+"-"+ tokens[4]+"-"+ tokens[5]+",");
                String CO2 = (tokens[6]+":"+tokens[7]+"."+tokens[8]+",");
                DateTimeFormatter dtf5 = DateTimeFormatter.ofPattern("HH:mm.ss");
                String CO3 = (dtf5.format(now3)+","+String.format("%.2f", hoursWorked));

                pw.println(CO1+CO2+CO3);
                } 
           }

       pw.flush();
       pw.close();
       fr.close();
       br.close();
       bw.close();
       fw.close();



   } catch (Exception e) {
    // TODO: handle exception
   }




}






public void ClockIn(String data) {
    FileWriter fr = null;
    try {
        File file = new File("C:\\TimeClockGradle\\app\\src\\main\\java\\timeclockgradle\\Data\\ClockInData.txt");
        fr = new FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(fr);
        PrintWriter pr = new PrintWriter(br);
        pr.println(data);
        pr.close();
        br.close();
        fr.close();

   } catch (IOException e) {
        e.printStackTrace();
        //fr.close();    
    }
}
/* +-----------------------------------------------+
   |                                               |
   |   ClockedInRemove                             |
   |                                               |
   |   Deletes all lines in the Clocked In file    |
   |   which are indexed by data                   |
   |                                               |
   ------------------------------------------------+     */
public void ClockedInRemove (int data) {
    String tempFile = "temp.txt";
    File oldFile = new File("C:\\TimeClockGradle\\app\\src\\main\\java\\timeclockgradle\\Data\\ClockInData.txt");
    File newFile = new File(tempFile);
                         
    String currentLine;

   try {
       FileWriter fw = new FileWriter(tempFile, true);
       BufferedWriter bw = new BufferedWriter(fw);
       PrintWriter pw = new PrintWriter(bw);

       FileReader fr = new FileReader("C:\\TimeClockGradle\\app\\src\\main\\java\\timeclockgradle\\Data\\ClockInData.txt");
       BufferedReader br = new BufferedReader(fr);

       while ((currentLine = br.readLine()) != null)
           {  String[] tokens = currentLine.split(",");
              int Numbx = Integer.parseInt(tokens[0]);
              if (data != Numbx)  pw.println(currentLine);
           }
       pw.flush();
       pw.close();
       fr.close();
       br.close();
       bw.close();
       fw.close();

       oldFile.delete();
       File dump = new File("C:\\TimeClockGradle\\app\\src\\main\\java\\timeclockgradle\\Data\\ClockInData.txt");
       newFile.renameTo(dump);

   } catch (Exception e) {
    // TODO: handle exception
   }

}





}
