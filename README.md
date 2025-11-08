# 💰 Financify – Personal Finance Assistant  
*A personal finance tracker built with JavaFX and MySQL.*

Financify is a modern desktop finance tracker built with **JavaFX** and **MySQL**.  
It helps users record, categorize, and visualize income and expenses with an intuitive and clean UI.

---

## 🚀 Features

- 💸 Add, view, and delete income or expense transactions  
- 📊 Real-time Pie Chart analytics for spending categories  
- 🗃️ Persistent data storage using MySQL  
- 🧱 MVC + DAO architecture for clean separation of logic  
- ⚙️ Built with Maven for easy setup and dependency management  
- 🎨 JavaFX-based modern UI  

---

## 🧰 Built With

![Java](https://img.shields.io/badge/Java-17-orange?logo=java)
![JavaFX](https://img.shields.io/badge/JavaFX-UI-blue)
![MySQL](https://img.shields.io/badge/MySQL-Database-4479A1?logo=mysql)
![Maven](https://img.shields.io/badge/Maven-Build-lightgrey?logo=apachemaven)
![IntelliJ IDEA](https://img.shields.io/badge/IDE-IntelliJ%20IDEA-purple?logo=intellij-idea)

---

## 🛠️ Tech Stack

| Layer | Technology |
|--------|-------------|
| Language | Java 17 |
| UI | JavaFX |
| Database | MySQL |
| Database Access | JDBC |
| Build Tool | Maven |
| IDE | IntelliJ IDEA |

---

## ⚙️ Installation & Setup

### 1️⃣ Clone this repository
```bash
git clone https://github.com/anmoln-27/financify.git
```

### 2️⃣ Open in IntelliJ IDEA (or any IDE supporting Maven)

- Make sure you have **Java 17+** and **MySQL** installed.
- Maven dependencies will be downloaded automatically.

### 3️⃣ Configure your MySQL Database

Run this SQL in MySQL Workbench:
```sql
CREATE DATABASE financify;

USE financify;

CREATE TABLE transactions (
  id INT AUTO_INCREMENT PRIMARY KEY,
  type VARCHAR(10),
  category VARCHAR(50),
  amount DOUBLE,
  note VARCHAR(255),
  date DATE
);
```

### 4️⃣ Update Database Credentials

Open:
```
src/main/java/org/example/util/DBConnection.java
```

Edit your connection settings:
```java
private static final String URL = "jdbc:mysql://localhost:3306/financify";
private static final String USER = "root";
private static final String PASSWORD = "your_password";
```

### 5️⃣ Run the Application

Using Maven:
```bash
mvn javafx:run
```

Or directly from IntelliJ — right-click `Main.java` → **Run**.

---

## 📦 Build Executable JAR

To package Financify into a runnable `.jar`:
```bash
mvn clean package
```

The JAR file will be created in:
```
target/financify-1.0-SNAPSHOT-shaded.jar
```

You can run it anywhere using:
```bash
java -jar target/financify-1.0-SNAPSHOT-shaded.jar
```

---

## 🧠 Future Enhancements

- 🔍 Add filters (by date/category)
- 📤 Export transactions to CSV/PDF
- 👤 Add user login and authentication
- 🌙 Dark mode UI

---

## 👨‍💻 Author

**Anmol Nandwani**  
📧 anmolnandwani@gmail.com  
🔗 [GitHub Profile](https://github.com/anmoln-27)  
💼 [LinkedIn (optional)](https://linkedin.com/in/anmolnandwani)

---

⭐ **If you like this project, consider giving it a star on GitHub!**
