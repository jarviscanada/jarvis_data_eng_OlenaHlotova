package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.log4j.BasicConfigurator;

public class JavaGrepLambdaImp extends JavaGrepImp{

  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }

    //Usage default logger config
    BasicConfigurator.configure();

    JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
    javaGrepLambdaImp.setRegex(args[0]);
    javaGrepLambdaImp.setRootPath(args[1]);
    javaGrepLambdaImp.setOutFile(args[2]);

    try {
      javaGrepLambdaImp.process();
    } catch (Exception ex) {
      javaGrepLambdaImp.logger.error("Error. Process Failed", ex);
    }
  }
/**
 * Implement using Lambda and Stream APIs
 */
  @Override
  public List<String> readLines(File inputFile){
    if(!inputFile.isFile()){
      throw new IllegalArgumentException("Not a file.");
    }
    List<String> lines = new ArrayList<>();
    try {
      Files.lines(Paths.get(inputFile.getPath())).collect(Collectors.toList());
    } catch (IOException e) {
      logger.debug("Can't read the file", e);
    }
    return lines;
  }

  /**
   * Implement using Lambda and Stream APIs
   */
  @Override
  public List<File> listFiles(String rootDir) {
    Path path = Paths.get(rootDir);
    List<File> files = new ArrayList<>();
    try {
     Files.walk(path).filter(Files::isRegularFile).forEach(file->files.add(file.toFile()));
    } catch (Exception e){
      logger.debug("Unable to read file/directory", e);
    }
    return files;
  }
}
