package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface JavaGrep {
    /**
     * Top level search workflow
     * @throws IOException
     */
    void process() throws IOException;

    /**
     * Traverse the given directory and then return all the files
     * @param rootDir given directory
     * @return files inside that directory
     */
    List<File> ListFiles(String rootDir);

    /**
     * Read a file and return all the lines from the file
     *
     * FileReader class of java.io package can be used to read data from files.
     * BufferedReader is a Java class that reads text from input stream ,and it inherits the reader class
     * The character encoding of Java is UTF-16. how bites are converted to characters so it can be read by human
     *
     * @param inputFile file to be read
     * @return lines
     * @throws  IllegalArgumentException if a given inputFile is not a file
     */
    List<String> readLines(File inputFile) throws IllegalArgumentException, IOException;

    /**
     * check if a given line contains the desired regex pattern which is passed by user
     * @param line input string
     * @return true if there is a match
     */
    boolean containsPattern(String line);

    /**
     * Write lines to a file
     *
     * FileOutputStream: Creates a file output stream to write to the file represented by specified File object
     * OutputStreamWriter: Is a stream that converts character streams to byte stream using a specific charset.
     * BufferedWriter: Writes text to a character output stream. Buffered to increase efficiency.
     *
     * @param lines matched line
     * @throws IOException if write failed
     */
    void writeToFile(List<String> lines) throws IOException;

    String getRootPath();

    void setRootPath(String rootPath);

    String getRegex();

    void setRegex(String regex);

    String getOutFile();

    void setOutFile(String outFile);

}
