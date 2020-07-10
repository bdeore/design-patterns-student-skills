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

  public void insert(StudentRecord record) throws CloneNotSupportedException {
    ArrayList<StudentRecord> objectReferences = new ArrayList<>();
    StudentRecord tempRecord = record;

    for (BalancedTree tree : replicas) {
      objectReferences.add(tempRecord);
      tree.setRoot(tree.insert(tree.getRoot(), tempRecord));
      tempRecord = record.clone();
    }

    for (StudentRecord node : objectReferences) {
      for (StudentRecord observer : objectReferences) {
        if (node != observer) node.registerObserver(observer);
      }
    }
  }

  public void updateNode(int replicaID, int bNumber, String origValue, String newValue) {
    if ((replicaID + 1) > replicas.size()) {
      System.out.println("replica doesn't exist");
    } else {
      StudentRecord node = replicas.get(replicaID).find(bNumber);
      if (node != null) {
        node.modify(origValue, newValue);
        node.notifyObservers(origValue, newValue);
      } else System.out.println("updateNode : no such record exists");
    }
  }

  public List<BalancedTree> getReplicas() {
    return replicas;
  }
}
