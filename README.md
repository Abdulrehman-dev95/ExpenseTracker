# 💰 Expense Tracker

> A simple, modern Android expense management application built with Jetpack Compose for tracking income and expenses.

## 📱 App Screenshots

| Home Screen                                          | Add Transaction                                    | Graph Screen                                       |
| -------------------------------------------------- | -------------------------------------------------- | -------------------------------------------------- |
| <img src="app/screenshots/Home Screen.jpeg" width="250"/> | <img src="app/screenshots/Add Espense Screen.jpeg" width="250"/> | <img src="app/screenshots/Graph Screen.jpeg" width="250"/> |

## 📝 Description

**Expense Tracker** is a modern Android application designed to help users record and monitor their personal income and expenses.

The application provides a simple financial dashboard where users can view their balance, transactions, and analyze their spending through visual summaries.

The project focuses on building a reactive Android UI with **Jetpack Compose** while practicing local data management, state handling, and the MVVM pattern.

## ✨ Key Features

* 💵 **Income Management** – Record income transactions
* 💸 **Expense Management** – Record daily expenses
* 📊 **Financial Summary** – View balance and financial statistics
* 📋 **Transaction History** – Review recorded transactions
* 📈 **Visual Graphs** – Analyze financial activity visually
* 🎨 **Modern UI** – Built with Jetpack Compose
* ⚡ **Reactive Updates** – UI updates automatically when data changes

## 🛠️ Tech Stack

| Technology           | Purpose                      |
| -------------------- | ---------------------------- |
| **Kotlin**           | Primary programming language |
| **Jetpack Compose**  | Modern declarative UI        |
| **Room**             | Local database               |
| **Coroutines**       | Asynchronous operations      |
| **Flow / StateFlow** | Reactive data streams        |
| **MVVM**             | Presentation architecture    |

## ⚙️ Architecture

The application is structured around the separation of UI, state management, and data access.

```text
UI - Jetpack Compose
        │
        ▼
ViewModel
        │
        ▼
Repository
        │
        ▼
Local Data Source
   ┌────┴
   ▼         
 Room     
```

## 🚀 Getting Started

### Prerequisites

* Android Studio
* JDK compatible with the project
* Android SDK

### Installation

1. Clone the repository:

```bash
git clone https://github.com/Abdulrehman-dev95/ExpenseTracker.git
```

2. Open the project in Android Studio.

3. Sync the Gradle project.

4. Build the application.

5. Run it on an Android device or emulator.

## 🤝 Contributing

Contributions and suggestions are welcome.

Feel free to open an issue or submit a pull request.

## 📄 License

This project is available under the MIT License.
