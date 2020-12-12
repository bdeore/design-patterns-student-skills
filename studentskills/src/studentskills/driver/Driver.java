package studentskills.driver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import studentskills._exceptions.EmptyInputFileException;
import studentskills.helper.ErrorLogger;
import studentskills.helper.LineParser;
import studentskills.mytree.BalancedTree;
import studentskills.mytree.TreeHelper;
import studentskills.util.FileProcessor;
import studentskills.util.Results;

/** @author Bhagwan Deore */
public class Driver {
  private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 7;

  public static void main(String[] args) {
    /*
     * As the build.xml specifies multiple arguments other than input and output, in case the
     * argument value is not given java takes the default value specified in
     * build.xml. To avoid that, below condition is used
     */
    if ((args.length != 7)
        || (args[0].equals("${input}"))
        || (args[1].equals("${modifications}"))
        || (args[2].equals("${out1}"))
        || (args[3].equals("${out2}"))
        || (args[4].equals("${out3}"))
        || (args[5].equals("${error}"))
        || (args[6].equals("${debug}"))) {
      System.err.printf(
          "Error: Incorrect number of arguments. Program accepts %d arguments (minimum).",
          REQUIRED_NUMBER_OF_CMDLINE_ARGS);
      System.exit(0);
    }

    try {

      /* Separate File Processor instances are used for input file and modifications file.
       * file names are read in directly from the command line arguments. */
      FileProcessor inputFP = new FileProcessor(args[0]);
      FileProcessor modificationsFP = new FileProcessor(args[1]);

      /* TreeHelper class is used to manage and create replicas and provides consistent API for
       * insert and update operations. constructor of this class takes Number Of Replicas as the
       * parameter and also stores references to all the replicas in an ArrayList. */
      TreeHelper replicaManager = new TreeHelper(3);

      /* Separate LineParser instances are used to parse input file and modifications file.
       * instance of TreeHelper is passed in so that line parser can directly call tree methods */
      LineParser ilp = new LineParser(inputFP, replicaManager);
      ilp.processFile();

      LineParser mlp = new LineParser(modificationsFP, replicaManager);
      mlp.processFile();

      /* TreeHelper class provides getReplica(int replicaID) and printNodes(StudentRecord root, Results rs)
       * methods. getReplica method returns replica for given replicaID. printNodes method prints
       * bNumber and list of skills in ascending order  */
      BalancedTree replica0 = replicaManager.getReplica(0);
      Results rs = new Results();
      replica0.printNodes(replica0.getRoot(), rs);
      rs.write(args[2]);

      BalancedTree replica1 = replicaManager.getReplica(1);
      rs = new Results();
      replica1.printNodes(replica1.getRoot(), rs);
      rs.write(args[3]);

      BalancedTree replica2 = replicaManager.getReplica(2);
      rs = new Results();
      replica2.printNodes(replica2.getRoot(), rs);
      rs.write(args[4]);

      /* ErrorLogger is a Singleton class which maintains a buffer for error messages */
      ErrorLogger.getInstance().write(args[5]);

    } catch (InvalidPathException
        | FileNotFoundException
        | SecurityException
        | ArithmeticException
        | EmptyInputFileException e) {
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

  @Override
  public String toString() {
    return "Driver Class";
  }
}
