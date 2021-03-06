package studentskills.mytree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import studentskills.helper.ErrorLogger;

/** Student Record class stores the record information and serves as node in the tree */
public class StudentRecord implements SubjectI, ObserverI, Cloneable {

  private int bNumber;
  private String firstName;
  private String lastName;
  private double gpa;
  private String major;
  private Set<String> skills;
  private StudentRecord leftNode;

  private StudentRecord rightNode;
  private int height;

  private List<StudentRecord> observers;

  public StudentRecord(
      int bNumber,
      String firstName,
      String lastName,
      double gpa,
      String major,
      HashSet<String> skills) {
    this.bNumber = bNumber;
    this.firstName = firstName;
    this.lastName = lastName;
    this.gpa = gpa;
    this.major = major;
    this.skills = skills;
    this.leftNode = null;
    this.rightNode = null;
    this.height = 0;
    this.observers = new ArrayList<>();
  }

  /**
   * getter for bNumber
   *
   * @return int bNumber
   */
  public int getbNumber() {
    return bNumber;
  }

  /**
   * setter for bNumber
   *
   * @param bNumber key value
   */
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

  public List<StudentRecord> getObservers() {
    return observers;
  }

  /**
   * method to do modifications
   *
   * @param origValue original value
   * @param newValue new value
   */
  public void modify(String origValue, String newValue) {
    if (this.firstName.equals(origValue)) this.firstName = newValue;
    else if (this.lastName.equals(origValue)) this.lastName = newValue;
    else if (this.major.equals(origValue)) this.major = newValue;
    else if (skills.contains(origValue)) {
      skills.remove(origValue);
      skills.add(newValue);
    } else {
      ErrorLogger.getInstance()
          .store("Modification Error -- [" + origValue + "] value does not exist in tree");
    }
  }

  @Override
  public void update(String origValue, String newValue) {
    modify(origValue, newValue);
  }

  /**
   * method to register observers
   *
   * @param node StudentRecord object
   */
  @Override
  public void registerObserver(ObserverI node) {
    observers.add((StudentRecord) node);
  }

  /**
   * method to remove observers
   *
   * @param node StudentRecord object
   */
  @Override
  public void removeObserver(ObserverI node) {
    if (observers.contains(node)) {
      observers.remove(node);
    } else {
      System.out.println("Observer doesn't exist");
    }
  }

  /**
   * method to notify all observers
   *
   * @param origValue original value
   * @param newValue new value
   */
  @Override
  public void notifyObservers(String origValue, String newValue) {
    for (StudentRecord observer : observers) {
      observer.update(origValue, newValue);
    }
  }

  /**
   * method to create clones with deep copy
   *
   * @return StudentRecord Object
   * @throws CloneNotSupportedException exception
   */
  @Override
  protected StudentRecord clone() throws CloneNotSupportedException {
    StudentRecord copy = (StudentRecord) super.clone();
    copy.skills = new HashSet<>();
    copy.observers = new ArrayList<>();
    return copy;
  }

  /**
   * toString method
   *
   * @return String
   */
  @Override
  public String toString() {
    return "StudentRecord{"
        + "bNumber="
        + bNumber
        + ", firstName='"
        + firstName
        + '\''
        + ", lastName='"
        + lastName
        + '\''
        + ", gpa="
        + gpa
        + ", major='"
        + major
        + '\''
        + ", skills="
        + skills
        + ", leftNode="
        + leftNode
        + ", rightNode="
        + rightNode
        + ", height="
        + height
        + ", observers="
        + observers
        + '}';
  }
}
