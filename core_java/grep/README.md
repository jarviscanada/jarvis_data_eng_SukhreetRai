# Introduction
This Java application is a implementation of a Unix command 'grep' which helps user to search for certain patern in the files and directories recursively with a help of regular expression. Apache Maven is used to manage dependencies and plugins in thisproject. Implementation of the grep functionality in this project relies on pre-Java 8 JDK features. Finally, the app is deployed on Docker hub using a Docker image built from the project code.

# Quick Start
```bash
# The docker image named sukhreetrai/grep can be downloaded from DockerHub.

# Build a docker container from your present working directory
docker build -t sukhreetrai/grep

# Running docker container
docker run -rm -v `pwd`/data:/data -v `pwd`/out:/out sukhreetrai/grep [INSERT REGEX HERE] [rootDir] [output]
```

#Implemenation
## Pseudocode
In this project, User has to provide the regular expression, the root directory, and the output file. ListFiles method returns all the non-directory files from the root directory. Then, ReadLines method traverse each file and collect all the lines. ContainsPattern method gets collected Lines from ReadLines and scan to see if lines have the regex pattern match. WritetoFile method returns if there is a match and print it on the output file which was passed by user as an argument. 


## Performance Issue
 Depending on the size of the heap space allocated by the JVM at runtime, and the amount of objects created during program execution,there is a chance of running into a OutOfMemoryError. This performance issue can be resolved by two ways, the first is to use the `-Xmx` flag when configuring the JVM to specify a maximum heap size greater the size of the files that are being put through the grep program, but this is easier said than done. Another solution is to use the Stream and Lambda implementation which helps user to read data through source rather than storing it on heap and then read. Therefore, Java will not encounter the outOfMemory error.

# Test

The application was tested manually using some sample data in the form of a large text file that contains several of William Shakespeare's most famous plays. The project was tested sequentially with the first test being of its ability to access files recursively within directories and also its ability to distinguish between directories and regular files. Finally, it was all put together in the `process()` method and tested as one program using the sample text files provided and comparing
the expected output with the text generated in the `outfile`.

# Deployment
This program was deployed using Docker. A dockerfile was made and included in the GitHub repository to build the code from scratch on another computer.

# Improvement
1. First, I would like to improve with this project is the speed with which it actually parses the information and scans through the files.
2. Another potential improvement is being able to match lines in a case insensitive way.
