
# ProgApplication3

---

## Features

### 1. User Registration & Login
- Register a user with `name`, `username`, and `password`.
- Login with exact username and password.
- GUI dialogs via `JOptionPane`.

### 2. Messaging
- Send, Disregard, or Store messages.
- Validate recipient format (`+27xxxxxxxxx`) and message length (≤250 characters).
- Display sent messages, longest messages, search by ID or recipient.
- Delete messages by hash.
- Generate full sent message report.

### 3. JSON Storage
- Stored messages are appended to `messages.json`.
- On start, loads stored messages if JSON exists; otherwise uses sample JSON.
- Maintains valid JSON format on append.

### 4. Tests
- JUnit 5 tests included for `MessageManager`:
  - Adding messages
  - JSON load/append
  - Sending and storing messages
  - Validations for recipients, message length, and actions

---

## How to Run

1. **Compile & Build**
   ```bash
   ant compile
