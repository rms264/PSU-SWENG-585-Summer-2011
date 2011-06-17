package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.io.File;

public class KahinduImage {
  private Image image;
  private ColorModel cm = ColorModel.getRGBdefault();
  private boolean imageComesFromFile = false;
  private Frame frame;


  // No, you can't use bytes, cause
  // Java bytes are signed. Yeah, that bites!
  private  short r[][];
  private  short g[][];
  private  short b[][];

  private int width = 128;
  private int height = 128;
  private static int min =  10000;
  private static int max = -10000;
  private boolean clipped = false;

  // A default file name..set to null
  // to start with file open dialog.
  // Set to string to start with an image.
  // Use a fully qualified path name, in quotes.
  private String fileName = null;

  
  public KahinduImage(Frame frame) {
    this.frame = frame;
    initialize();
  }


  private void initialize() {
    grabNumImage();
  }
  
  public int getWidth(){return width;}
  public int getHeight(){return height;}
  public short[][] getRed(){return r;}
  public short[][] getGreen(){return g;}
  public short[][] getBlue(){return b;}
   
  public void grabNumImage() {
    imageComesFromFile = false;
    width = NumImage.gray.length;
    height = NumImage.gray[0].length;
    r = new short[width][height];
    g = new short[width][height];
    b = new short[width][height];
    for (int y=0; y < height  ;y++) {
      for (int x=0;x< width;x++) {
        r[x][y] = NumImage.gray[y][x];
        g[x][y] = NumImage.gray[y][x];
        b[x][y] = NumImage.gray[y][x];

      }
    }
    short2Image();
  }

  

  public void setImageNoShort(Image i) {
    image = i;
    waitForImage(frame, i);
    width = i.getWidth(frame);
    height = i.getHeight(frame);
  }

  public void loadImageToFrame(){
    waitForImage(frame, getImage());
    width = getImage().getWidth(frame);
    height = getImage().getHeight(frame);
    Rectangle r = frame.getBounds();
    frame.repaint(0,0,r.width,r.height);
  }
  public Image getImage() {
    waitForImage(frame, image);
    return image;
  }
  
  public void setImageComesFromFile(boolean fromFile){
    imageComesFromFile = fromFile;
  }
  public boolean imageFromFile(){return imageComesFromFile;}

  public String getFileName() {
    return fileName;
  }
  private short clip(short i) {
    if (i < min) min=i;
    if (i > max) max=i;
    if (i < 0 ) {
      clipped = true;
      if (i < min) min=i;
      return 0;
    }
    if (i > 255) {
      clipped = true;
      return 255;
    } 
    return i;
  }
  public void clip() {
   for (int x = 0; x < width; x++)
    for (int y = 0; y < height; y++) {
      r[x][y] = clip(r[x][y]); 
      g[x][y] = clip(g[x][y]);
      b[x][y] = clip(b[x][y]);
    }
  }

  /**
     short2Image - turn 3 short arrays, r, g and b into an image.
  */
  public void short2Image() {
   clipped = false;
   width = r.length;
   height = r[0].length;
   Toolkit tk = Toolkit.getDefaultToolkit();
   int pels[] = new int[width*height];
   for (int x = 0; x < width; x++)
    for (int y = 0; y < height; y++) {
       pels[ x + y * width] = 
           0xff000000 
           | (r[x][y] << 16) 
           | (g[x][y] << 8) 
           |  b[x][y];
    }
    Image i =tk.createImage(
          new MemoryImageSource(
            width, 
            height,
            cm, 
            pels, 0, 
            width));
    setImageNoShort(i);
    
    if (clipped) System.out.println(
      "warning: clipped image. min max ="+min+","+max);
  }
  public void pels2Image(int pels[]) {
   width = r.length;
   height = r[0].length;
   Toolkit tk = Toolkit.getDefaultToolkit();
   Image i =tk.createImage(
          new MemoryImageSource(
            width, 
            height,
            cm, 
            pels, 0, 
            width));
    setImageNoShort(i);
//    Rectangle r = getBounds();
//    repaint(0,0,r.width,r.height);
  }
  /**
     image2Short - takes the private Image instance and
     makes 3 short arrays, r, g and b.
  */
  public void image2Short() {
    r = new short[width][height];
    g = new short[width][height];
    b = new short[width][height];   

    int pels[] = new int[width * height];
    cm = ColorModel.getRGBdefault();

    PixelGrabber grabber = 
        new PixelGrabber(
          image, 0, 0, 
          width, height, pels, 0, width);

    try {grabber.grabPixels();}
    catch (InterruptedException e){};
    int i = 0;
    for (int x = 0; x < width; x++)
      for (int y = 0; y < height; y++) {
        i = x + y * width;
        b[x][y] = (short)cm.getBlue(pels[i]);
        g[x][y] = (short)cm.getGreen(pels[i]);
        r[x][y] = (short)cm.getRed(pels[i]);
      }
  }

    
   static void waitForImage(Component component, Image image) {
    MediaTracker tracker = new MediaTracker(component);        
    try {
      tracker.addImage(image, 0);
      tracker.waitForID(0);
      if (!tracker.checkID(0)) 
           System.out.println("Load failure!");
    }
    catch(InterruptedException e) {  }
  }
   
  
  public void setFileName(String _fn) {
    File f = new File(_fn);
    if (f.exists()) {
      fileName = _fn;
      System.out.println(
      "File:"+ fileName +
      "\nis " + f.length() + " bytes long"); 
    }
    setImageComesFromFile(true);
  }
  
   // Takes a packed RGB model and makes
   // the short arrays
   public void int2Short(int pels[]) {
    System.out.println("The width and height are" 
      + width+","+height);
    r = new short[width][height];
    g = new short[width][height];
    b = new short[width][height];   

    cm = ColorModel.getRGBdefault();
    int i = 0;
    for (int x = 0; x < width; x++)
      for (int y = 0; y < height; y++) {
        i = x + y * width;
        b[x][y] = (short)cm.getBlue(pels[i]);
        g[x][y] = (short)cm.getGreen(pels[i]);
        r[x][y] = (short)cm.getRed(pels[i]);
      }
   }
   public void openGif() {
     String fn = FileUtility.getReadFileName(frame);
     if (fn == null) return;
     openGif(fn);
   }
   
   public void openGif(String fn) {
     File f = new File(fn);
     if (!f.exists()) {
       grabNumImage();
       return;
     }
     fileName = fn;
     image = Toolkit.getDefaultToolkit().getImage(fileName);
     setImageResize(image);
     setImageComesFromFile(true);
   }
   
   public void setImageResize(Image i) {
     setImage(i);

     frame.setSize(getWidth(),getHeight());
     frame.setVisible(true);
     frame.repaint();
   }

   public void setImage(Image i) {
     image = i;
     waitForImage(frame, i);
     width = i.getWidth(frame);
     height = i.getHeight(frame);
     image2Short();
   }
   public void setImage(short red[][], short green[][], short blue[][]){
     // show image ...very slow
     // but interesting!
     r = red;
     g = green;
     b = blue;
     image2Short();
   }
   
   public void revert() {
     Toolkit tk = Toolkit.getDefaultToolkit();
     if (imageComesFromFile) {
       if (fileName == null) 
         openGif();
       else {
         image = tk.getImage(fileName);
         setImageResize(image);  
       }
     }
     else grabNumImage();
   } 
   public void revertNoResize() {
     Toolkit tk = Toolkit.getDefaultToolkit();
     if (imageComesFromFile) {
       if (fileName == null) 
         openGif();
       else {
         image = 
           tk.getImage(fileName);
         setImage(image);  
       }
     }
     else grabNumImage();

   } 
  
}
