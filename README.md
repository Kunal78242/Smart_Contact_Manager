# Smart_Contact_Manager
Smart Contact Manager: A secure Spring app for personal contact management. Features user registration, encrypted passwords, and private dashboards with full contact CRUD, including JPEG profile pictures. Data is completely isolated per user. Built with Spring Boot, Security, Data JPA, and Thymeleaf for a professional, responsive experience.

🧠 Smart Contact Manager
A Full-Stack Intelligent Contact Management System with Secure User Management
Built with Java Spring technologies, this application provides secure user authentication and allows each registered user to independently manage their contacts with high accuracy. Supports text entries and JPEG profile pictures for contacts.

✨ Features
🔐 User Management
Create New Users – Register with unique username and secure password

Spring Security – Password encryption, secure authentication, and role-based access

Personalized Dashboards – Each user accesses only their own contacts

📇 Contact Management
Save Contacts – Store contact details (name, phone, email, etc.)

JPEG Support – Upload and save contact profile pictures

CRUD Operations – Each user can:

View their saved contacts

Update contact information

Delete unwanted contacts

Change Contact Profile Picture via update functionality

🔒 Security Features
Secure password storage with encryption

Session management and authentication

Protected endpoints and user data isolation

🔐 Security Implementation
Spring Security Features Used:

UserDetailsService for authentication

BCryptPasswordEncoder for password encryption

HttpSecurity configuration for URL protection

AuthenticationManager for login processing

CSRF protection enabled

Session management with timeout

🪧 Protected Endpoints:

/user/dashboard – Authenticated users only

/contacts/** – User-specific contact operations

/admin/** – Admin-only access (if implemented)

🛠️ Tech Stack
Spring Framework
Spring Boot – Rapid application development

Spring Security – Authentication and authorization

Spring MVC – Web layer architecture

Spring Data JPA – Database operations

Spring Validation – Input validation

Frontend & UI
Thymeleaf – Server-side templating engine

Bootstrap – Responsive design

HTML5, CSS3, JavaScript

⚙️Database & Tools
MySQL/PostgreSQL – Data persistence

Hibernate – ORM implementation

Maven/Gradle – Build automation

BCrypt – Password encryption

Spring Tool Suite (STS) / IntelliJ IDEA – Development environment

🎯 Key Benefits
✅ Secure User System – Individual login with Spring Security protection
✅ Personal Contact Space – Each user manages their own contacts privately
✅ Profile Picture Management – Upload/update JPEG images for contacts
✅ Full CRUD Functionality – Complete control over contact data
✅ High Accuracy – Reliable data handling and retrieval
✅ Practical & Useful – Solves real-world contact organization needs

📁 Project Structure
smart-contact-manager/
├── src/main/java/com/smart/contactmanager/
│   ├── config/
│   │   ├── SecurityConfig.java        # Spring Security configuration
│   │   └── CustomUserDetails.java     # User details service
│   ├── controller/
│   │   ├── HomeController.java        # Main controller
│   │   ├── UserController.java        # User management
│   │   └── ContactController.java     # Contact CRUD operations
│   ├── dao/
│   │   ├── UserRepository.java        # User data access
│   │   └── ContactRepository.java     # Contact data access
│   ├── entity/
│   │   ├── User.java                  # User entity
│   │   └── Contact.java               # Contact entity
│   ├── helper/
│   │   └── FileUploadHelper.java      # File/image upload handling
│   └── service/
│       ├── UserService.java           # User business logic
│       └── ContactService.java        # Contact business logic
├── src/main/resources/
│   ├── static/
│   │   ├── css/                       # Stylesheets
│   │   ├── js/                        # JavaScript files
│   │   └── images/                    # Uploaded images stored here
│   ├── templates/                     # Thymeleaf HTML templates
│   └── application.properties         # Configuration file
└── pom.xml                            # Maven dependencies


📱 Screenshots

📌 Login Page – Secure authentication interface

<img width="1920" height="1080" alt="Screenshot 2026-01-21 145030" src="https://github.com/user-attachments/assets/3fedafe3-26c7-4dcc-9cad-7c20ff422aa9" />


📌 User Registration – New account creation

<img width="1920" height="1080" alt="Screenshot 2026-01-21 144604" src="https://github.com/user-attachments/assets/611785dc-0c7b-4dbc-acd4-3a4028e5bd7a" />


📌 Contact Dashboard – All contacts view

<img width="1920" height="1080" alt="Screenshot 2026-01-21 144730" src="https://github.com/user-attachments/assets/a3744d95-d4ed-480d-a22f-622673500723" />


📌 Add Contact Form – With image upload option

<img width="1920" height="1080" alt="Screenshot 2026-01-21 144634" src="https://github.com/user-attachments/assets/9b6ab4c6-fa25-4b44-af64-30fdec03a5a5" />


📌 Contact Details – With profile picture display

<img width="1920" height="1080" alt="Screenshot 2026-01-21 144800" src="https://github.com/user-attachments/assets/514c4c39-4e5b-4188-8e45-cbdf57a86d11" />


📌 Update Contact – Edit form with current image

<img width="1920" height="1080" alt="Screenshot 2026-01-21 144826" src="https://github.com/user-attachments/assets/bbead6fc-ba99-4a52-af63-87f181e9bab6" />



<img width="1920" height="1080" alt="Screenshot 2026-01-21 144833" src="https://github.com/user-attachments/assets/bead1344-e92e-4631-b5da-e0b238482ff7" />



