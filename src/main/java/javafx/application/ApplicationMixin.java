package javafx.application;

import javafx.scene.Scene;
import javafx.util.GlassRobot;
import javafx.util.GlassRobotImpl;
import javafx.util.SceneRobot;
import javafx.util.SceneRobotImpl;

public class ApplicationMixin {

    public static GlassRobot createGlassRobot() {
        return new GlassRobotImpl();
    }

    public static SceneRobot createSceneRobot(Scene scene) {
        return new SceneRobotImpl(scene);
    }

}
