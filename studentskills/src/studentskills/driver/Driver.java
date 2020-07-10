package studentskills.driver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import studentskills.mytree.BalancedTree;
import studentskills.mytree.StudentRecord;
import studentskills.mytree.TreeHelper;
import studentskills.util.FileProcessor;

public class Driver {
  private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 7;

  public static void main(String[] args) {
    /*
     * As the build.xml specifies the arguments as input,output or metrics, in case the
     * argument value is not given java takes the default value specified in
     * build.xml. To avoid that, below condition is used
     */
    if ((args.length != 7) || (args[0].equals("${input}")) || (args[1].equals("${output}"))) {
      System.err.printf(
          "Error: Incorrect number of arguments. Program accepts %d arguments (minimum).",
          REQUIRED_NUMBER_OF_CMDLINE_ARGS);
      System.exit(0);
    }

    System.out.println("Hello World");
    try {
      FileProcessor fp = new FileProcessor(args[0]);

      StudentRecord s1 = new StudentRecord(10, "John", "Doe", 3.0, "CS");
      StudentRecord s2 = new StudentRecord(20, "jane", "Doe", 3.0, "CS");
      StudentRecord s3 = new StudentRecord(30, "test", "Doe", 3.0, "CS");
      StudentRecord s4 = new StudentRecord(40, "jr", "Doe", 3.0, "CS");
      StudentRecord s5 = new StudentRecord(50, "Jn", "Doe", 3.0, "CS");
      StudentRecord s6 = new StudentRecord(25, "ohn", "Doe", 3.0, "CS");

      TreeHelper replicaManager = new TreeHelper(3);

      replicaManager.insert(s1);
      replicaManager.insert(s2);
      replicaManager.insert(s3);
      replicaManager.insert(s4);
      replicaManager.insert(s5);
      replicaManager.insert(s6);

      replicaManager.updateNode(2, 10, "CS", "ECON");

      BalancedTree records = replicaManager.getReplicas().get(0);
      records.inorder(records.getRoot());
      System.out.println();

      BalancedTree records0 = replicaManager.getReplicas().get(1);
      records0.inorder(records0.getRoot());
      System.out.println();

      BalancedTree records1 = replicaManager.getReplicas().get(2);
      records1.inorder(records1.getRoot());

    } catch (InvalidPathException
        | FileNotFoundException
        | SecurityException // | EmptyInputFileException
        | ArithmeticException
        | CloneNotSupportedException e) {
      System.out.println(e);
      System.out.println("(Class Driver) Terminating Program");
      System.exit(1);
      // e.printStackTrace();
    } catch (IOException e) {
      System.out.println("IOException occurred in FileProcessor class\n" + e);
      System.exit(1);
      // e.printStackTrace();
    }
  }
}
