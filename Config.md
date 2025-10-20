#



## Project Overview

This is a Spring-Hibernate demo project showcasing basic CRUD operations with MySQL database integration. The project demonstrates:
- Spring Context configuration with annotations
- Hibernate ORM for entity mapping and database operations
- Transaction management with Spring's declarative transactions
- DAO pattern implementation with generic interface

## Development Commands

### Build and Compile
```bash
mvn compile
mvn package
```

### Run Tests
```bash
mvn test
```

### Run Applications
- Test Hibernate configuration: `mvn exec:java -Dexec.mainClass="com.example.TestHibernate"`
- Run product demo: `mvn exec:java -Dexec.mainClass="com.example.Presentation2"`
- Start web application: `mvn jetty:run` (accessible at http://localhost:8080/spring-hibernate-demo)

### Clean and Install
```bash
mvn clean
mvn install
```

## Architecture

### Core Components

**Configuration Layer (`util/`)**
- `HibernateConfig.java`: Spring configuration class that sets up DataSource, SessionFactory, and TransactionManager using properties from `application.properties`

**Entity Layer (`entities/`)**
- `Product.java`: JPA entity representing the Product table with auto-generated ID and @ManyToOne relationship to Category
- `Category.java`: JPA entity representing categories with @OneToMany relationship to Products

**Data Access Layer (`dao/` and `metier/`)**
- `IDao<T>`: Generic DAO interface defining standard CRUD operations
- `ProductDaoImpl`: Repository implementation using Hibernate SessionFactory with Spring transaction management
- `CategoryDaoImpl`: Repository implementation for Category entity operations

**Web Layer (`controller/`)**
- `ProductController`: Spring MVC controller handling Product CRUD operations via web interface
- `CategoryController`: Spring MVC controller handling Category CRUD operations via web interface
- `HomeController`: Simple controller for application home page

**View Layer (`webapp/WEB-INF/views/`)**
- JSP views for products and categories with CRUD functionality
- Bootstrap-styled responsive interface

**Application Layer**
- `TestHibernate.java`: Configuration verification utility
- `Presentation2.java`: Demo application showing product creation

### Database Configuration

The project connects to MySQL database using:
- Host: localhost:3307
- Database: base
- Username/Password: root/root
- Dialect: MySQL8Dialect
- DDL mode: update (auto-creates/updates schema)

Database connection properties are externalized in `src/main/resources/application.properties`.

### Key Technologies

- **Spring Framework 5.3.22**: Dependency injection, transaction management, and MVC framework
- **Spring MVC**: Web framework with JSP view resolution
- **Hibernate 5.6.12**: ORM and entity mapping
- **MySQL 8.0.29**: Database connectivity (H2 for tests)
- **Maven**: Build tool with Java 1.8 target and WAR packaging
- **JUnit 4.13.2**: Testing framework with Spring Test integration
- **Mockito 4.6.1**: Mocking framework for unit tests
- **Jetty**: Embedded web server for development

### Entity Relationships

- **Product** @ManyToOne **Category**: Each product belongs to one category
- **Category** @OneToMany **Product**: Each category can have multiple products
- Bidirectional relationship with helper methods for maintaining consistency

### Testing Strategy

- **Unit Tests**: Test DAO operations with H2 in-memory database
- **Integration Tests**: Test web controllers with MockMvc
- **Test Configuration**: Separate test configuration using H2 database
- All tests use Spring's transaction rollback for isolation

### Component Scanning

Spring scans packages: `com.example.dao`, `com.example.entities`, `com.example.metier`, `com.example.controller` for component registration.
