# Android MVVM Base Architecture Mobile Application (Kotlin) using Architectural Components

# Highlights

1. MVVM Architectural pattern
2. Offline Support
3. Unit test demonstration using JUnit and Mockito
4. Use AndroidX for Android Support Libraries
7. Use of The Navigation component library
8. Data Binding

The application has been built with **offline support**. It has been designed using **Android Architecture components** with **Room** for offline data caching. The application is built in such a way that when ever there is a service call, the result will be stored in local database. If there is not internet connectivity local data will be utilize

The whole application is built based on the MVVM architectural pattern.

# Application Architecture
![alt text](https://cdn-images-1.medium.com/max/1600/1*OqeNRtyjgWZzeUifrQT-NA.png)

The main advatage of using MVVM, there is no two way dependency between ViewModel and Model unlike MVP. Here the view can observe the data changes in the viewmodel as we are using LiveData which is lifecycle aware. The viewmodel to view communication is achieved through observer pattern (basically observing the state changes of the data in the viewmodel).

# Overview of Offline Architecture
Consider the following diagram, which shows how all the modules should interact with one another in the app:
![alt text](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)

# Programming Practices Followed
a) Android Architectural Components <br/>
b) Dagger 2 for Dependency Injection <br/>
c) MVVM <br/>
d) Retrofit with Okhttp <br/>
e) Room for data caching <br/>
f) JUnit and Mockito for Unit testing <br/>
d) Repository pattern <br/>
e) Couroutine with Flow

# Libraries Used
1. Architecture - A collection of libraries that help you design robust, testable, and maintainable apps. Start with classes for managing your UI component lifecycle and handling data persistence.
    - Data Binding - Declaratively bind observable data to UI elements.
    - Lifecycles - Create a UI that automatically responds to lifecycle events.
    - LiveData - Build data objects that notify views when the underlying database changes.
    - Navigation - Handle everything needed for in-app navigation.
    - Room - Access your app's SQLite database with in-app objects and compile-time checks.
    - ViewModel - Store UI-related data that isn't destroyed on app rotations. Easily schedule asynchronous tasks for optimal execution.

2. Third party and miscellaneous libraries
    - Glide for image loading
    - Dagger: for dependency injection
    - Kotlin Coroutines with Flow for managing background threads with simplified code and reducing needs for callbacks
