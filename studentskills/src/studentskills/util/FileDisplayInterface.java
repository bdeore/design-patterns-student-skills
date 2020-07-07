package studentskills.util;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import studentskills._exceptions.EmptyInputFileException;

/** Interface for File IO */
public interface FileDisplayInterface {
  void write(String output_filename)
      throws ArithmeticException, InvalidPathException, IOException, EmptyInputFileException;
}
