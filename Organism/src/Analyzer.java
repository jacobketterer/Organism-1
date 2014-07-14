import java.util.*;

/**
 * An Analyzer is a Cell whose only function is to report to the console
 * whenever it's sufficiently excited.
 * @author hta
 *
 */

public class Analyzer extends Cell {

  final static int REST_POTENTIAL = 0;
  final static int THRESHOLD = 50;
  
  public Analyzer() {  
    this.parents = new HashSet<Cell>();
    this.children = new HashSet<Cell>();
    this.potential = REST_POTENTIAL;
  }

  /**
   * Fires messages to the console
   * THRESHOLD: 50
   */
  
  @Override
  public void tick() {
    if (potential >= THRESHOLD) {
      System.out.println(potential + " V");
      potential -= THRESHOLD;
    }
  }
  
}
