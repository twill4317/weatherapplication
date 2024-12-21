---
- Initial commit **users will each require there own api key**
  - Add your api key to WeatherRepository before running 

- Incoming Commit 
  - implement universal api key w security
  - More Unit Test
  - Data persistence bug fix
---
# Weather Tracker App

This is a **Weather Tracker** app built using **Kotlin**, **Jetpack Compose**, **MVVM Architecture**, and **Hilt** for Dependency Injection. The app fetches weather data for a selected city and displays it to the user. It also supports persistence of the selected city across app launches using **DataStore**.

## Table of Contents
- [Overview](#overview)
- [Tech Stack](#tech-stack)
- [Features](#features)
- [Setup and Configuration](#setup-and-configuration)
    - [Prerequisites](#prerequisites)
    - [Clone the Repository](#clone-the-repository)
    - [Open the Project in Android Studio](#open-the-project-in-android-studio)
    - [Gradle Sync](#gradle-sync)
    - [API Key Configuration](#api-key-configuration)
- [Running the App](#running-the-app)
- [Testing](#testing)
    - [Unit Tests](#unit-tests)
- [Contributing](#contributing)
- [License](#license)

---

## Overview

The **Weather Tracker App** allows users to:
- Search for weather details by city name.
- Display information such as temperature, weather condition, humidity, UV index, and "feels like" temperature.
- Persist the selected city across app launches using **DataStore**.
- The app uses **WeatherAPI.com** to fetch weather data, and the user interface is built using **Jetpack Compose**.

---

## Tech Stack

- **Kotlin** - Main programming language.
- **Jetpack Compose** - UI toolkit for building modern UIs in Android.
- **MVVM Architecture** - Clean architecture for maintainable and testable code.
- **Hilt** - Dependency Injection framework for Android.
- **WeatherAPI.com** - Weather data source.
- **DataStore** - To persist the selected city.
- **JUnit 5** - For unit testing.
- **Mockito** - For mocking dependencies in unit tests.
- **Kotlin Coroutines** - For asynchronous programming.

---

## Features

- **Search City**: Users can search for a city's weather by typing in the search bar.
- **Weather Details**: Displays detailed weather information, including:
    - City name
    - Temperature
    - Weather condition
    - Humidity
    - UV index
    - Feels-like temperature
- **Persistent City Storage**: The app remembers the last selected city across app launches using **DataStore**.
- **Error Handling**: Displays a user-friendly message if weather data cannot be fetched.

---

## Setup and Configuration

### Prerequisites

Before running the project, make sure you have the following installed:

- **Android Studio** (latest version recommended)
- **Java Development Kit (JDK 11)**

### Clone the Repository

1. Clone the repository to your local machine:

```bash
git clone https://github.com/your-username/weather-tracker-app.git
```

2. Navigate into the project directory:

```bash
cd weather-tracker-app
```

### Open the Project in Android Studio

1. Open **Android Studio**.
2. Click on **Open an Existing Project** and select the cloned project directory.
3. Wait for Android Studio to sync the project and download necessary dependencies.

### Gradle Sync

After opening the project in Android Studio, it should automatically prompt you to sync the Gradle files. Click **Sync Now**.

If Gradle does not sync automatically, you can trigger the sync manually:
- Go to **File** → **Sync Project with Gradle Files**.

### API Key Configuration

You need an API key from **WeatherAPI.com** to fetch weather data.

1. Go to [WeatherAPI.com](https://www.weatherapi.com/) and sign up for a free account.
2. Obtain your **API key** from the dashboard.
3. In the project, open `gradle.properties` and add the following line:

```properties
WEATHER_API_KEY=your_api_key_here
```

4. In the `WeatherApiService` class, use this API key to make requests to the WeatherAPI.com service. Example usage (replace `your_api_key_here` with the actual key):

```kotlin
private const val API_KEY = BuildConfig.WEATHER_API_KEY
```

### Gradle Properties (Optional)

You can also set the **API key** directly in your `build.gradle` file (if you want to avoid using `gradle.properties`):

```gradle
android {
    defaultConfig {
        buildConfigField("String", "WEATHER_API_KEY", "\"your_api_key_here\"")
    }
}
```

---

## Running the App

1. Make sure your **Android Emulator** is running or a physical device is connected.
2. Click the **Run** button (green triangle) in Android Studio to build and run the app.
3. The app will launch, and you can start interacting with it by searching for cities and viewing the weather details.

---

## Testing

### Unit Tests

The project contains **unit tests** that verify the correctness of the `WeatherViewModel` and other important logic in the app. The tests are written using **JUnit 5**, **Mockito**, and **Coroutines**.

To run the tests:

1. **In Android Studio**, go to the **Run** menu.
2. Select **Run 'All Tests'** or run specific tests from the **Project** pane by right-clicking on the test file or method.

**Test coverage** includes:
- Success and failure scenarios for fetching weather data.
- Ensuring the ViewModel correctly updates its state when weather data is fetched.

---

## Contributing

Contributions to the **Weather Tracker App** are welcome! If you would like to contribute, please fork the repository and create a pull request with your changes.

### Steps for Contributing:
1. Fork the repo.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes and commit them (`git commit -m "Add new feature"`).
4. Push to your forked repository (`git push origin feature-branch`).
5. Open a pull request to the `main` branch of the original repository.

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

---

### Notes:

- Replace placeholders such as `your-username` with the actual GitHub username in the **Clone the Repository** section.
- Include any additional setup instructions specific to your project if necessary.

Let me know if you need any additional adjustments or have other questions!