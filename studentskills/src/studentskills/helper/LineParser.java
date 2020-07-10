package studentskills.helper;

import java.io.IOException;
import java.util.HashSet;
import studentskills._exceptions.EmptyInputFileException;
import studentskills._exceptions.InvalidWordException;
import studentskills.mytree.StudentRecord;
import studentskills.mytree.TreeHelper;
import studentskills.util.FileProcessor;

public class LineParser {

  private final FileProcessor fp;
  private final TreeHelper replicaManager;

  public LineParser(FileProcessor fp, TreeHelper replicaManager) {
    this.fp = fp;
    this.replicaManager = replicaManager;
  }

  /**
   * processFile method uses file processor object to read in the line. validates format of the line
   * using validateLineFormat() method. switch control statement is used to pass on the control to
   * separate handlers for each operation rather than creating one giant monolithic function.
   */
  public void processFile() {
    int count = 0;
    try {
      String line = fp.poll();
      String delimiters = "[:,]+";
      while (line != null) {
        String[] tokens = line.split(delimiters);
        count++;
        // validateLineFormat(tokens, count);
        int tokenZero = Integer.parseInt(tokens[0]);
        if (tokenZero > 999 && tokenZero <= 9999 && tokens.length > 4) {
          processNewInsert(tokens);
        } else if (tokenZero < replicaManager.getAllReplicas().size()) {
          processModifyRequest(tokens);
        } else {
          throw new InvalidWordException(
              "[ Line Number "
                  + count
                  + " ] "
                  + ((tokens[0].length() > 0)
                      ? "(" + tokens[0] + ")"
                      : "(Empty Line / Invalid First Token)")
                  + " Please Ensure Input File contains Valid Lines");
        }
        line = fp.poll();
      }
      if (count == 0) throw new EmptyInputFileException();
    } catch (IOException
        | InvalidWordException
        | NumberFormatException
        | NullPointerException
        | EmptyInputFileException
        | CloneNotSupportedException e) {
      System.out.println(e);
      System.out.println("(processFile method) Terminating Program");
      System.exit(1);
    }
  }

  private void processNewInsert(String[] tokens) throws CloneNotSupportedException {
    int bNumber = Integer.parseInt(tokens[0]);
    String firstName = tokens[1];
    String lastName = tokens[2];
    double gpa = Double.parseDouble(tokens[3]);
    String major = tokens[4];
    HashSet<String> skills = new HashSet<>();

    for (int i = 5; i < tokens.length; i++) {
      if (skills.size() < 10) {
        skills.add(tokens[i]);
      }
    }

    StudentRecord record = new StudentRecord(bNumber, firstName, lastName, gpa, major, skills);
    replicaManager.insert(record);
  }

  private void processModifyRequest(String[] tokens) throws CloneNotSupportedException {
    //
    //    System.out.println();
    //
    //    for (String token : tokens) {
    //      System.out.println(token);
    //    }

    int replicaID = Integer.parseInt(tokens[0]);
    int bNumber = Integer.parseInt(tokens[1]);
    String origValue = tokens[2];
    String newValue = tokens[3];

    replicaManager.updateNode(replicaID, bNumber, origValue, newValue);
  }
}
