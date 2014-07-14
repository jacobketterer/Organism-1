import java.util.*;

/*
 * Connecting - To add one cell to another cell's children/parents, and
 *              add the second cell to the first cell's parents/children.
 * Firing     - To check for a threshold potential, perform some action,
 *              and subtract that from the Cell's potential.
 */

/**
 * A cell is the smallest unit within an organism. It is capable of
 * being fed and being excited. It also has a tick method to behave
 * differently depending on its state. This includes apoptosis, which
 * removes a cell from the organism whenever the cell "dies." 
 * @author Tanner
 *
 */

public abstract class Cell {

  protected int potential, food;
  protected HashSet<Cell> children, parents;
  protected boolean dead;
  

  /**
   * Connects a new child to the Cell
   * @param child
   */
  
  public void addChild(Cell child) {
    children.add(child);  
    child.parents.add(this);
  }
  
  /**
   * Connects a new parent to the Cell
   * @param parent
   */
  
  public void addParent(Cell parent) {
    parents.add(parent);
    parent.children.add(this);
  }
  
  /**
   * Removes a Cell from the organism by disconnecting it from its children and parents
   */
  
  protected final void apoptose() {
    dead = true;
    for (Cell child : children) {
      child.parents.remove(this);
    }
    for (Cell parent : parents) {
      parent.children.remove(this);
    }
    System.out.println("Apoptosed");
  }
  
 /**
  * Adds food to this Cell's food
  * @param food
  */
  
  public void feed(int food) {
    this.food += food;
  }
  
  /**
   * Adds potential to this Cell's potential
   * @param potential
   */
  
  public void excite(int potential) {
    this.potential += potential;
  }
  
  /**
   * Only does stuff if the Cell is not dead. tick() causes the Cell 
   * to behave differently depending on its state. Cells will 
   * apoptose() if their food reaches/goes below 0. Each tick also 
   * costs food and causes the potential of the cell to approach 0.
   */
  
  public void tick() {
    if (dead) {
      return;
    }
    food -= 10;
    potential /= 2;
    if (food <= 0) { 
      apoptose(); 
    }
  }
  
  public int getFood() {
    return food;
  }
  
  public boolean isDead() {
    return dead;
  }
  
  public int hashCode() {
    int hash = 5 * food * potential;
    return hash;
  }
  
  /**
   * Prints out information about the Cell
   */
  
  public void print() {
    for (Cell child : children) { 
      child.print(); 
    }
  }
  
}
