# Speech Analysis Mini Project

## Overview

This project is a Java-based application that captures user speech, converts it into text, analyzes the speech for fluency (fumbles), performs sentiment analysis, and stores the results in a database.

The system integrates multiple components including audio recording, speech-to-text conversion using Vosk, natural language processing, and database connectivity.

---

## Features

* Record audio input from the user
* Convert speech to text using Vosk API
* Detect fumbles in speech
* Perform sentiment analysis on recognized text
* Store session data in MySQL database
* Modular and scalable architecture

---

## Technologies Used

* Java
* Maven
* MySQL
* Vosk Speech Recognition API
* JDBC (Java Database Connectivity)

---

## Project Structure

```
src/
 └── com.mpj.miniproj
     ├── audio
     │    └── AudioRecorder.java
     ├── speech
     │    └── SpeechToTextService.java
     ├── sentiment
     │    └── SentimentAnalyzer.java
     ├── database
     │    ├── DBConnection.java
     │    └── SessionDAO.java
     ├── ui
     │    └── Main.java
```

---
---

## 🏗️ System Architecture

Frontend (UI) → Spring Boot REST API → Processing Modules (Speech + NLP) → MySQL Database  

The system follows a layered architecture ensuring modularity, scalability, and easy integration of components.

## How It Works

1. User starts the application.
2. Audio is recorded using the AudioRecorder module.
3. The recorded audio is passed to Vosk for speech recognition.
4. The converted text is processed:

   * Fumble detection
   * Sentiment analysis
5. Results are stored in the MySQL database.
6. Output is displayed to the user.

---

## Database Setup

1. Install MySQL and start the server.
2. Create database:

```sql
CREATE DATABASE speakstreak;
```

3. Create table:

```sql
CREATE TABLE sessions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    text TEXT,
    fumbles INT,
    sentiment VARCHAR(50)
);
```

4. Update credentials in `DBConnection.java`:

```java
private static final String URL = "jdbc:mysql://localhost:3306/speakstreak";
private static final String USER = "root";
private static final String PASSWORD = "your_password";
```

---

## Vosk Setup

1. Download a Vosk model (e.g., small English model).
2. Extract the model folder.
3. Place it inside the project directory.
4. Update path in code:

```java
Model model = new Model("model");
```

---

## How to Run

1. Open project in IntelliJ IDEA.
2. Ensure Maven dependencies are synced.
3. Connect MySQL database.
4. Run `Main.java`.
5. Press ENTER and start speaking.

---

## Output Example

* Recognized Speech: "Hello this is a test"
* Fumbles Detected: 2
* Sentiment: Positive

---

## Future Enhancements

* Add graphical user interface (GUI)
* Improve speech accuracy with better models
* Add real-time speech processing
* Store audio files in database or cloud
* Advanced NLP analysis

---

## Author

Vani Sharma
Aamena Sheikh 

## License

This project is for academic and learning purposes only.
