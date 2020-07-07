package studentskills.util;

import java.nio.file.InvalidPathException;
import studentskills._exceptions.EmptyInputFileException;

/** interface for standard out IO */
public interface StdoutDisplayInterface {

  void write() throws ArithmeticException, InvalidPathException, EmptyInputFileException;
}
