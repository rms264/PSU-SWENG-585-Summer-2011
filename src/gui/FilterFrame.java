package gui;import imageUtilities.GrayImage;import java.awt.*;import java.awt.event.*;public class FilterFrame extends  BaseFrame{  private static final long serialVersionUID = 1L;  KahinduMenu filterMenu;   KahinduMenu rgbMenu;  MenuItem gray_mi;    public FilterFrame(KahinduMenuBar menuBar, KahinduImage image) {    super(menuBar, image);    initMenu();  }	  private void initMenu(){        filterMenu = new KahinduMenu("Filter",MenuEnum.FILTER);    rgbMenu = new KahinduMenu("RGBto",MenuEnum.RBG);    try {      menuBar.addMenu(filterMenu);    } catch (Exception e) {      System.out.println(e.getMessage());    }    try {      menuBar.addMenu(MenuEnum.FILTER, rgbMenu);    } catch (Exception e) {      System.out.println(e.getMessage());    }    gray_mi = menuBar.addMenuItem(rgbMenu,"[E-g]ray",this);  }  public void actionPerformed(ActionEvent e) {    if (match(e,gray_mi)) {      new GrayImage(image);      return;    }  }}