import java.util.*;


public class Neuron extends Cell {
  
  final static int REST_POTENTIAL = 0;
  final static int REST_FOOD = 500;
  final static int THRESHOLD = 20;
  final static int JUMP = 50;
  
  static int count = 0;
  
  public Neuron() {
    children = new HashSet<Cell>();
    parents = new HashSet<Cell>();
    food = REST_FOOD;
    potential = REST_POTENTIAL;
  }
  
  /**
   * ACTION: Gains 50 potential and loses 50 food, distributes potential to children
   * and loses 100 potential for refractory period
   * THRESHOLD: 20
   */
  
  @Override
  public void excite(int potential) {
    if (dead) {
      return;
    }
    this.potential += potential;
    if (this.potential >= THRESHOLD) {
      System.out.println(++count + " action potential(s) fired.");
      this.potential += JUMP;
      food -= JUMP;
      for (Cell child : children) { 
        child.excite(this.potential / children.size()); 
      }
      potential -= (JUMP * 2);
    }
  }
  
  public void print() {
    System.out.println("Potential: " + potential);
    System.out.println("Food: " + food + "\n");
    for (Cell child : children) { 
      child.print(); 
    }
  }
  
}
