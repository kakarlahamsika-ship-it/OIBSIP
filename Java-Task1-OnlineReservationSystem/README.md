# 🚆 Online Railway Reservation System

A Java-based Online Railway Reservation System developed using **Java Swing, JDBC, and SQLite**. This application allows users to book railway tickets, check PNR status, view ticket details, print tickets, and cancel reservations through a graphical user interface.

## 📌 Project Overview

The Online Railway Reservation System is a desktop application designed to simplify railway ticket reservation operations. It provides an easy-to-use interface for managing passenger information and railway bookings.

## ✨ Features

- 🔐 **Admin Login** – Secure login screen with demo credentials.
- 🚆 **Train Search** – Search trains using train numbers.
- 🎫 **Ticket Booking** – Book tickets by entering passenger and journey details.
- 📅 **Date Selection** – Select journey dates using a popup calendar.
- 🔢 **PNR Generation** – Generate a unique PNR number for every booking.
- 🔍 **PNR Status** – Retrieve booking details using the PNR number.
- ❌ **Ticket Cancellation** – Cancel existing reservations.
- 🖨️ **Print Ticket** – Print ticket confirmation details.

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Core programming language |
| Java Swing | Graphical User Interface |
| JDBC | Database connectivity |
| SQLite | Database management |
| Maven | Dependency management and project build |
| JCalendar | Popup date selection |

## 📂 Project Structure

```text
OnlineReservationSystem/
│
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── reservation/
│                   ├── App.java
│                   ├── DBConnection.java
│                   ├── LoginFrame.java
│                   ├── DashboardFrame.java
│                   ├── ReservationFrame.java
│                   ├── PNRFrame.java
│                   ├── CancellationFrame.java
│                   └── TicketFrame.java
│
├── pom.xml
├── README.md
└── .gitignore
```

## ⚙️ Installation and Setup

### Prerequisites

- Java JDK 17 or later (use a compatible JDK for the project).
- Apache Maven.
- Git.
- VS Code or any Java IDE.

### Step 1: Clone the Repository

```bash
git clone https://github.com/YOUR-USERNAME/Online-Railway-Reservation-System.git
```

### Step 2: Open the Project

```bash
cd Online-Railway-Reservation-System
```

### Step 3: Build the Project

```bash
mvn clean compile
```

### Step 4: Run the Application

Build the dependency classpath:

```powershell
mvn dependency:build-classpath "-Dmdep.outputFile=classpath.txt"
```

Run the application in Windows PowerShell:

```powershell
$cp = "target\classes;" + (Get-Content .\classpath.txt -Raw)
java -cp $cp com.reservation.App
```

## 🔑 Demo Login Credentials

| Field | Value |
|---|---|
| Username | admin |
| Password | admin123 |

**Note:** These are demonstration credentials, not production-grade authentication.

## 🚆 Sample Train Details

| Train Number | Train Name |
|---|---|
| 12727 | Godavari Express |
| 12728 | Godavari Express |

## 🗄️ Database Details

The application uses SQLite to store railway reservation information.

**Tables:**
- `trains` – Stores train numbers and train names.
- `reservations` – Stores passenger details, PNR, train information, journey date, source, destination, and class.

The database is initialized automatically when the application starts.

## 🎯 Learning Outcomes

Through this project, I gained practical experience in:

- Java object-oriented programming.
- Developing desktop applications using Java Swing.
- Connecting Java applications with SQLite using JDBC.
- Performing database operations using SQL.
- Implementing CRUD operations.
- Managing project dependencies using Maven.
- Building a user-friendly graphical interface.

## 🔮 Future Enhancements

- User registration and authentication.
- Real-time train availability.
- Seat availability management.
- Fare calculation.
- Waiting list management.
- Online payment integration.
- Admin dashboard for train management.

## 👨‍💻 Author

**Your Name**

B.Tech – Information Technology

## 📄 License

This project is developed for educational purposes.
