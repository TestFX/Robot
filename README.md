## OpenJFX/Robot

This is a test bed for a public API of JavaFX's robot classes. These classes are for purposes of
test automation, self running demos and other applications where control of the mouse and keyboard
is needed.

**Note:** This API is unstable and subject to change. The code is currently intended
for JDK `1.8.0_65`, this will change to JDK `1.9` eventually.


### Candidates

Glass robot:

- `com.sun.glass.ui.Robot`
- `com.sun.glass.ui.Pixels`
- `com.sun.glass.ui.Application::createRobot()`
- `com.sun.glass.ui.Application::createPixels()`

Scene robot:

- `com.sun.javafx.robot.FXRobot`
- `com.sun.javafx.robot.FXRobotFactory`
- `com.sun.javafx.robot.FXRobotImage`
