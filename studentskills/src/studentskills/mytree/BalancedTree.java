package studentskills.mytree;

import studentskills.util.Results;

/* class for balanced tree(AVL). provides public methods -- to getRoot, printNodes, insert etc */
/** References: BST: CLRS page 286. AVL tree: https://www.baeldung.com/java-avl-trees */
public class BalancedTree {

  private final int replicaID;
  private StudentRecord root;

  /**
   * constructor for balanced tree takes replicaID as a parameter and stores it as a final int
   *
   * @param replicaID id number of the replica
   */
  public BalancedTree(int replicaID) {
    root = null;
    this.replicaID = replicaID;
  }

  /**
   * method to return replica ID
   *
   * @return int value replica id
   */
  public int getReplicaID() {
    return replicaID;
  }

  /**
   * method to do right rotation
   *
   * @param y StudentRecord object as root of rotation
   * @return StudentRecord object
   */
  private StudentRecord rotateRight(StudentRecord y) {
    StudentRecord x = y.getLeftNode();
    StudentRecord z = x.getRightNode();
    x.setRightNode(y);
    y.setLeftNode(z);
    updateHeight(y);
    updateHeight(x);
    return x;
  }

  /**
   * method to do left rotation
   *
   * @param y StudentRecord object as root of rotation
   * @return StudentRecord object
   */
  private StudentRecord rotateLeft(StudentRecord y) {
    StudentRecord x = y.getRightNode();
    StudentRecord z = x.getLeftNode();
    x.setLeftNode(y);
    y.setRightNode(z);
    updateHeight(y);
    updateHeight(x);
    return x;
  }

  /**
   * code to rebalance tree treating z as root node for the rotation
   *
   * @param z StudentRecord object
   */
  private void rebalanceTree(StudentRecord z) {
    updateHeight(z);
    int balance = getBalance(z);
    if (balance > 1) {
      if (height(z.getRightNode().getRightNode()) > height(z.getRightNode().getLeftNode())) {
        z = rotateLeft(z);
      } else {
        z.setRightNode(rotateRight(z.getRightNode()));
        z = rotateLeft(z);
      }
    } else if (balance < -1) {
      if (height(z.getLeftNode().getLeftNode()) > height(z.getLeftNode().getRightNode()))
        z = rotateRight(z);
      else {
        z.setLeftNode(rotateLeft(z.getLeftNode()));
        z = rotateRight(z);
      }
    }
    this.root = z;
  }

  /**
   * Method to insert node into the tree
   *
   * @param root current root of the tree
   * @param record new record to be inserted in the tree
   * @return new root node
   */
  public StudentRecord insert(StudentRecord root, StudentRecord record) {
    if (root == null) {
      if (this.root == null) this.root = record;
      root = record;
      return root;
    } else if (record.getbNumber() < root.getbNumber()) {
      root.setLeftNode(insert(root.getLeftNode(), record));
    } else if (record.getbNumber() > root.getbNumber()) {
      root.setRightNode(insert(root.getRightNode(), record));
    } else if (record.getbNumber() == root.getbNumber()) {
      record.setLeftNode(root.getLeftNode());
      record.setRightNode(root.getRightNode());
      root = record;
    }
    rebalanceTree(root);
    return this.root;
  }

  /**
   * Utility method to find node in the tree for given key int bNumber
   *
   * @param bNumber key for the node
   * @return StudentRecord object
   */
  public StudentRecord find(int bNumber) {

    StudentRecord current = this.root;

    while (current != null && current.getbNumber() != bNumber) {
      // System.out.print(current.getbNumber() + " ");
      if (current.getbNumber() > bNumber) {
        current = current.getLeftNode();
      } else if (current.getbNumber() < bNumber) {
        current = current.getRightNode();
      }
    }
    return current;
  }

  /**
   * Utility method to print nodes
   *
   * @param record root node of the tree to be printed
   * @param results instance of results to store results
   */
  public void printNodes(StudentRecord record, Results results) {
    if (record == null) return;
    printNodes(record.getLeftNode(), results);
    boolean first = true;
    String output = "bNumber: " + record.getbNumber() + " Skills: [ ";
    for (String skill : record.getSkills()) {
      if (!first) output += ", ";
      output += skill;
      first = false;
    }
    output += " ]";
    results.store(output);
    printNodes(record.getRightNode(), results);
  }
  //
  //  public void inorder(StudentRecord record) {
  //    if (record == null) return;
  //    inorder(record.getLeftNode());
  //    System.out.print(
  //        "BNumber: "
  //            + record.getbNumber()
  //            + ", "
  //            + "firstName: "
  //            + record.getFirstName()
  //            + ", "
  //            + "lastName: "
  //            + record.getLastName()
  //            + ", "
  //            + "GPA: "
  //            + record.getGpa()
  //            + ", "
  //            + "Major: "
  //            + record.getMajor()
  //            + " Skills: ");
  //    for (String skill : record.getSkills()) System.out.print(" " + skill);
  //    System.out.println();
  //    inorder(record.getRightNode());
  //  }

  /**
   * method to update height of tree
   *
   * @param record StudentRecord object
   */
  private void updateHeight(StudentRecord record) {
    record.setHeight(1 + Math.max(height(record.getLeftNode()), height(record.getRightNode())));
  }

  /**
   * method to get height
   *
   * @param record StudentRecord
   * @return int height
   */
  private int height(StudentRecord record) {
    return record == null ? -1 : record.getHeight();
  }

  /**
   * method to get balance
   *
   * @param record StudentRecord object
   * @return int difference
   */
  private int getBalance(StudentRecord record) {
    return (record == null) ? 0 : height(record.getRightNode()) - height(record.getLeftNode());
  }

  /**
   * method to return root node of tree
   *
   * @return StudentRecord object
   */
  public StudentRecord getRoot() {
    return root;
  }

  /**
   * method set root node of the tree
   *
   * @param root StudentRecord object
   */
  public void setRoot(StudentRecord root) {
    this.root = root;
  }

  @Override
  public String toString() {
    return "BalancedTree{" + "replicaID=" + replicaID + ", root=" + root + '}';
  }
}
