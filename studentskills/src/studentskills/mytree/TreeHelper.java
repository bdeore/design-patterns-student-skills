package studentskills.mytree;

import java.util.ArrayList;
import java.util.List;

/* TreeHelper class is used to manage and create replicas and provides consistent API for
 * insert and update operations. constructor of this class takes Number Of Replicas as the
 * parameter and also stores references to all the replicas in an ArrayList. */

public class TreeHelper {

  private final List<BalancedTree> replicas;

  /**
   * constructor for TreeHelper class
   *
   * @param numberOfReplicas count of replicas you want to maintain, no hard-coded upper limit.
   */
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

  /**
   * Method to insert to nodes in all tree replicas and to register all observers. it uses Insert
   * method of individual balanced tree for insertion
   *
   * @param record record to be inserted
   * @throws CloneNotSupportedException exception in case record cannot be cloned
   */
  public void insert(StudentRecord record) throws CloneNotSupportedException {
    ArrayList<StudentRecord> objectReferences = new ArrayList<>();
    StudentRecord tempRecord = record;

    for (BalancedTree tree : replicas) {
      for (String skill : record.getSkills()) tempRecord.getSkills().add(skill);
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

  /**
   * Method to update the nodes as per requirements in modifications file. it uses Modify method of
   * individual balanced tree for the update.
   *
   * @param replicaID ID of the replica in which record needs to be updated
   * @param bNumber key for the record
   * @param origValue original value
   * @param newValue new value
   */
  public void updateNode(int replicaID, int bNumber, String origValue, String newValue) {
    if ((replicaID + 1) > replicas.size()) {
      System.out.println("replica doesn't exist");
    } else {
      StudentRecord node = replicas.get(replicaID).find(bNumber);
      if (node != null) {
        node.update(origValue, newValue);
        node.notifyObservers(origValue, newValue);
      } else System.out.println("updateNode : no such record exists");
    }
  }

  /**
   * Method to return the list of all the replicas currently being maintained
   *
   * @return list of references to BalancedTree objects
   */
  public List<BalancedTree> getAllReplicas() {
    return replicas;
  }

  /**
   * Method to return individual replica reference based on replicaID
   *
   * @param replicaID id of the replica to be returned
   * @return reference to root node of requested replica
   */
  public BalancedTree getReplica(int replicaID) {
    if ((replicaID > replicas.size() - 1) || replicaID < 0) return null; // throw exception
    else return replicas.get(replicaID);
  }

  /**
   * toString method for debugging
   *
   * @return String
   */
  @Override
  public String toString() {
    return "TreeHelper{" + "replicas=" + replicas + '}';
  }
}
