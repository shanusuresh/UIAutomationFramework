# FlinkWeatherShopperAssignment
This repo contains the test automation script for the weather shopper application

## Concepts Included

* Parallel test runs
* Cross browser test runs
* Page Object pattern
* Common web page interaction methods
* Externalised test configuration
* Commonly used test utility classes
* Neat test report
* Data driven approach

## Tools

* Maven
* TestNG
* Selenium Webdriver
* apache poi
* extent report
* assertJ
* webdrivermanager

## Requirements

In order to utilise this project you need to have the following installed locally:

* Maven
* Chrome browser, Firefox browser, Safari browser
* Java 1.8

## Usage:
### 1. Steps to run the test:
    Option1: Go to the project root directory in the cmd prompt and enter "mvn clean install"
    
    Option2: On the IDE, choose the testng.xml and click run

### 2. Parallel execution:
    Ensure to set *@DataProvider(parallel=true)* in the file src/main/java/com/weathershopper/utils/DataProviderUtils.java
    
    Note: src/test/resources/excel/testdata.xlsx should have more than one tests marked 'yes' for execution

### 3. To run on multiple browsers (Framework supports chrome, firefox and safari):
    src/test/resources/excel/testdata.xlsx - create new rows for the test and pass different browser name
    
    browser values -- 'chrome','firefox','safari'
