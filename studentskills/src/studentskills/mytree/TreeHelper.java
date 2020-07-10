package studentskills.mytree;

import java.util.ArrayList;
import java.util.List;

public class TreeHelper {

  private final List<BalancedTree> replicas;

  public TreeHelper(int numberOfReplicas) {
    int counter = 0;
    BalancedTree tempTree;
    replicas = new ArrayList<>();
    while (numberOfReplicas > 0) {
      tempTree = new BalancedTree(counter);
      replicas.add(tempTree);
      numberOfReplicas--;
      counter++;
    }
  }

  public List<BalancedTree> getReplicas() {
    return replicas;
  }
}
