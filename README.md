# 🚀 E-Commerce API Automation Task

A comprehensive REST API automation project built with **Java**, **Rest-Assured**, and **TestNG** that provides **100% test coverage** for the **Platzi Fake Store API** (https://fakeapi.platzi.com/).

This suite validates the entire website's functionality, including complex CRUD operations, authentication, and file management.

## 📋 Table of Contents

- [✨ Features](#-features)
- [📦 Module Coverage](#-module-coverage)
- [🛠 Tech Stack](#-tech-stack)
- [📁 Project Structure](#-project-structure)
- [🧪 Running Tests](#-running-tests)
- [📊 Reporting](#-reporting)
- [📝 Tasks for Later](#-tasks-for-later)
- [👨‍💻 Author](#-author)

---

## ✨ Features

- **Full Website Coverage** - Comprehensive testing of all Platzi Fake Store API modules.
- **Parallel Execution** - Configured via `testng.xml` to run 6 threads simultaneously for maximum speed.
- **Advanced Allure Reporting** - Organized by **Epic**, **Feature**, and **Story** for detailed professional visualization.
- **Centralized API Client** - Robust request handling with automated token management and retry logic.
- **Dynamic Data Generation** - Realistic test data generated on-the-fly using **JavaFaker**.
- **DTO Architecture** - Clean separation of request/response payloads using the Data Transfer Object pattern.

---

## 📦 Module Coverage

The suite provides full CRUD and edge-case testing for:
- **Products** (Create, Read, Update, Delete, Pagination)
- **Categories** (Manage categories and filter products by category)
- **Users** (Full user lifecycle management)
- **Authentication** (JWT Login, Profile retrieval, and Token validation)
- **Files** (Multipart file uploads)
- **Filter Products** (Advanced filtering by title, price, and category)
- **Locations** (Custom module implementation)


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
├── src/main/java/org/example/
│   ├── dto/                # Data Transfer Objects (POJOs for Requests & Responses)
│   ├── framework/          # Core Automation Engine
│   │   ├── apis/           # API Endpoint wrappers (Products, Categories, Users, etc.)
│   │   ├── auth/           # JWT Token and Session management
│   │   ├── client/         # Centralized API Client (Rest-Assured wrapper)
│   │   ├── config/         # Environment routes and project configuration
│   │   ├── listeners/      # TestNG & Allure reporting listeners
│   │   └── utils/          # Generic helper methods
│   └── utils/              # Project-wide utility classes
├── src/test/java/org/example/
│   ├── datagenerators/     # Dynamic test data logic (JavaFaker)
│   └── testscases/         # Organized test suites (Split by Module)
│       ├── auth/           # Authentication tests
│       ├── categories/     # Category CRUD tests
│       ├── files/          # File upload tests
│       ├── filterproducts/ # Product filtering tests
│       ├── locations/      # Location management tests
│       ├── products/       # Product CRUD & pagination tests
│       └── users/          # User management tests
├── testng.xml              # Suite configuration with parallel settings
└── pom.xml                 # Maven dependencies and reporting plugins
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
