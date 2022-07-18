package ca.jrvs.apps.practice;

public interface RegexExc {
    /**
     * Check if filename end with a jpeg or jpeg (case insensitive)
     * @param filename
     * @return return true if condition is true
     */
    public boolean matchJpeg(String filename);

    /**
     * Returns true if IP address is within the ranges 0.0.0.0 to 999.999.999.999
     * @param ip ip address
     * @return true if condition is true
     */
    public boolean matchIp(String ip);

    /**
     * Check if this line is empty or has a white space
     * @param line
     * @return return true if condition is true
     */
    public boolean isEmptyLine(String line);

}
