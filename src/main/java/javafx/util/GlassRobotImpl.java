package javafx.util;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

import com.sun.glass.ui.Application;
import com.sun.glass.ui.Pixels;
import com.sun.glass.ui.Robot;

public class GlassRobotImpl implements GlassRobot {

    //---------------------------------------------------------------------------------------------
    // CONSTANTS.
    //---------------------------------------------------------------------------------------------

    public static final int BYTE_BUFFER_BYTES_PER_COMPONENT = 1;
    public static final int INT_BUFFER_BYTES_PER_COMPONENT = 4;

    //---------------------------------------------------------------------------------------------
    // PRIVATE FIELDS.
    //---------------------------------------------------------------------------------------------

    private final Robot internalRobot;

    //---------------------------------------------------------------------------------------------
    // CONSTRUCTORS.
    //---------------------------------------------------------------------------------------------

    public GlassRobotImpl() {
        internalRobot = createGlassRobot();
    }

    //---------------------------------------------------------------------------------------------
    // METHODS.
    //---------------------------------------------------------------------------------------------

    @Override
    public void destroy() {
        internalRobot.destroy();
    }

    @Override
    public void keyPress(KeyCode key) {
        internalRobot.keyPress(convertToKeyCodeId(key));
    }

    @Override
    public void keyRelease(KeyCode key) {
        internalRobot.keyRelease(convertToKeyCodeId(key));
    }

    @Override
    public Point2D getMouseLocation() {
        return convertFromCoordinates(internalRobot.getMouseX(), internalRobot.getMouseY());
    }

    @Override
    public void mouseMove(Point2D location) {
        internalRobot.mouseMove((int) location.getX(), (int) location.getY());
    }

    @Override
    public void mousePress(MouseButton button) {
        internalRobot.mousePress(convertToButtonId(button));
    }

    @Override
    public void mouseRelease(MouseButton button) {
        internalRobot.mouseRelease(convertToButtonId(button));
    }

    @Override
    public void mouseWheel(int wheelAmount) {
        internalRobot.mouseWheel(wheelAmount);
    }

    @Override
    public Color getCapturePixelColor(Point2D location) {
        int glassColor = internalRobot.getPixelColor(
            (int) location.getX(), (int) location.getY()
        );
        return convertFromGlassColor(glassColor);
    }

    @Override
    public Image getCaptureRegion(Rectangle2D region) {
        Pixels glassPixels = internalRobot.getScreenCapture(
            (int) region.getMinX(), (int) region.getMinY(),
            (int) region.getWidth(), (int) region.getHeight()
        );
        return convertFromGlassPixels(glassPixels);
    }

    //---------------------------------------------------------------------------------------------
    // PRIVATE METHODS.
    //---------------------------------------------------------------------------------------------

    private Robot createGlassRobot() {
        return Application.GetApplication().createRobot();
    }

    @SuppressWarnings("deprecation")
    private int convertToKeyCodeId(KeyCode keyCode) {
        return keyCode.impl_getCode();
    }

    private Point2D convertFromCoordinates(int pointX,
                                           int pointY) {
        return new Point2D(pointX, pointY);
    }

    private int convertToButtonId(MouseButton button) {
        switch (button) {
            case PRIMARY: return Robot.MOUSE_LEFT_BTN;
            case SECONDARY: return Robot.MOUSE_RIGHT_BTN;
            case MIDDLE: return Robot.MOUSE_MIDDLE_BTN;
        }
        throw new IllegalArgumentException("MouseButton not supported.");
    }

    private Color convertFromGlassColor(int glassColor) {
        WritableImage image = new WritableImage(1, 1);
        image.getPixelWriter().setArgb(0, 0, glassColor);
        return image.getPixelReader().getColor(0, 0);
    }

    private Image convertFromGlassPixels(Pixels glassPixels) {
        int width = glassPixels.getWidth();
        int height = glassPixels.getHeight();
        WritableImage image = new WritableImage(width, height);

        int bytesPerComponent = glassPixels.getBytesPerComponent();
        if (bytesPerComponent == INT_BUFFER_BYTES_PER_COMPONENT) {
            IntBuffer intBuffer = (IntBuffer) glassPixels.getPixels();
            writeIntBufferToImage(intBuffer, image);
        }
        else if (bytesPerComponent == BYTE_BUFFER_BYTES_PER_COMPONENT) {
            ByteBuffer byteBuffer = (ByteBuffer) glassPixels.getPixels();
            writeByteBufferToImage(byteBuffer, image);
        }

        return image;
    }

    private void writeIntBufferToImage(IntBuffer intBuffer,
                                       WritableImage image) {
        PixelWriter pixelWriter = image.getPixelWriter();
        double width = image.getWidth();
        double height = image.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int argb = intBuffer.get();
                pixelWriter.setArgb(x, y, argb);
            }
        }
    }

    private void writeByteBufferToImage(ByteBuffer byteBuffer,
                                        WritableImage image) {
        throw new UnsupportedOperationException("Writing from byte buffer is not supported.");
    }

}
