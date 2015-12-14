package javafx.application;

import javafx.util.GlassRobot;
import javafx.util.GlassRobotImpl;

public class ApplicationMixin {

    public static GlassRobot createGlassRobot() {
        return new GlassRobotImpl();
    }

}
