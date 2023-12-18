# Android Expense Management App

## Overview
This Android application is specialized for managing and reporting expenses. It utilizes the Model-View-ViewModel (MVVM) architecture to ensure clear separation of concerns, thereby enhancing maintainability and testability.

## Environment

* IDE：Android Studio Hedgehog | 2023.1.1
* Kotlin：1.9.0
* Java：17
* Gradle：8.2
* minSdk：26
* targetSdk：34

## Architecture
The application is structured into two primary directories: `domain` and `app`, adhering to the principles of Clean Architecture.

### Domain Layer (`/domain`)
Defines the business logic with entity definitions and use cases encapsulating the business rules. This layer can reference the `/app` package but is designed to remain framework agnostic for ease of testing.

#### Components:
- Use Cases: `FindDepartmentsUseCase`, `InsertHistoryUseCase`
- Models: `SegmentType`
- ViewModel: `SearchViewModel`

### App Layer (`/app`)
Contains Android framework-dependent code, including DAOs, database setup, and DI modules. This layer should not reference the `/domain` package to maintain unidirectional flow of dependencies.

#### Components:
- Data: `DepartmentDao`, `HistoryDao`, `SettingDao`
- Database: `AppDatabase`, `DatabaseWorker`
- DI Modules: `DatabaseModule`, `RepositoryModule`, `UseCaseModule`
- UI: Various UI components like `BaseDatePickerDialog`, `BaseInput`, etc.

## Package Rules
- `/domain` can reference `/app` if needed.
- `/app` must not reference `/domain` to keep architecture clean.




