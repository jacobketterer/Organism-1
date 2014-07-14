import java.util.*;

public class Main {

  final static int LIMIT = 100;
  
  static HashSet<Cell> allCells = new HashSet<Cell>();
  
  public static void main(String[] args) {
    CoordinatorCell c = ringConfig(6);
    for (int i = 0; i < LIMIT; i++) {
      c.excite(20);
      for (Cell x : allCells) {
        x.tick();
      }
    }
  }
  
  /**
   * Arranges neurons into a ring.
   * @param numCells
   * @return CoordinatorCell for configuration
   */
  
  public static CoordinatorCell lineConfig(int numCells) {
    Neuron[] line = new Neuron[numCells];
    for (int i = 0; i < numCells; i++) {
      Neuron n = new Neuron();
      allCells.add(n);
      line[i] = n;
    }
    for (int i = 0; i < numCells - 1; i++) {
      line[i].addChild(line[i + 1]);
    }
    CoordinatorCell c = new CoordinatorCell();
    c.addChild(line[0]);
    return c;
  }
  
  public static CoordinatorCell ringConfig(int numCells) {
    Neuron[] ring =  new Neuron[numCells];
    for (int i = 0; i < ring.length; i++) {
      Neuron n = new Neuron();
      allCells.add(n);
      ring[i] = n;
    }
    ring[ring.length - 1].addChild(ring[0]);
    for (int i = 0; i < ring.length - 1; i++) {
      ring[i].addChild(ring[i + 1]);
    }
    CoordinatorCell c = new CoordinatorCell();
    c.addChild(ring[0]);
    return c;
  }

}
