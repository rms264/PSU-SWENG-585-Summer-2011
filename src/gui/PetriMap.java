package gui;

public class PetriMap {
  private static int place = 1;
  private static String ps;

  public static String get(int c) {
    String s ="[" + (char) c + "]";
    switch (place) {
      case 1: 
        if (isEsc(c)) {
          // t1
          place=2; 
          return null; 
        }
        if (isTab(c)) {
          // t2
          place=4; 
          return null;
        }
        return s;
      case 2:
        if (isTab(c)) {
          // t3
          place = 3; 
          return null;
        }
        if (isEsc(c))
          return null;
        // t4
        place = 1; 
        ps = "[E-"+(char)c+"]";
        return (ps);
      case 3:
        if (isTab(c))
          return null;
        if (isEsc(c)) {
          // t5
          place = 1; 
          return null;
        }
        // t6
        place = 1; 
        ps = "[E-T-"+(char)c+"]";
        return (ps);
      case 4:
        if (isEsc(c)) {
          // t7
          place = 1; 
          return null;
        }
        if (isTab(c)) return null;
        // t8
        place = 1; 
        ps = "[T-"+(char)c+"]";
        return (ps);            
      }
      ps = s;
      return ps;   
    }

   private static boolean isEsc(int c) {
    return ( c == 27);
   }
   private static boolean isTab(int c) {
    return (c == '\t');
   }
}
