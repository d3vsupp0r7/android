# Table of Contents

* [Scope](#scope)
* [Prerequisite](#prerequisite)
* [UI Design](#ui-design)
* [BackEnd informations](#backend-informations)
  * [REST API Mocking](#rest-api-mocking)
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

# Prerequisite
* Java version 1.8
* Gradle

# UI Design

For UI design we have used a open-source tool named **Pencil**, avaiable [here](https://pencil.evolus.vn/)

# BackEnd informations

## REST API Mocking

In order to simulate a json response we use [Wiremock](http://wiremock.org/) tool avaiable [here](http://wiremock.org/docs/download-and-installation/)

To run a Wiremock you need to:
1) Add a json mock mapping file under the folder **<wiremock_jar_home>/mappings**  

2) Run Wiremock standalone application on your chosen http port as example:  

```console
java -jar wiremock-standalone-*<version>*.jar --port *<your_http_port>* 
```

To run Wiremock on 9091 http port:

```console
java -jar wiremock-standalone-2.24.0.jar --port 9091
```

* **List all mappins avaiable on wiremock**  

```
http://localhost:<wiremock_port>/__admin/
```

# Running Test
*TO_DO*

# Built with
* [Java SDK Version](http://www.oracle.com/technetwork/java/javase/downloads/index.html) - 1.8
* [Gradle](https://gradle.org/) - Dependency Management

# Default library dependency

## ORM

* **room :** 1.1.1

## Rest client libary

* **retrofit2**
  * **core:** '2.4.0'
  * **json-parser:** converter-gson

## Util libraries

* **joda-time:** 2.10.3  
  Library used for Date/Time management  

* **apache_collections:** 4.4

## Test libraries
* **junit.version:** 4.12

## Authors
*TO_DO*

## Licence
*TO_DO*

## Acknowledgments
*TO_DO*
