# How-to-Use-VHAL-in-Android: A Comprehensive Guide

This project provides a detailed guide on how to integrate the `car-lib` and utilize the Vehicle Hardware Abstraction Layer (VHAL) in your Android applications. By following this guide, you will learn how to access vehicle properties, such as speed and gear, and display them in your app's UI using modern Android development techniques.

## Project Setup

To get started with this project, follow these simple steps:

1.  **Clone the Repository:**
    ```bash
    git clone https://github.com/your-username/VHALSample.git
    ```
2.  **Open in Android Studio:**
    *   Launch Android Studio and select "Open an Existing Project."
    *   Navigate to the cloned repository and open it.
3.  **Integrate `car-lib`:**
    *   To enable VHAL access in your Android app, you need to integrate the `car-lib`. This can be done by adding the following line to your `app/build.gradle.kts` file:
        ```kotlin
        useLibrary("android.car")
        ```
    *   This will automatically include the necessary libraries to communicate with the car's hardware.

## How it Works

This project follows a clean architecture that separates the data layer from the UI layer. Here's a breakdown of the key components:

*   **`CarDataSource`:** This class handles the low-level interactions with the `CarPropertyManager`, which is the system service that provides access to vehicle properties.
*   **UI Layer:** The UI is built with Jetpack Compose and observes the `StateFlow` streams from the `CarDataSource` to display the latest vehicle data.

## Validation

To validate that the project is working correctly, follow these steps:

1.  **Run the App:**
    *   Build and run the app on an Android Automotive emulator or a compatible physical device.
2.  **Access Vehicle Properties:**
    *   Once the app is running, use the car's property control panel (available in the emulator) to change vehicle properties, such as speed and gear.
3.  **Observe UI Updates:**
    *   You should see the UI of the app update in real-time to reflect the changes you make in the property control panel.

## Dependencies

This project uses the following libraries:

*   **Jetpack Compose:** For building the UI.
*   **Kotlin Coroutines:** For managing background threads and asynchronous operations.
*   **Koin:** For dependency injection.
*   **`car-lib`:** For accessing the VHAL.

Here are the specific versions used in this project:

| Library                       | Version     |
| ----------------------------- | ----------- |
| Android Gradle Plugin         | 8.12.0-alpha05|
| Kotlin                        | 2.0.21      |
| Core KTX                      | 1.16.0      |
| JUnit                         | 4.13.2      |
| Espresso Core                 | 3.6.1       |
| Lifecycle Runtime KTX         | 2.9.1       |
| Activity Compose              | 1.10.1      |
| Compose BOM                   | 2024.09.00  |
| Koin BOM                      | 4.1.1       |
| Kotlinx Coroutines            | 1.8.0       |

## SEO Tags

Android, VHAL, Vehicle HAL, `car-lib`, StateFlow, Kotlin, Jetpack Compose, Android Automotive, Android App Development, Vehicle Properties, `CarPropertyManager`

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvement, please open an issue or create a pull request.

## License

This project is licensed under the Apache 2.0 License. See the `LICENSE` file for details.