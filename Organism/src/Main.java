import java.util.*;

public class Main {

  final static int NUMBER_OF_TICKS = 100;
  
  static HashSet<Cell> allCells = new HashSet<Cell>();
  
  public static void main(String[] args) {
    runTrial(supportedRingConfig(5), NUMBER_OF_TICKS);
  }
  
  /**
   * Runs a simulation for the organism
   * @param CoordinatorCell c
   * @param int numTicks
   */
  
  public static void runTrial(CoordinatorCell c, int numTicks) {
    for (int i = 0; i < numTicks; i++) {
      c.excite(10);
      for (Cell x : allCells) {
        if (x.isDead()) {
          break;
        }
        x.tick();
      }
    }
  }
  
  /**
   * Arranges neurons into a line with an analyzer at the end
   * @param num neurons in the line
   * @return CoordinatorCell for configuration
   */
  
  public static CoordinatorCell lineConfig(int numNeurons) {
    Neuron[] line = new Neuron[numNeurons];
    for (int i = 0; i < numNeurons; i++) {
      Neuron n = new Neuron();
      allCells.add(n);
      line[i] = n;
    }
    for (int i = 0; i < numNeurons - 1; i++) {
      line[i].addChild(line[i + 1]);
    }
    CoordinatorCell c = new CoordinatorCell();
    c.addChild(line[0]);
    return c;
  }

  /**
   * Arranges neurons into a ring.
   * @param number of neurons in the ring (num > 0)
   * @return CoordinatorCell for configuration
   */
  
  public static CoordinatorCell ringConfig(int num) {
    assert(num > 0);
    Neuron[] ring =  new Neuron[num];
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
  
  /**
   * Ring of neurons that are all children of an unsustained SupportCell.
   * SupportCell within ring, child to one of neurons
   * @param number of neurons in ring (num > 0) 
   * @return CoordinatorCell for configuration
   */
  
  public static CoordinatorCell supportedRingConfig(int num) {
    assert (num > 0);
    Neuron[] ring = new Neuron[num];
    SupportCell s = new SupportCell();
    allCells.add(s);
    for (int i = 0; i < ring.length; i++) {
      Neuron n = new Neuron();
      allCells.add(n);
      s.addChild(n);
      ring[i] = n;
    }
    s.addParent(ring[0]);
    ring[ring.length - 1].addChild(ring[0]);
    for (int i = 0; i < ring.length - 1; i++) {
      ring[i].addChild(ring[i + 1]);
    }
    CoordinatorCell c = new CoordinatorCell();
    c.addChild(ring[0]);
    return c; 
  }

}
