package studentskills.mytree;

import java.util.HashSet;
import java.util.Set;

public class StudentRecord {

  private int bNumber;
  private String firstName;
  private String lastName;
  private double gpa;
  private String major;
  private Set<String> skills;
  private StudentRecord leftNode;
  private StudentRecord rightNode;
  private int height;

  public StudentRecord(int bNumber, String firstName, String lastName, double gpa, String major) {
    this.bNumber = bNumber;
    this.firstName = firstName;
    this.lastName = lastName;
    this.gpa = gpa;
    this.major = major;
    this.skills = new HashSet<>();
    this.leftNode = null;
    this.rightNode = null;
    this.height = 0;
  }

  public int getbNumber() {
    return bNumber;
  }

  public void setbNumber(int bNumber) {
    this.bNumber = bNumber;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public double getGpa() {
    return gpa;
  }

  public String getMajor() {
    return major;
  }

  public Set<String> getSkills() {
    return skills;
  }

  public StudentRecord getLeftNode() {
    return leftNode;
  }

  public void setLeftNode(StudentRecord leftNode) {
    this.leftNode = leftNode;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public StudentRecord getRightNode() {
    return rightNode;
  }

  public void setRightNode(StudentRecord rightNode) {
    this.rightNode = rightNode;
  }
}
