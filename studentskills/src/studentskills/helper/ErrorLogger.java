package studentskills.helper;

import studentskills.util.Results;

/** Singleton class for logging error information. maintains a buffer for storing error messages */
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
