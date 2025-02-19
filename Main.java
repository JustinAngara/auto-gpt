import java.awt.AWTException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class Main implements NativeKeyListener{
  static GrabImage g;
  public static void main(String[] args) throws InterruptedException{
    g = new GrabImage();
    init();
    
    
  }
  
  @Override
  public void nativeKeyPressed(NativeKeyEvent arg0) {
      // TODO Auto-generated method stub
      if(arg0.getKeyCode()==NativeKeyEvent.VC_ALT) {
        try {
          g.retrieveImage();
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        System.out.println("test");
      }
  }

  @Override
  public void nativeKeyReleased(NativeKeyEvent arg0) {
      // TODO Auto-generated method stub
      
  }

  @Override
  public void nativeKeyTyped(NativeKeyEvent arg0) {
      // TODO Auto-generated method stub
      
  }
  
  public static void init() {
    GlobalScreen.addNativeKeyListener(new Main());
    LogManager.getLogManager().reset();

    // Get the logger for "org.jnativehook" and set the level to off.
    Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
    logger.setLevel(Level.OFF);
    try {
        GlobalScreen.registerNativeHook();
    } catch (NativeHookException ex) {
    
    }
  }
  
  
}
