package javafx.util;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

import com.sun.javafx.robot.FXRobot;
import com.sun.javafx.robot.FXRobotFactory;
import com.sun.javafx.robot.FXRobotImage;

public class SceneRobotImpl implements SceneRobot {

    //---------------------------------------------------------------------------------------------
    // PRIVATE FIELDS.
    //---------------------------------------------------------------------------------------------

    private final FXRobot fxRobot;

    //---------------------------------------------------------------------------------------------
    // CONSTRUCTORS.
    //---------------------------------------------------------------------------------------------

    public SceneRobotImpl(Scene scene) {
        fxRobot = createFxRobot(scene);
    }

    //---------------------------------------------------------------------------------------------
    // METHODS.
    //---------------------------------------------------------------------------------------------

    @Override
    public void keyPress(KeyCode key) {
        fxRobot.keyPress(key);
    }

    @Override
    public void keyRelease(KeyCode key) {
        fxRobot.keyRelease(key);
    }

    @Override
    public void keyType(KeyCode key, String character) {
        fxRobot.keyType(key, character);
    }

    // MOUSE.

    @Override
    public Point2D getMouseLocation() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void mouseMove(Point2D location) {
        fxRobot.mouseMove((int) location.getX(), (int) location.getY());
    }

    @Override
    public void mousePress(MouseButton button, int clickCount) {
        fxRobot.mousePress(button, clickCount);
    }

    @Override
    public void mouseRelease(MouseButton button, int clickCount) {
        fxRobot.mouseRelease(button, clickCount);
    }

    @Override
    public void mouseClick(MouseButton button, int clickCount) {
        fxRobot.mouseClick(button, clickCount);
    }

    @Override
    public void mousePress(MouseButton button) {
        fxRobot.mousePress(button);
    }

    @Override
    public void mouseRelease(MouseButton button) {
        fxRobot.mouseRelease(button);
    }

    @Override
    public void mouseClick(MouseButton button) {
        fxRobot.mouseClick(button);
    }

    @Override
    public void mouseDrag(MouseButton button) {
        fxRobot.mouseDrag(button);
    }

    @Override
    public void mouseWheel(int wheelAmount) {
        fxRobot.mouseWheel(wheelAmount);
    }

    @Override
    public Color getCapturePixelColor(Point2D location) {
        int fxRobotColor = fxRobot.getPixelColor((int) location.getX(), (int) location.getY());
        return convertFromFxRobotColor(fxRobotColor);
    }

    @Override
    public Image getCaptureRegion(Rectangle2D region) {
        FXRobotImage fxRobotImage = fxRobot.getSceneCapture(
            (int) region.getMinX(), (int) region.getMinY(),
            (int) region.getWidth(), (int) region.getHeight()
        );
        return convertFromFxRobotImage(fxRobotImage);
    }

    //---------------------------------------------------------------------------------------------
    // PRIVATE METHODS.
    //---------------------------------------------------------------------------------------------

    private FXRobot createFxRobot(Scene scene) {
        FXRobot fxRobot = FXRobotFactory.createRobot(scene);
        fxRobot.setAutoWaitForIdle(false);
        return fxRobot;
    }

    private Color convertFromFxRobotColor(int fxRobotColor) {
        throw new UnsupportedOperationException();
    }

    private Image convertFromFxRobotImage(FXRobotImage fxRobotImage) {
        throw new UnsupportedOperationException();
    }

}
