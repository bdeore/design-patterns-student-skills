package studentskills.mytree;

public class BalancedTree {

  private final int replicaID;
  private StudentRecord root;

  public BalancedTree(int replicaID) {
    root = null;
    this.replicaID = replicaID;
  }

  public int getReplicaID() {
    return replicaID;
  }

  private StudentRecord rotateRight(StudentRecord y) {
    StudentRecord x = y.getLeftNode();
    StudentRecord z = x.getRightNode();
    x.setRightNode(y);
    y.setLeftNode(z);
    updateHeight(y);
    updateHeight(x);
    return x;
  }

  private StudentRecord rotateLeft(StudentRecord y) {
    StudentRecord x = y.getRightNode();
    StudentRecord z = x.getLeftNode();
    x.setLeftNode(y);
    y.setRightNode(z);
    updateHeight(y);
    updateHeight(x);
    return x;
  }

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

  public StudentRecord insert(StudentRecord root, StudentRecord record) {
    if (root == null) {
      if (this.root == null) this.root = record;
      root = record;
      return root;
    } else if (record.getbNumber() < root.getbNumber()) {
      root.setLeftNode(insert(root.getLeftNode(), record));
    } else if (record.getbNumber() > root.getbNumber()) {
      root.setRightNode(insert(root.getRightNode(), record));
    } else {
      System.out.println("duplicate key " + record.getbNumber());
      // throw new RuntimeException("duplicate Key!");
    }
    rebalanceTree(root);
    return this.root;
  }

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

  public void inorder(StudentRecord record) {
    if (record == null) return;
    inorder(record.getLeftNode());
    System.out.println(
        "BNumber: "
            + record.getbNumber()
            + ", "
            + "firstName: "
            + record.getFirstName()
            + ", "
            + "lastName: "
            + record.getLastName()
            + ", "
            + "Major: "
            + record.getMajor());
    inorder(record.getRightNode());
  }

  private void updateHeight(StudentRecord record) {
    record.setHeight(1 + Math.max(height(record.getLeftNode()), height(record.getRightNode())));
  }

  private int height(StudentRecord record) {
    return record == null ? -1 : record.getHeight();
  }

  private int getBalance(StudentRecord record) {
    return (record == null) ? 0 : height(record.getRightNode()) - height(record.getLeftNode());
  }

  public StudentRecord getRoot() {
    return root;
  }

  public void setRoot(StudentRecord root) {
    this.root = root;
  }
}
