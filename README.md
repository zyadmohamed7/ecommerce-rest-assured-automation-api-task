# 🚀 E-Commerce API Automation Task

A simple REST API automation project built with **Java**, **Rest-Assured**, and **TestNG** for testing the **Platzi Fake Store API** (https://fakeapi.platzi.com/).

## 📋 Table of Contents

- [✨ Features](#-features)
- [🛠 Tech Stack](#-tech-stack)
- [📁 Project Structure](#-project-structure)
- [🧪 Running Tests](#-running-tests)
- [📊 Reporting](#-reporting)
- [📝 Tasks for Later](#-tasks-for-later)
- [👨‍💻 Author](#-author)

---

## ✨ Features

- **TestNG Test Engine** - Used for running tests using `test.xml` with **parallel execution** (running multiple tests at the same time to speed up execution).
- **Rest-Assured** - Used to send HTTP requests and validate responses.
- **Data Generation** - Generates random test data using **JavaFaker**.
- **Allure Integration** - Generates HTML test reports.
- **DTO Pattern** - Organizes Request and Response payloads into separate Java classes.

---

## 🛠 Tech Stack

- **Java**
- **TestNG**
- **Rest Assured**
- **Jackson**
- **JavaFaker**
- **Allure Report**

---

## 📁 Project Structure

```text
ProjectRoot/
├── src/main/java/org/example/framework/ 
│   ├── apis/               # API endpoints (ProductsApi, CategoriesApi)
│   ├── client/             # API Client to send requests
│   └── dto/                # Data Transfer Objects (Requests & Responses)
├── src/test/java/org/example/
│   ├── datagenerators/     # Test data creation
│   └── testscases/         # TestNG Test Classes
├── testng.xml              # TestNG Suite Configuration
└── pom.xml                 # Maven configuration
```

---

## 🧪 Running Tests

You can run the tests using Maven. The tests are configured to run in **parallel** (up to 6 test classes at once) for faster execution.

### Run All Tests
```bash
mvn clean test
```

---

## 📊 Reporting

This project uses **Allure** for test reporting.

**To view the test report:**
1. Run the tests: `mvn clean test`
2. Open the report:
   ```bash
   mvn allure:serve
   ```

---

## 📝 Tasks for Later

- [x] Create a TestNG `test.xml` file and include both test classes for Categories and Products.
- [x] Run using `mvn` command to execute this `test.xml` file.
- [x] Push your project to a GitHub Repository.
- [x] Setup Jenkins on your machine.
- [x] Create a Jenkins project that runs the same MVN command each 12 hours.
- [x] Generate an XML report for the test results.

---

## 👨‍💻 Author

**Zyad mohamed**
- **GitHub**: [zyadmohamed7](https://github.com/zyadmohamed7)
- **LinkedIn**: [Zyad Mohamed](https://www.linkedin.com/in/zyad-mohamed-182b7a363/)
- **Email**: zyadmohamed3711@gmail.com
