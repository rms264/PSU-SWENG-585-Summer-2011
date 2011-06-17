package gui;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Frame;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public abstract class BaseFrame extends Frame implements WindowListener,
  KeyListener, ActionListener
{
  private int place = 1;
  private String s = null;
  private String ps = null;
  protected final KahinduMenuBar menuBar;
  protected final KahinduImage image;
  
  public  BaseFrame(KahinduMenuBar menuBar, KahinduImage image) {
    this.menuBar = menuBar;
    this.image = image;
    setBackground(Color.white);
    addWindowListener(this);
    addKeyListener(this);
  }
  
  public boolean match(AWTEvent e, MenuItem mi) {
    if (e.getSource() == mi) return true;

    if (e.getSource() instanceof MenuItem) return false;
    if (ps == null) return false;
    if ( mi.getLabel().startsWith(ps)) return true;
    return false;
  }
  
  public void keyPressed(KeyEvent e) {
    int charValue = e.getKeyChar();
    ps = PetriMap.get(charValue);
    actionPerformed(new ActionEvent(e,
      ActionEvent.ACTION_PERFORMED,charValue+""));
   }
   public void keyReleased (KeyEvent e) {
   }
  
   public String getPS() {
     return ps;
   }
   public void keyTyped(KeyEvent arg0) {
     // TODO Auto-generated method stub
     
   }

   public void actionPerformed(ActionEvent arg0) {
     // TODO Auto-generated method stub
     
   }
  public void windowClosing(WindowEvent e) {
    dispose();
  }
  public void windowClosed(WindowEvent e) {};
  public void windowDeiconified(WindowEvent e) {};
  public void windowIconified(WindowEvent e) {};
  public void windowActivated(WindowEvent e) {};
  public void windowDeactivated(WindowEvent e) {};
  public void windowOpened(WindowEvent e) {};
}

