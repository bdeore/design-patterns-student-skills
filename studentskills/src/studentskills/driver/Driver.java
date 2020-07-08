package studentskills.driver;

import studentskills.mytree.StudentRecord;
import studentskills.mytree.TreeHelper;

public class Driver {
  public static void main(String[] args) {
    System.out.println("Hello World");

    StudentRecord root = null;

    StudentRecord s1 = new StudentRecord(123);
    StudentRecord s2 = new StudentRecord(234);
    StudentRecord s3 = new StudentRecord(235);
    StudentRecord s4 = new StudentRecord(12);
    StudentRecord s5 = new StudentRecord(789);
    StudentRecord s6 = new StudentRecord(123);

    TreeHelper records = new TreeHelper();
    records.insert(s1);
    records.insert(s2);
    records.insert(s3);
    records.insert(s4);
    records.insert(s5);
    records.insert(s6);

    root = records.insert(root);

    records.inorder(root);
    System.out.println(records.find(792));
  }
}
