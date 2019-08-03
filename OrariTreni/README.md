# Table of Contents

* [Scope](#scope)
  * [Getting started](#getting-started)
  * [Prerequisite](#prerequisite)
    * [Installing](#installing)
  * [Running Test](#running-test)
  * [Built with](#built-with)
    * [Default library dependency](#default-library-dependency)
      * [Util libraries](#util-libraries)
      * [Test libraries](#test-libraries)
  * [Authors](#authors)
  * [Licence](#licence)

# Scope

This is a simple project to simulate a train journey related operations within an Android application.

**NOTE:**  
This application is intented as example/educational scope. 

## Getting started
*TO_DO*

### Prerequisite
* Java version 1.8
* Gradle

### Installing

#### REST API Mocking

In order to simulate a json response we use [Wiremock](http://wiremock.org/) tool avaiable [here](http://wiremock.org/docs/download-and-installation/)

To run a Wiremock you need to:
1) Add a json mock mapping file under the folder **<wiremock_jar_home>/mappings**  

2) Run Wiremock standalone application on your chosen http port as example:  

java -jar wiremock-standalone-*version*.jar --port *your_http_port* 

To run Wiremock on 9091 hhtp port:

```console
java -jar wiremock-standalone-2.24.0.jar --port 9091
```

* **List all mappins avaiable on wiremock**
http://localhost:<wiremock_port>/__admin/

## Running Test
*TO_DO*

## Built with
* [Java SDK Version](http://www.oracle.com/technetwork/java/javase/downloads/index.html) - 1.8
* [Gradle](https://gradle.org/) - Dependency Management

### Default library dependency

#### Rest client libary

* **retrofit2**
  * **core:** '2.4.0'
  * **json-parser:** converter-gson

#### Util libraries


#### Test libraries
* **junit.version:** 4.12


## Authors
*TO_DO*

## Licence
*TO_DO*

## Acknowledgments
*TO_DO*

