# Selenium-project-with-gradle-and-cucumber

A Selenium WebDriver test automation project using Gradle and Cucumber BDD framework.

## Project Structure

```
src/test/
├── java/
│   ├── configuration/    # WebDriver configuration
│   ├── pageObjects/      # Page Object Models
│   └── stepDefinitions/  # Cucumber step definitions and hooks
└── resources/
    └── features/         # Cucumber feature files
```

## Prerequisites

- **Java**: JDK 21 or higher
- **Gradle**: 8.0+
- **Browser**: Chrome (WebDriverManager handles driver management)

## Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd Selenium-project-with-gradle-and-cucumber
```

2. Build the project:
```bash
./gradlew build
```

## Running Tests

### Run all tests
```bash
./gradlew cucumber
```

### Run tests by tag
```bash
# Run homepage tests
./gradlew cucumber -PcucumberTag="@homePage1 or @homePage2 or @homePage3"

# Run product page tests
./gradlew cucumber -PcucumberTag="@productPage1 or @productPage2"

# Run all web automation tests
./gradlew cucumber -PcucumberTag="@webAutomation"
```

### Run specific test
```bash
./gradlew cucumber -PcucumberTag="@homePage1"
```

## Test Reports

After running tests, HTML reports are generated at:
```
build/reports/cucumber/
```

Open `index.html` in a browser to view detailed test results.

## Features

- **Page Object Model** (POM) for maintainable test code
- **Cucumber BDD** for readable test scenarios
- **WebDriverManager** for automatic driver management
- **Explicit waits** and synchronization mechanisms
- **Case-insensitive assertions** for robust testing
- **Automatic browser cleanup** after each test via Cucumber hooks
- **GitHub Actions CI/CD** integration

## Test Tags

- `@homePage1`: Verify products are properly displayed
- `@homePage2`: Verify product filtering with sorting
- `@homePage3`: Verify favorite product icon functionality
- `@productPage1`: Verify product details page
- `@productPage2`: Verify product add to cart functionality
- `@webAutomation`: Meta-tag for all web automation tests

## GitHub Actions

This project includes automated testing via GitHub Actions. The workflow:
- Runs on every push to `main` or `develop` branches
- Runs on all pull requests
- Executes all `@webAutomation` tagged tests
- Generates and uploads Cucumber HTML reports
- Publishes test results summary

See `.github/workflows/test.yml` for workflow configuration.

## Troubleshooting

### StaleElementReferenceException
- Ensure `PageFactory.initElements(driver, this)` is called after DOM updates
- Add explicit waits before interacting with elements

### Tests failing intermittently
- Increase wait times in `HomePage.java` methods
- Check that the application under test is responsive
- Verify test data and search functionality

### Browser not closing
- Verify `@After` hook is enabled in `WebHooks.java`
- Check logs for exceptions during tearDown

## Future Enhancements

- [ ] Add test result email notifications
- [ ] Implement parallel test execution
- [ ] Add screenshot capture on failures
- [ ] Integrate with cloud testing platforms
- [ ] Add performance metrics collection

## Support

For issues or questions, please open an issue in the repository.
