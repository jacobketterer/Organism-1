import java.util.*;

// Do SupportCells require excitation?

/**
 * A SupportCell is a Cell that is responsible for transporting food
 * and feeding other Cells.
 * @author hta
 *
 */

public class SupportCell extends Cell {

  final static int REST_FOOD = 1000;
  final static int REST_POTENTIAL = 0;
  final static int THRESHOLD = 10;
  final static int DISTRIBUTION = 100;
  
  public SupportCell() {
    children = new HashSet<Cell>();
    parents = new HashSet<Cell>();
    food = REST_FOOD;
    potential = REST_POTENTIAL;
  }
  
  /**
   * ACTION: Distributes DISTRIBBUTION food evenly to all children
   * THRESHOLD: 10
   * USES:      10
   */
  
  public void excite(int potential) {
    if (dead) {
      return;
    }
    this.potential += potential;
    if (potential >= THRESHOLD) {
      this.potential -= THRESHOLD;
      this.food -= DISTRIBUTION;
      for (Cell child : children) { 
        child.feed(DISTRIBUTION / children.size()); 
      }
    }
  }
  
}
