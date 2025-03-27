[![Build](https://github.com/mk-it-easy/libswiss/actions/workflows/maven.yml/badge.svg)](https://github.com/mk-it-easy/libswiss/actions/workflows/maven.yml)
# LibSwiss
LibSwiss is a Java library designed to provide robust validation for various data patterns, including aircraft registrations, Swiss social security numbers, and Swiss vehicle registrations. It offers a comprehensive set of utilities to ensure that these data types adhere to the expected standards.

## Features
Aircraft Registration Validation: Validate aircraft registration numbers to ensure they conform to the official format.
Swiss Social Security Number Validation: Check Swiss social security numbers for correct formatting and validity.
Swiss Vehicle Registration Validation: Validate Swiss vehicle registration numbers to ensure they meet the required standards.

## Getting Started

To use LibSwiss in your project, follow these steps:

### Step 1: Add Dependency

Add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>org.libswiss</groupId>
    <artifactId>libswiss</artifactId>
    <version>1.0.3</version>
</dependency>
```

### Step 2: Validate Data

Here is an example of how to use LibSwiss to validate a Swiss social security number:

```java
import org.libswiss.validator.OASIValidator;

public class Main {
    public static void main(String[] args) {
        OASIValidator oasiValidator = OASIValidator.getInstance();
        String ssn = "756.1234.5678.95";
        boolean isValid = oasiValidator.isValid(ssn);
        System.out.println("Is the Swiss social security number valid? " + isValid);
    }
}
```

You can similarly validate aircraft registrations and Swiss vehicle registrations using the respective validators provided by LibSwiss.
