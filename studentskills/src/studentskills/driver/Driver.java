package studentskills.driver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import studentskills.mytree.BalancedTree;
import studentskills.mytree.ReplicaID;
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

      String line = fp.poll();
      while (line != null) {
        System.out.println(line);
        line = fp.poll();
      }

      StudentRecord s1 = new StudentRecord(10, "John", "Doe", 3.0, "CS");
      StudentRecord s2 = new StudentRecord(20, "John", "Doe", 3.0, "CS");
      StudentRecord s3 = new StudentRecord(30, "John", "Doe", 3.0, "CS");
      StudentRecord s4 = new StudentRecord(40, "John", "Doe", 3.0, "CS");
      StudentRecord s5 = new StudentRecord(50, "John", "Doe", 3.0, "CS");
      StudentRecord s6 = new StudentRecord(25, "John", "Doe", 3.0, "CS");

      TreeHelper replicas = new TreeHelper(3);

      BalancedTree records = replicas.getReplicas().get(1);

      records.setRoot(records.insert(records.getRoot(), s1));
      records.setRoot(records.insert(records.getRoot(), s2));
      records.setRoot(records.insert(records.getRoot(), s3));
      records.setRoot(records.insert(records.getRoot(), s4));
      records.setRoot(records.insert(records.getRoot(), s5));
      records.setRoot(records.insert(records.getRoot(), s6));

      records.inorder(records.getRoot());
      System.out.println(records.find(25).getbNumber());
      System.out.println(records.getRoot().getbNumber());

      System.out.println(ReplicaID.getID(records));

      BalancedTree records0 = replicas.getReplicas().get(0);
      records0.inorder(records0.getRoot());

    } catch (InvalidPathException
        | FileNotFoundException
        | SecurityException // | EmptyInputFileException
        | ArithmeticException e) {
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
