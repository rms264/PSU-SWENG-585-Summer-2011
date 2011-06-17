package gui;

import java.awt.FileDialog;
import java.awt.Frame;

public class FileUtility {
  public static String getReadFileName(Frame frame) {
    FileDialog fd = new 
      FileDialog(frame, "select a file");
    fd.setVisible(true);
    String file_name = fd.getFile();
    if (fd.getFile() == null) return null;
    String path_name = fd.getDirectory();
    String file_string = path_name + file_name;
    System.out.println("Opening file: "+file_string);
    fd.dispose();
    return file_string;
  }
}
