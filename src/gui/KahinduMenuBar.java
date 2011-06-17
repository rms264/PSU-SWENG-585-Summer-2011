package gui;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class KahinduMenuBar {
	private MenuBar menuBar;
	HashMap<MenuEnum,KahinduMenu> menuMap = new HashMap<MenuEnum,KahinduMenu>();
	private KahinduMenuBar(MenuBar mb){
		menuBar = mb;
	}
	
	public static KahinduMenuBar create(MenuBar mb){
	  return new KahinduMenuBar(mb);
	}
	public MenuBar getMenuBar(){
	  return menuBar;
	}
	
	public MenuItem addMenuItem(Menu aMenu, String itemName, ActionListener l) {
    MenuItem item = new MenuItem(itemName);
    aMenu.add(item);
    item.addActionListener(l);
    return(item);
  }
	public MenuItem addMenuItem(Menu aMenu, String itemName) {
    MenuItem item = new MenuItem(itemName);
    aMenu.add(item);
    return(item);
  }
	public void addMenu(KahinduMenu menu) throws Exception{
	  //add to base menubar no parent
	  menuMap.put(menu.getID(), menu);
	  menuBar.add(menu);
	}
	
	public void addMenu(MenuEnum parentID, KahinduMenu m) throws Exception{
	  if(!menuMap.containsKey(parentID)){
	    addMenu(new KahinduMenu(parentID.toString(),parentID));
	  }
	  Menu parentMenu = menuMap.get(parentID);
	  if(menuMap.containsKey(m.getID())){
	    Menu tmp = menuMap.get(m.getID());
	    for(int i = 0; i<m.getItemCount(); i++){
	      for(int j = 0; j<tmp.getItemCount(); j++){
	        if(m.getItem(i) == tmp.getItem(j))
	          break;
	        tmp.add(m.getItem(i));
	      }
	    }
	  }
	  else{
	    parentMenu.add(m);
	    menuMap.put(m.getID(), m);
	  }
	}
	public Menu getMenu(MenuEnum menuID) throws Exception{
	  if(menuMap.containsKey(menuID))
	    return menuMap.get(menuID);
	  else 
	    throw new Exception("Menu Not Found");
	}
}
