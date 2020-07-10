package studentskills.driver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import studentskills.helper.LineParser;
import studentskills.mytree.BalancedTree;
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

    try {
      FileProcessor inputFP = new FileProcessor(args[0]);
      FileProcessor modificationsFP = new FileProcessor(args[1]);

      TreeHelper replicaManager = new TreeHelper(3);
      LineParser ilp = new LineParser(inputFP, replicaManager);
      ilp.processFile();
      LineParser mlp = new LineParser(modificationsFP, replicaManager);
      mlp.processFile();

      BalancedTree replica0 = replicaManager.getReplica(0);
      replica0.inorder(replica0.getRoot());
      System.out.println();

      BalancedTree replica1 = replicaManager.getReplica(1);
      replica1.inorder(replica1.getRoot());
      System.out.println();

      BalancedTree replica2 = replicaManager.getReplica(2);
      replica2.inorder(replica2.getRoot());

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
