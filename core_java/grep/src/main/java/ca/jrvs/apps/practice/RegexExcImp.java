package ca.jrvs.apps.practice;
import  java.util.regex.*;

public class RegexExcImp implements RegexExc{

    public boolean matchJpeg(String filename){
        return filename.matches(".*\\.jp[e]?g$");
    }


    public boolean matchIp(String ip){
        return ip.matches("^([0-9]{1,3}\\.){1,3}([0-9]{1,3})");
    }


    public boolean isEmptyLine(String line){
        return line.matches("^\\s*$");
    }

}
