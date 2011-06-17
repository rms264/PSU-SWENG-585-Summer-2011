package imageUtilities;

import gui.KahinduImage;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;

public class Grab extends Component{
  
  private static final long serialVersionUID = 1L;
  SnellWlx sw;
  KahinduImage image;
  public Grab(KahinduImage image){
    this.image = image;
    grabTestPattern();
  }
  public void grab(Container f) {
    int width = f.getSize().width;
    int height = f.getSize().height;
    Image backBuffer = createImage(width, height);
    Graphics g = backBuffer.getGraphics();
    f.paint(g);
    image.setImageResize(backBuffer);
    g = getGraphics();
    g.clearRect(0,0,getSize().width,getSize().height);
    repaint();
  }
  public void grabTestPattern() {
    sw = new SnellWlx();
    sw.init();
    sw.start();
    sw.setSize(256,256);
    grab(sw);
}

}
