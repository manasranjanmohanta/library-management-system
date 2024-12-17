### **Project Name**: Library Management System (REST API)

---

### **Description**:

Develop a backend API for a Library Management System to manage books, authors, and borrowers. This project will allow you to practice CRUD operations, relationships, and validation using Spring Boot.

---

### **Features**:

#### 1. **Book Management**:

- Add a new book.
- Update book details.
- Delete a book.
- Fetch details of a specific book.
- List all books with optional filters (e.g., by author, genre, availability).

#### 2. **Author Management**:

- Add a new author.
- Update author details.
- Delete an author.
- Fetch details of a specific author.
- List all authors.

#### 3. **Borrower Management**:

- Add a new borrower.
- Update borrower details.
- Delete a borrower.
- Fetch details of a specific borrower.
- List all borrowers.

#### 4. **Borrowing System**:

- Borrow a book (mark it as borrowed by a borrower).
- Return a borrowed book (mark it as available).
- Track overdue books (calculate due dates and penalties).

---

### **Tech Stack**:

- **Backend**: Spring Boot (REST API)
- **Database**: MySQL or PostgreSQL
- **ORM**: Hibernate JPA
- **Tools**: Postman, Swagger for API documentation
- **Testing**: JUnit, Mockito

---

### **Entities**:

1. **Book**:
    
    - `id` (Primary Key)
    - `title`
    - `isbn`
    - `authorId` (Foreign Key to Author)
    - `genre`
    - `available` (boolean)
    - `borrowedBy` (Foreign Key to Borrower, nullable)
    - `borrowedDate`
    - `dueDate`
2. **Author**:
    
    - `id` (Primary Key)
    - `name`
    - `email`
3. **Borrower**:
    
    - `id` (Primary Key)
    - `name`
    - `email`
    - `phone`

---

### **Endpoints**:

#### **Books**:

- `POST /api/books` – Add a new book.
- `GET /api/books` – Get a list of books with optional filters.
- `GET /api/books/{id}` – Get details of a specific book.
- `PUT /api/books/{id}` – Update book details.
- `DELETE /api/books/{id}` – Delete a book.

#### **Authors**:

- `POST /api/authors` – Add a new author.
- `GET /api/authors` – Get a list of authors.
- `GET /api/authors/{id}` – Get details of a specific author.
- `PUT /api/authors/{id}` – Update author details.
- `DELETE /api/authors/{id}` – Delete an author.

#### **Borrowers**:

- `POST /api/borrowers` – Add a new borrower.
- `GET /api/borrowers` – Get a list of borrowers.
- `GET /api/borrowers/{id}` – Get details of a specific borrower.
- `PUT /api/borrowers/{id}` – Update borrower details.
- `DELETE /api/borrowers/{id}` – Delete a borrower.

#### **Borrowing**:

- `POST /api/borrow/{bookId}` – Borrow a book.
- `POST /api/return/{bookId}` – Return a borrowed book.
- `GET /api/overdue` – Get a list of overdue books.

---