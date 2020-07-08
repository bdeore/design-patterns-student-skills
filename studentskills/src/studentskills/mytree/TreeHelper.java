package studentskills.mytree;

public class TreeHelper {
  private StudentRecord root;

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

  public StudentRecord insert(StudentRecord record) {
    if (root == null) {
      root = record;
      return root;
    } else if (record.getbNumber() > root.getbNumber()) {
      root.setLeftNode(insert(record.getLeftNode()));
    } else if (record.getbNumber() < root.getbNumber()) {
      root.setRightNode(insert(record.getRightNode()));
    } else {
      System.out.println("duplicate key");
      // throw new RuntimeException("duplicate Key!");
    }
    rebalanceTree(record);
    return this.root;
  }

  public StudentRecord find(int bNumber) {

    StudentRecord current = this.root;

    System.out.println("Visiting elements: ");

    while (current != null && current.getbNumber() != bNumber) {
      System.out.print(current.getbNumber() + " ");
      if (current.getbNumber() > bNumber) {
        current = current.getLeftNode();
      } else if (current.getbNumber() < bNumber) {
        current = current.getRightNode();
      }
    }
    return current;
  }

  private StudentRecord mostLeftChild(StudentRecord record) {
    StudentRecord current = record;
    /* loop down to find the leftmost leaf */
    while (current.getLeftNode() != null) {
      current = current.getLeftNode();
    }
    return current;
  }

  public void inorder(StudentRecord record) {
    if (record == null) return;
    inorder(record.getLeftNode());
    System.out.println(record.getbNumber() + " ");
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
}
