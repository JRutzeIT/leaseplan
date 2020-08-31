# How to run the test

Use the following command at the terminal:

```bash
gradlew clean test
```

If you want to run the test with logging:

```bash
gradlew clean test -i
```

# Adding additional tests

To expand the existing pet testset. Add more scenarios to the pet.feature file and expand the PetSteps definition file.

In order to create new tests. Create a new feature file, a corresponding step definition file and runner.

# Structure

The runners are located at:

```bash
src/test/java/cucumber/runners/
```

The step definition files are located at:


```bash
src/test/java/cucumber/steps/
```

The feature files are located at:


```bash
src/test/resources/features/
```

# Endpoints documentation

The documentation of the endpoints is found at: [petstore.swagger.io](https://petstore.swagger.io/)

