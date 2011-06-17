package gui;

import java.awt.Menu;

public class KahinduMenu extends Menu {
  private static final long serialVersionUID = 1L;
  MenuEnum id;
  public KahinduMenu(String label,MenuEnum type){
    super(label);
    id = type;
  }
  public Menu getMenu(){return this;}
  public MenuEnum getID(){return id;}

}
