Jersey & Kotlin
========

[![Build Status](https://github.com/taulinger/kotlin-jersey/workflows/Java%20CI/badge.svg)](https://github.com/taulinger/kotlin-jersey/actions/workflows/java.yml?query=branch%3Amain)

Servlet based Jersey setup in Kotlin.


## Building from source

Prerequisites
- JDK 11 or later must be installed (https://adoptium.net/)
- Maven 3 must be installed

Steps
- Clone the GitHub project:
```
git clone https://github.com/taulinger/kotlin-jersey
```
- Change to the git repository and run Maven:
```
mvn install
```
- compiled artifact `kotlinJersey` can be found under `./target` and is ready to be deployed to your servlet container
- you can also run the app directly using the jetty plugin: `mvn jetty:run -Djetty.port={your port}` 