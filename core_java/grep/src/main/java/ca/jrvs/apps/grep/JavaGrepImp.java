package ca.jrvs.apps.grep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.BasicConfigurator;

public class JavaGrepImp implements JavaGrep {

  final Logger logger = LoggerFactory.getLogger(JavaGrepImp.class);

  private String regex;
  private String rootPath;
  private String outFile;


  public static void main(String[] args) {

    if (args.length < 3) {
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }

    BasicConfigurator.configure();

    JavaGrepImp javaGrepImp = new JavaGrepImp();
    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRootPath(args[1]);
    javaGrepImp.setOutFile(args[2]);

    try {
      javaGrepImp.process();
    } catch (Exception ex) {
      javaGrepImp.logger.error("Error: Unable to process", ex);
    }

  }

  @Override
  public void process() throws IOException {
    List<String> matchedLines = new ArrayList<>();

    for (File file : ListFiles(getRootPath())) {
      try {
        for (String line : readLines(file)) {
          if (containsPattern(line)) {
            matchedLines.add(line);
          }
        }
      } catch (Exception e) {
        throw new IOException("File could not be read");
      }
    }
    try {
      writeToFile(matchedLines);
    } catch (IOException exp) {
      throw exp;
    }
  }

  @Override
  public List<File> ListFiles(String rootDir) {
    File dir = new File(rootDir);
    List<File> resultFiles = new ArrayList<>();
    File[] dirCon = dir.listFiles();

    for (File dirCo : dirCon) {
      if (dirCo.isDirectory()) {
        List<File> dirResults = ListFiles(dirCo.toString());
        resultFiles.addAll(dirResults);
      } else {
        resultFiles.add(dirCo);
      }
    }

    return resultFiles;
  }

  @Override
  public List<String> readLines(File inputFile) throws IllegalArgumentException, IOException {
    List<String> lines = new ArrayList<>();

    try {
      BufferedReader reader = new BufferedReader(new FileReader(inputFile));
      String line;
      while ((line = reader.readLine()) != null) {
        lines.add(line);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("Input file is not Valid or regular file");
    }
    return lines;
  }

  @Override
  public boolean containsPattern(String line) {
    Pattern pattern = Pattern.compile(getRegex());
    Matcher matcher = pattern.matcher(line);
    return matcher.find();
  }

  @Override
  public void writeToFile(List<String> lines) throws IOException {
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(getOutFile(), true));
      for (String line : lines) {
        writer.write(line);
        writer.newLine();
      }
    } catch (IOException e) {
      throw new IOException("Error: could not write to a file");
    }
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
  public String getRegex() {
    return regex;
  }

  @Override
  public void setRegex(String regex) {
    this.regex = regex;
  }

}
