package gui;

public enum MenuEnum {
  FILE,OPEN,FILTER,RBG,GRAB,NET,NEGATE,HISTOGRAM;
  
  public String toString(){
    String label = "File";
    switch(this){
    case FILE:
      label = "File";
      break;
    case OPEN:
      label = "Open";
      break;
    case FILTER:
      label = "Filter";
      break;
    case RBG:
      label = "RGBto";
      break;
    case GRAB:
      label = "Grab";
      break;
    case NET:
      label = "net";
      break;
    case NEGATE:
      label = "Negate";
      break;
    case HISTOGRAM:
      label = "Histogram";
      break;
    }
    
    return label;
  }

}
