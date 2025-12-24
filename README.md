Idle Tracker Application (Java + C++ DLL Integration)

## Overview
Idle Tracker is a Java + Spring Boot + JavaFX desktop application that monitors system idle activity using a native C++ DLL on Windows.

The application demonstrates:
    -System-level thinking
    -Java ↔ C++ native integration
    -Background task execution
    -UI + backend separation
    -Clean shutdown handling

This project was built as part of a technical assignment focusing on working logic and clarity rather than UI design.

## Tech Stack
Layer	          Technology
Language	      Java 17+
Backend	          Spring Boot (Maven)
UI	              JavaFX
Native Code	      C++ (Windows DLL)
OS	              Windows
Java ↔ C++	      JNA (Java Native Access)

## Project Structure
idletracker/
├── pom.xml
├── IdleTracker.dll
├── README.md
├── cpp/
│   └── IdleTracker.cpp
└── src/main/java/com/example/idletracker
    ├── IdleTrackerApplication.java
    ├── nativeapi/
    │   └── IdleTrackerLibrary.java
    └── ui/
        ├── JavaFxApp.java
        ├── LoginController.java
        └── DashboardController.java
└── src/main/resources
    └── fxml/
        ├── login.fxml
        └── dashboard.fxml

## Functional Flow
1️⃣ Application Startup
    -Application starts using Spring Boot
    -Launches JavaFX UI
    -Displays Login Screen

2️⃣ Login Page
    -Username and Password fields
    -Credentials are hardcoded
    -On successful login → Dashboard

## Hardcoded Credentials
Username: admin
Password: admin123

3️⃣ Dashboard
Dashboard contains:
    -Timer
        -Starts automatically after login
        -Runs continuously until application closes
    -Start Button
        -Starts idle tracking (C++ DLL)
    -Stop Button
        -Stops idle tracking only

(Timer is intentionally independent of Start/Stop buttons.)

4️⃣ C++ DLL Integration
When Start is clicked:
    -Java calls a C++ DLL
    -Passes an integer value (seconds)
    -DLL checks system idle state using Windows APIs
Windows API Used
    -GetLastInputInfo
If system becomes idle within given time:
    -DLL returns true to Java

5️⃣ Idle Notification
When idle is detected:
    -JavaFX shows a system notification

📢 Message
    Desktop is in idle state

6️⃣ API Calls
After detecting idle:
    -Java sends two REST API calls

## Request Payload (Json)
{
"userEmailId": "test@example.com",
"idleState": true
}

## Endpoints (Dummy / Mock)
    -HTTP: http://example.com/api/idle
    -HTTPS: https://example.com/api/idle
Endpoints are logged only; no real request is sent.

## Java ↔ C++ Communication
    -Communication is implemented using JNA
    -Java loads IdleTracker.dll directly
    -No JNI boilerplate required
    -DLL method is mapped as a Java interface

## Prerequisites
System Requirements
    -Windows OS
    -Java 17+
    -Maven
    -Visual Studio (C++ Desktop Development)
    -Git

## Steps to Build C++ DLL
1. Open Visual Studio
2. Create:
   New Project → Dynamic Link Library (DLL)
3. Name project: IdleTracker 
4. Replace source code with provided IdleTracker.cpp 
5. Set:
  -Configuration: Release
  -Platform: x64
6. Build:
   -Build → Build Solution
7. Copy generated:
   -IdleTracker.dll
8. Paste into Java project root:
   -idletracker/IdleTracker.dll

## Steps to Run Java Application
    run directly from IDE:
       1. right-click on IdleTrackerApplication.java
       2. run

## Application Shutdown Behavior
    -Closing the JavaFX window:
        -Stops background threads
        -Shuts down JVM cleanly
    -No zombie Java processes remain

## Important Design Decisions
    -Timer runs independently of idle tracking
    -UI thread is never blocked
    -Native calls run in background executor
    -Clean shutdown implemented via Application.stop()
    -Clear separation of concerns

## What This Project Demonstrates
    -System-level design thinking
    -Native OS integration
    -JavaFX lifecycle handling
    -Thread safety
    -Clean code organization
    -Real-world desktop application flow

## Notes
    -UI design is intentionally minimal
    -APIs are mocked as per instructions
    -Focus is on logic, integration, and clarity

## Author
Subhanshu Chaurasiya
BTech CSE
Backend / Java Developer