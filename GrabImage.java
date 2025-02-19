import java.awt.AWTException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;

public class GrabImage implements Transferable {
  // dimensions of a screen
  private int height;
  private int width;
  private Image image;
  
  
  // this is for mouse coords not where to take image
  
  private int m[];
  private int s[];
  public GrabImage() {
    height = 1080;
    width = 1920;
    
    // main coords
    m = new int[]{342,-170};
    
    // split coords
    s = new int[] {1264, 939};
  }
  public BufferedImage retrieveImage() throws InterruptedException {
    try {
      Robot robot = new Robot();

      Rectangle screenRect = new Rectangle(0,0,width,height);
      BufferedImage screenFullImage = robot.createScreenCapture(screenRect);

      copyImage(screenFullImage);
      Click.run(s[0], s[1]);
      return screenFullImage;
    } catch (AWTException ex) {
        System.err.println(ex);
        return null;
    } 
  }
  
  public void copyImage(BufferedImage image) {
    this.image = image; // Store the image correctly
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(this, null);
    
  }
  
  public DataFlavor[] getTransferDataFlavors() {
    return new DataFlavor[] { DataFlavor.imageFlavor };
  }

  public boolean isDataFlavorSupported(DataFlavor flavor) {
    return DataFlavor.imageFlavor.equals(flavor);
  }

  public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
    if (!DataFlavor.imageFlavor.equals(flavor)) {
      throw new UnsupportedFlavorException(flavor);
    }
    return image;
  }
}
