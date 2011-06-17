package imageUtilities;

public class TestPattern {
  public TestPattern(){
    execute();
  }
  public void execute() {
    SnellWlx sw = new SnellWlx();
    sw.init();
    sw.start();
    sw.setSize(256,256);
  }

}
