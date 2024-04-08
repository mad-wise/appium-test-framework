# E-commerce Testing Framework

## Overview

This E-commerce Testing Framework automates testing for the e-commerce mobile application. It leverages Appium and TestNG to perform unit and integration testing, structured in an object-oriented fashion with a focus on page objects and JSON-driven test data provisioning.

## Project Structure

The framework's directory and file structure is as follows:

- **`src/main/java`**: Contains the application's main functionalities and utilities.
  - **`nnsomova.pageObjects.android`**: Page objects for app screens.
    - `CartPage.java`
    - `FormPage.java`
    - `ProductCatalogue.java`
  - **`nnsomova.resources`**: Configuration files and resources.
    - `data.properties`
  - **`nnsomova.utils`**: Utility classes supporting test scripts.
    - `AndroidActions.java`
    - `AppiumUtils.java`

- **`src/test/java`**: Test cases utilizing TestNG.
  - **`nnsomova`**: Test scripts that utilize page objects.
    - `eCommerce_tc_1.java`
    - `eCommerce_tc_2.java`
  - **`nnsomova.testData`**: JSON files containing test data.
    - `eCommerce.json`
  - **`nnsomova.testUtils`**: Base classes and utilities for test execution.
    - `AndroidBaseTest.java`
    - `ExtentReporterNG.java`
    - `Listeners.java`

- **`reports`**: Generated reports from test executions.

- **`testNGSuites`**: XML files for TestNG suite definitions.
  - `testng.xml`
  - `testngSmoke.xml`

- **`Maven Dependencies`**: Project libraries and frameworks.

- **`pom.xml`**: Maven POM file for managing dependencies.

## Key Components

- **`FormPage`**: Interaction with the application's form elements.
- **`ProductCatalogue`**: Management of the product catalogue actions.
- **`CartPage`**: Manipulations of cart items and checkout processes.

## Setup Instructions

To set up the framework, perform the following:

1. Install Java and Maven on your machine.
2. Clone the project repository.
3. Import it as a Maven project into your IDE.
4. Set up and start the Appium server.
5. Update `data.properties` with required configurations.

## Test Data

JSON-formatted test data is stored in `eCommerce.json`, facilitating easy extension and modification for data-driven tests.
