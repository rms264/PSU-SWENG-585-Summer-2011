package gui;

import java.awt.Frame;
import java.awt.MenuBar;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class KahinduMainFrame extends Frame implements WindowListener{
  private static final long serialVersionUID = 1L;
  KahinduMenuBar menuBar;
  KahinduImage image;
  
  public KahinduMainFrame(String name){
    super(name);
    menuBar = KahinduMenuBar.create(new MenuBar());
    image = new KahinduImage(this);
    setMenuBar(menuBar.getMenuBar());
    addFrames();
  }

  private void addFrames(){
    add(new ShortCutFrame(menuBar,image));
  }
  @Override
  public void windowActivated(WindowEvent arg0) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void windowClosed(WindowEvent arg0) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void windowClosing(WindowEvent arg0) {
    dispose();
  }

  @Override
  public void windowDeactivated(WindowEvent arg0) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void windowDeiconified(WindowEvent arg0) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void windowIconified(WindowEvent arg0) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void windowOpened(WindowEvent arg0) {
    // TODO Auto-generated method stub
    
  }
  
}
