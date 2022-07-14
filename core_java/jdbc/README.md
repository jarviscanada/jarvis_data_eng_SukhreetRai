# Introduction
This application utilizes the Java Database Connection API to a query a PostgreSQL database that has been provisioned by Docker. The dependencies required for this project are managed using Apache Maven. In addition, DBeaver was used to generate EntityRelationship diagram. A Docker container running PostgreSQL was used to query the database directly from the command line and store the collected data on specified volume. This application can only perform CRUD operations such as Create, Read, Update, and Delete on the customers stored in the database and display order details from orders. The technolgies such as Java, PostgreSQL, Docker, Maven, and DBeaver were used in this implementation. 

# Implementaiton
## ER Diagram
![image](../assets/ER diagram.png)

## Design Patterns
The Data Access Object pattern is used as a Design Pattern in this project. DAO provides an abstraction of data persistence and allows us to separate the actual accessing of information from the "business logic" of the rest of the application. The Repository pattern has a similar goal, but it functions primarily as an abstraction of a collection rather than as an abstraction of the data store and as such is more closely related to the business logic of the application. DAO executes transactions against a given database using CRUD operations. Respository is best if database is highly normalized. Moreover it only focuses on single table access per class. 

# Test
The database was accessed using Docker container running a PostgreSQL image. Database was populated with number of tables withsample data via several SQL files. A new record was added, displayed, updated, and deleted to ensure that CRUD functionality is operating successfully. 
