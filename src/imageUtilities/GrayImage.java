package imageUtilities;

import gui.KahinduImage;

public class GrayImage {
  private final KahinduImage image;
  public GrayImage(KahinduImage image){
    this.image = image;
    execute();
  }
  
  public void execute() {
    for (int x=0; x < image.getWidth(); x++) 
   for (int y=0; y < image.getHeight(); y++) {
     image.getRed()[x][y] = (short)
        ((image.getRed()[x][y] + image.getGreen()[x][y]  + 
            image.getBlue()[x][y]) / 3);
         image.getGreen()[x][y] = image.getRed()[x][y];
         image.getBlue()[x][y] = image.getRed()[x][y];
   }
   image.short2Image();
 }
}
