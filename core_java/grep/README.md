# Java Grep Application
- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Implementation](#implementation)
    - [Pseudocode](#pseudocode)
    - [Performance Issue](#performance-issue)
- [Test](#test)
- [Deployment](#deployment)
- [Improvements](#improvements)

# Introduction

The Java Grep App was implemented to mimic the Linux CLI command `grep` 
and allows users to search matching strings from the file system.
There are three input arguments: regex pattern, the root directory
and the output file to save the results.
The application was developed using Java 8, Apache Maven, Java Regex and Lambda API.
It was also Dockerized and the image was uploaded to Docker Hub.

# Quick Start

You can start using the application using two approaches:

1. Using `.jar` file. Make sure you have [Maven](https://maven.apache.org/download.cgi) downloaded and installed.
    Three CLI arguments:
     * `regex` - string pattern
     * `rootDir` - root directory path
     * `outfile` - file, where the results will be saved
```
# Compile and package the app
mvn clean package

# Launch JVM and run the app
java -cp target/grep-1.0-SNAPSHOT.jar ca.jrvs.apps.grep.JavaGrepImp [regex] [rootDir] [outFile]
```
2. Using Docker. Make sure you have [Docker](https://docs.docker.com/get-docker/) downloaded and installed.
```
# pull Docker image from Docker Hub
docker pull olensa/grep

# run the container
docker run --rm -v `pwd`/data:/data -v `pwd`/log:/log olensa/grep [regex] [rootDir] [outFile]
```


# Implementation

## Pseudocode
```
matchedLines = []                   //create an empty List of Strings
for file in listFiles(rootDir)      //traverse a given directory and return all files as List of Files
    for line in readLines(file)     //read files and return all the lines as a List of Strings
        if containsPattern(line)    //check if the line contains the given pattern
            matchedLines.add(line)  //if yes - add the line to the output file
writeToFile(matchedLines)           //save the matched lines to the output file
```

## Performance Issue

There is a chance that the app will return `OutOfMemory Error`
when there is insufficient space to allocate an object in the Java heap.
It happens when the processed file's size is bigger than the heap's size.<br />
A possible solution is to use Lambda and Stream APIs and replace the `List` datatype with `Buffer` or `Stream`.
In this case, the file's content will be loaded and unloaded partially as necessary, not clogging the memory.


# Test

The Java Grep App was tested on Linux CentOS 7 (running on GCP's virtual machine). 
Testing was performed manually through the IntelliJ file configurations by providing 3 input arguments (pattern, rootDir, outfile).
The input arguments (different regex patterns, existing/non-existing directories) 
were manually changed and used to verify the results by comparing them with the original Linux `grep` command.

# Deployment

The Grep app was deployed by creating a Docker image and pushing it to the Docker Hub for easier distribution.

# Improvements

* Show the name of the file, where the pattern was found
* Make the app more memory efficient as mentioned above
* Implement more Linux `grep` functions (`egrep`,`fgrep`, `agrep`)
* Allow the user to choose the character encoding - in special cases, it might speed up the process.