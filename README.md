# Spring Boot Grocery Inventory API

This is a robust RESTful API for managing a grocery inventory system, built with Spring Boot.

It allows for adding new items, deleting them by name, and searching for items based on various criteria like category, purchase date, and supplier. The project includes data validation, DTOs, and a full API layer with a corresponding service layer for business logic.

## Key Features

* **Add New Items:** Create and save new grocery items to the inventory.
* **Delete Items by Name:** Remove an item from the inventory using its unique name.
* **Search and Filter:**
    * Fetch all items belonging to a specific category (e.g., `DAIRY`, `FRUIT`).
    * Fetch all items purchased on a specific date.
    * Fetch all items from a specific supplier.
* **Data Validation:** Uses Jakarta Bean Validation (`@NotBlank`, `@Min`, `@DecimalMin`) to ensure all data is valid before it's saved.
* **DTO Pattern:** Uses a `GroceryDTO` to safely transfer data between the client and the application.

## Tech Stack

* **Framework:** Spring Boot
* **Language:** Java 21
* **Database:** Spring Data JPA with MySQL
* **API Documentation:** SpringDoc (Swagger UI) - provides live, interactive API docs.
* **Tools:**
    * Lombok
    * ModelMapper (for DTO <-> Entity conversion)
    * Maven

## How to Run This Project

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/RishiDusane/grocery-app-springboot.git](https://github.com/RishiDusane/grocery-app-springboot.git)
    cd grocery-app-springboot
    ```

2.  **Configure the Database:**
    * Open `src/main/resources/application.properties`.
    * Ensure you have a MySQL database named `grocerydb`.
    * Update `spring.datasource.username` and `spring.datasource.password` to match your local MySQL credentials.
    * The application will automatically create the `Grocery` table on startup (`spring.jpa.hibernate.ddl-auto=update`).

3.  **Run the application:**
    You can run the application using the Maven wrapper:
    ```bash
    ./mvnw spring-boot:run
    ```
    Or, you can run the `main` method in `GroceryApplication.java` from your IDE.

---

## API Documentation (Swagger UI)

This project includes interactive API documentation. Once the application is running, you can view and test all the endpoints by visiting:

[**http://localhost:8080/swagger-ui.html**](http://localhost:8080/swagger-ui.html)

## API Endpoints

All endpoints are prefixed with `/api`.

### `POST /api/grocery`

* **Description:** Adds a new grocery item to the inventory.
* **Body (JSON):**
    ```json
    {
      "itemName": "Organic Milk",
      "category": "DAIRY",
      "quantity": 2,
      "price": 3.99,
      "purchaseDate": "2025-11-16",
      "supplierName": "FarmFresh"
    }
    ```

### `DELETE /api/items/{itemName}`

* **Description:** Deletes a grocery item by its unique name.
* **Example:** `DELETE /api/items/Organic Milk`

### `GET /api/items/category/{categoryName}`

* **Description:** Retrieves a list of all items in a specific category.
* **Example:** `GET /api/items/category/DAIRY`

### `GET /api/items/date/{purchaseDate}`

* **Description:** Retrieves a list of all items purchased on a specific date.
* **Example:** `GET /api/items/date/2025-11-16`

### `GET /api/items/supplier/{supplierName}`

* **Description:** Retrieves a list of all items from a specific supplier.
* **Example:** `GET /api/items/supplier/FarmFresh`
