Spring Boot REST API with an H2 in-memory database. 
Exposes endpoints for users, accounts, and transactions.

Quick start

Requirements: Java 17+
Run: ./mvnw spring-boot:run
Base URL: http://localhost:8080

API endpoints

Accounts (/api/accounts)

GET /{userId} - List all accounts for the given user. Each account's initialBalance in the response is the computed balance (initial balance plus or minus transactions).

POST - Create an account. Body: userId, accountName, initialBalance. Returns the created account. Fails if the user does not exist or the account name already exists for that user.

Example POST body: { "userId": 1, "accountName": "Checking", "initialBalance": 1000.50 }

Transactions (/api/transactions)

GET /{accountId} - List all transactions for the given account.

POST - Create a transaction. Body: accountId, amount, date, description, type ("EXPENSE" or "INCOME", case-insensitive). Returns the created transaction. Fails if the account does not exist.

Example POST body: { "accountId": 1, "amount": 50.25, "date": "2025-01-31", "description": "Coffee", "type": "expense" }

Error responses

400 - Validation or business rule (e.g. duplicate account name, invalid type).
404 - User or account not found when required by the request.

Errors return a JSON body with timestamp, status, error, message, and optionally fieldErrors.

Database schema

H2 in-memory DB; tables are created from JPA entities.

user
| id     | Long (Primary Key)
| name   | String 

account
| id             | Long (Primary Key)
| account_name   | String
| initial_balance| BigDecimal
| user_id        | Long (Foreign Key for user table)

transactions
| id         | Long (Primary Key)
| account_id | Long (Foreign Key for account table)
| date       | Date   
| description| String
| amount     | BigDecimal
| type       | String
