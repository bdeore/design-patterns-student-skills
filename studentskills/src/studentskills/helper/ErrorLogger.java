package studentskills.helper;

import studentskills.util.Results;

public class ErrorLogger {

  private static Results uniqueInstance;

  private ErrorLogger() {}

  public static synchronized Results getInstance() {
    if (uniqueInstance == null) {
      uniqueInstance = new Results();
    }
    return uniqueInstance;
  }
}
