# Task Manager CLI

A simple command-line interface (CLI) application for managing tasks. You can add, update, delete, mark, and list tasks directly from your terminal.

---

## Features

* **Add a Task:** Add a new task with a description.
* **Update a Task:** Update the description of an existing task by its ID.
* **Delete a Task:** Remove a task by its ID.
* **Mark Status:** Mark a task as `in-progress` or `done`.
* **List Tasks:** List all tasks or filter them by status (`todo`, `in-progress`, `done`).

---

## Requirements

* **Java 21** or higher installed on your system.

---

## Installation

```bash
git clone https://github.com/glakszymon/TaskManager.git
cd TaskManager
javac -d out src/Main.java src/Models/*.java

```

---

## Usage

```bash
# Adding a new task
java -cp out Main add "Buy groceries"

# Updating a task
java -cp out Main update 0 "Buy groceries and cook dinner"

# Marking status
java -cp out Main mark-in-progress 0
java -cp out Main mark-done 0

# Listing tasks
java -cp out Main list
java -cp out Main list todo
java -cp out Main list in-progress
java -cp out Main list done

# Deleting a task
java -cp out Main delete 0

# Help
java -cp out Main --help

```

https://roadmap.sh/projects/task-tracker