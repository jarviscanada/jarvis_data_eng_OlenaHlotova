package ca.jrvs.apps.grep;


import java.io.*;
import java.util.ArrayList;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JavaGrepImp implements JavaGrep{

  final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

  private String regex;
  private String rootPath;
  private String outFile;
  // Setters and getters
  @Override
  public String getRegex() {
    return regex;
  }

  @Override
  public void setRegex(String regex) {
    this.regex = regex;
  }

  @Override
  public String getRootPath() {
    return rootPath;
  }

  @Override
  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

  @Override
  public String getOutFile() {
    return outFile;
  }

  @Override
  public void setOutFile(String outFile) {
    this.outFile = outFile;
  }

  @Override
  public void process() throws IOException {
    List<String> matchedLines = new ArrayList<>();
    for (File file : listFiles(this.rootPath)) {
      for (String line : readLines(file)){
        if (containsPattern(line)) {
          matchedLines.add(line);
        }
      }
    }
    writeToFile(matchedLines);
  }

  // Traverse a given directory and return all the files
  // The input directory is rootDir
  @Override
  public List<File> listFiles(String rootDir) {
    List<File> fileList = new ArrayList<>();
    File dir = new File(rootDir);
    if (dir.isFile()){
      fileList.add(dir);
    } else {
        for (File file : dir.listFiles()) {
          List<File> newFileList;
          newFileList = listFiles(file.getPath());
          fileList.addAll(newFileList);
        }
    }
    return fileList;
  }

  // Read a file and return all the lines
  @Override
  public List<String> readLines(File inputFile) {
    if(!inputFile.isFile()){
      throw new IllegalArgumentException("Not a file.");
    }
    List<String> readFile = new ArrayList<>();
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader(inputFile));
      String line = reader.readLine();
      while (line != null) {
        readFile.add(line);
        line = reader.readLine();
      }
      reader.close();
    } catch (IOException e) {
        logger.debug("Can't read the file", e);
    }
    return readFile;
  }
  // Check if a line contains the regex pattern (passed by the user)
  @Override
  public boolean containsPattern(String line) {
    return line.matches(regex);
  }

  // Write lines to a file
  @Override
  public void writeToFile(List<String> lines) throws IOException {
    OutputStream output = new FileOutputStream(outFile);
    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(output);
    for (String line : lines) {
      outputStreamWriter.write(line+"\n");
    }
    outputStreamWriter.close();
  }

  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }

    //Usage default logger config
    BasicConfigurator.configure();

    JavaGrepImp javaGrepImp = new JavaGrepImp();
    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRootPath(args[1]);
    javaGrepImp.setOutFile(args[2]);

    try {
      javaGrepImp.process();
    } catch (Exception ex) {
      javaGrepImp.logger.error("Error. Process Failed", ex);
    }
  }
}
