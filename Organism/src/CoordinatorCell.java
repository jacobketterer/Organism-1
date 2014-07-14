import java.util.HashSet;


public class CoordinatorCell extends Cell {

  public CoordinatorCell() {
    parents = new HashSet<Cell>();
    children = new HashSet<Cell>();
    food = 1;
    potential = 0;
  }
  
  /**
   * ACTION: Distributes potential to children
   * THRESHOLD: 0
   * USES:      ALL
   */
  
  @Override
  public void excite(int potential) {
    this.potential += potential;
    for (Cell child : children) { 
      child.excite(this.potential / children.size()); 
    }
  }

  /**
   * CANNOT DIE
   */
  
  @Override
  public void tick() { 
    return;
  }
  
}
