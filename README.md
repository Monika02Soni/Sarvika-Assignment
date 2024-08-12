API Endpoints
You can interact with the Pet API using the following endpoints:

List All Pets
- Request: GET http://localhost:8080/pets
- Description: Retrieves a list of all pets.

Get Pet by ID
- Request: GET http://localhost:8080/pets/{id}
- Description: Retrieves details of a pet by its ID.
- Example: GET http://localhost:8080/pets/8

List Pets by Species
- Request: GET http://localhost:8080/pets?species={species}
- Description: Retrieves a list of pets filtered by species.
- Example: GET http://localhost:8080/pets?species=cat

Add a Pet
- Request: POST http://localhost:8080/pets
- Description: Adds a new pet to the database.
  
Edit a Pet
- Request: PUT http://localhost:8080/pets/{id}
- Description: Updates details of an existing pet.
- Example: PUT http://localhost:8080/pets/8
  
Add an Event to a Pet
- Request: POST http://localhost:8080/pets/{id}/events
- Description: Adds an event associated with a specific pet.
- Example: POST http://localhost:8080/pets/10/events
  
Delete a Pet
- Request: DELETE http://localhost:8080/pets/{id}
- Description: Removes a pet from the database.
- Example: DELETE http://localhost:8080/pets/8
