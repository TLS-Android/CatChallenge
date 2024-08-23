#  Cat Database Android App

Developed by Tiago Santos

This Android application allows users to browse and discover different cat breeds. The user can select individual breeds
to open a screen that displays more information of that specific breed, as well as mark their favorites. 
The app fetches data from The Cat API and displays it in a user-friendly interface.


## Libraries and Technologies

* **Jetpack Compose:** Used for building the UI declaratively.

* **Hilt:** For dependency injection to manage dependencies effectively.

* **OkHttp:** Used with Retrofit to intercept network requests, add headers, enable logging, and handle caching.

* **Retrofit:** For making network requests to The Cat API.

* **Glide:** For loading and caching images efficiently.

* **Room:** For local data persistence.

* **Kotlin Coroutines:** For asynchronous programming and managing background tasks.

* **Flow:** For handling streams of data and observing changes.

* **ViewModel:** For managing UI-related data and handling interactions with the repository.

* **JUnit:** For writing and running unit tests.

* **Mockk:** Used for creating mock objects and dependencies.


## Architecture

The app follows a modern Android architecture based on the Model-View-ViewModel (MVVM) pattern.

UI (Jetpack Compose): Composable functions define the UI and handle user interactions.
ViewModel: Holds and manages UI state, interacts with the repository, and exposes data to the UI.

For the data layer, I split into two different folders, local and Remote.
Local refers to the use of Room and a Shared Preferences Helper. 
I used Shared Preferences to save the state of the Room database.
When the app is opened the first time, there is a request to the API to fetch all of the cat data.
After that, unless the app is uninstalled, the local storage is used to reduce 
the need to fetch from remote storage / allow for offline operation.

Repository: Acts as an intermediary between the ViewModel and the data sources (network and local database).
Data sources: The Cat API for fetching cat breed data and Room database for storing favorites.

