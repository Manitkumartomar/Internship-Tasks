<h1 align="center">🌟 Spring-Boot-Microservices-Banking-Application 🌟</h1>

<p align="center"> Welcome to the Spring Boot Microservices Banking Application! This project showcases a core banking system built using Spring Boot microservices.</p>


<h2 align="center"> 📋 Table of Contents</h2>

- [🔍 Service Registry](#-service-registry)
- [🌐 API Gateway Service](#-api-gateway-service)
- [🌐 Authentication Service](#-authentication-service)
- [👤 User Service](#-user-service)
- [💼 Account Service](#-account-service)
- [💳 Transaction Service](#-transaction-service)
- [🌐 Config Server](#-config-server)
  
<h2 align="center">🚀 Functionalities</h2>

### 🔍 Service Registry
- Manages microservices for easy discovery and registration.

### 🌐 API Gateway Service
- Provides a single entry point to the microservices and handles routing.

### 🌐 Authentication Service
- **JWT Token Generation:** Generate a JWT token upon successful user authentication.
- **Token Validation:** Validate JWT token for secure access to other microservices.

### 👤 User Service
- **Registration of User:** Register users with the banking system.
- **Reading All Users:** Retrieve a list of all registered users.
- **Reading User by ID:** Retrieve user details based on their unique ID.
- **Reading User by account Id:** Reterive user details based on their account ID.
- **Update User Status**: Modify the user detials based on the user request.

### 💼 Account Service
- **Creating an Account:** Users can create bank accounts.
- **Gettting all Accounts:** Users can check all accounts.
- **Read Account by AccountId**: Read the account details based on the account Id that are active.
- **Read transaction for account:**: Reterive transactions that are made by specific account.

### 💳 Transaction Service
- **Make a transaction:** Make transaction such as deposit of amount to a bank account or withdraw money from the account.
- **Check Transaction with AccountId:** Users can check transaction with their account Id.

### 🌐 Config Server
- Provides the functionality to read the common configuration of eureka Server from github account.

<h2 align="center">🚀 Getting Started</h2>
To get started with this Spring Boot Microservices Banking Application, follow these steps:

1. Clone the repository to your local machine.
2. Set up and configure the individual microservices (User Service, Account Service, Authentication Service, Transaction Service).
3. Start the microservices in the desired order (Service Registry, API Gateway Service, User Service, Account Service, Authentication Service, Transaction Service, Config Server).
4. Explore the API documentation to understand how to make requests to each microservice.

<h2 align="center">🤝 Contribution</h2>

Contributions to this project are welcome! Feel free to open issues, submit pull requests, or provide feedback to enhance the functionality and usability of this banking application.

Let's build a robust and efficient banking system together using Spring Boot microservices!

Happy Banking! 🏦💰


<h2 align="center">📞 Contact Information</h2>
If you have any questions, feedback, or need assistance with this project, please feel free to reach out to me:

[![WhatsApp](https://img.shields.io/badge/WhatsApp-25D366?style=for-the-badge&logo=whatsapp&logoColor=white)](https://wa.me/8126288733)
[![GMAIL](https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:manitkumar8979@gmail.com)

I appreciate your interest in my project and look forward to hearing from you. Happy coding!