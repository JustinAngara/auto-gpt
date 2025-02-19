import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Click {
  static Robot robot;
  static int caX = 580;// click away x coord
  static int caY = 603;// click away y coord
  public static void run(int x, int y) throws AWTException, InterruptedException {
    // inits
    robot = new Robot();
    
    // init coords
    grabCoords();
    
    // first move to gpt search
    move(x,y);
    // click
    click();
    // paste
    press(KeyEvent.VK_CONTROL);
    press(KeyEvent.VK_V);
    
    release(KeyEvent.VK_CONTROL);
    release(KeyEvent.VK_V);
    move(caX,caY);
    click();
    
    Thread.sleep(1500);
    // press enter
    move(x,y);
    click();
    press(KeyEvent.VK_ENTER);
    release(KeyEvent.VK_ENTER);
    
    // click away
    move(caX,caY);
    click();
    
  }
  
  public static void grabCoords() {
    Point cursorLoc = MouseInfo.getPointerInfo().getLocation();

    // extract x and y coordinates
    caX = (int) cursorLoc.getX();
    caY = (int) cursorLoc.getY();
  }
  
  
  public static void move(int x, int y) {
    robot.mouseMove(x, y);
  }
  
  public static void click() {
    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
  }
  
  public static void press(int code) {
    robot.keyPress(code);
  }
  
  public static void release(int code) {
    robot.keyRelease(code); 
  }
}
