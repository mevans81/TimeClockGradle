package timeclockgradle;

import java.util.Calendar;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class AnalogClockWork {

    private static final double HOURS_ON_CLOCK = 12d;
    private static final double SECONDS_ON_CLOCK = 60d;
    private static final double MINUTES_ON_CLOCK = 60d;
    private static final double DEGREES_PER_SECOND = 360d / SECONDS_ON_CLOCK;
    private static final double DEGREES_PER_MINUTE = 360d / MINUTES_ON_CLOCK;
    private static final double DEGREES_PER_HOUR = 360d / HOURS_ON_CLOCK;
    private static final Duration SECONDS_HAND_TICK = Duration.millis(50);
    private static final Duration MINUTE_HAND_TICK = Duration.millis(500);
    private static final Duration HOUR_HAND_TICK = Duration.seconds(10);

    private final SimpleDoubleProperty hourHandAngle = new SimpleDoubleProperty(0);
    private final SimpleDoubleProperty minuteHandAngle = new SimpleDoubleProperty(0);
    private final SimpleDoubleProperty secondsHandAngle = new SimpleDoubleProperty(0);


    public AnalogClockWork() {
        updateHandAngles();
        startTicking();
    }

    
    public DoubleProperty hourHandAngle() {
        return hourHandAngle;
    }

    public DoubleProperty minuteHandAngle() {
        return minuteHandAngle;
    }

    public DoubleProperty secondsHandAngle() {
        return secondsHandAngle;
    }



    private void updateHandAngles() {
        updateSecondsHandAngle();
        updateMinuteHandAngle();
        updateHourHandAngle();
    }

    private void startTicking() {
        startHandTicking(SECONDS_HAND_TICK, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateSecondsHandAngle();
            }
        });
        startHandTicking(MINUTE_HAND_TICK, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateMinuteHandAngle();
            }
        });
        startHandTicking(HOUR_HAND_TICK, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateHourHandAngle();
            }
        });
    }

    private void startHandTicking(Duration tickDuration, EventHandler<ActionEvent> onTick) {

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(tickDuration, onTick));
        /*
         TimelineBuilder.create()
         .cycleCount(Timeline.INDEFINITE)
         .keyFrames(new KeyFrame(tickDuration, onTick))
         .build()
         */
        timeline.play();
    }

    private void updateHourHandAngle() {
        hourHandAngle.set(currentHourWithFractions() * DEGREES_PER_HOUR);
    }

    private void updateMinuteHandAngle() {
        minuteHandAngle.set(currentMinuteWithFractions() * DEGREES_PER_MINUTE);
    }

    private void updateSecondsHandAngle() {
        secondsHandAngle.set(currentSecondsWithFractions() * DEGREES_PER_SECOND);
    }

    private double currentHourWithFractions() {
        double hours = (double) Calendar.getInstance().get(Calendar.HOUR);
        return hours + currentMinuteWithFractions() / MINUTES_ON_CLOCK;
    }

    private double currentMinuteWithFractions() {
        double minutes = (double) Calendar.getInstance().get(Calendar.MINUTE);
        return minutes + currentSecondsWithFractions() / SECONDS_ON_CLOCK;
    }

    private double currentSecondsWithFractions() {
        Calendar calendar = Calendar.getInstance();
        double currentSeconds = calendar.get(Calendar.SECOND);
        double currentMilliseconds = calendar.get(Calendar.MILLISECOND);
        return currentSeconds + currentMilliseconds / 1000d;
    }



}
