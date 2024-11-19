# Change Log

## [0.86.0] 2024-11-19

- Added CRUD operations for religion

## [0.85.0] 2024-10-23

- Created CRUD for books
- Added CRUD for chapters
- Added book reading display with all chapters per book

## [0.84.0] 2024-10-23

- Added languages and letters for each language

## [0.83.0] 2024-10-19

- Added sub-races for races
- Added CRUD for sub-races

## [0.82.0] 2024-10-18

- Implemented fully functional family tree
- Edited modal for Race entity in Angular
- Added checkboxes for Race records in Angular
- Corrected incorrect API endpoints

## [0.81.0] 2024-10-15

- Implemented design for Balkan family tree
- Created entity classes for family trees

## [0.80.0] 2024-10-12

- Adjusted module for family trees (Balkan)
- Added marital status for person details
- Added default image for created person
- Added API endpoint for person image

## [0.79.0] 2024-10-10

- Added text editor for biography
- Biography can now be edited separately through PatchMapping

## [0.78.0] 2024-10-10

- Finalized event addition to a timeline
- Added delete functionality for event node
- Added edit functionality for individual event node
- Added addition functionality to insert new nodes at particular indexes

## [0.77.0] 2024-10-08

- Correctly displayed timelines onto frontend

## [0.76.1] 2024-10-07

- Removed unnecessary console logging on frontend

## [0.76.0] 2024-10-07

- Separated Person and PersonDetails entities
- Added route for PersonDetails
- Displayed data on PersonDetails route
- Allowed data to be edited on PersonDetails route

## [0.75.0] 2024-05-22

- Added Timeline entity
- Refactored Pantheon entity

## [0.74.1] 2024-05-22

- Added Rank attribute to Person entity

## [0.74.0] 2024-05-22

- Refactored `Person.java` entity
- Deleted unwanted entity classes

## [0.73.0] 2024-05-1

- Added HTTP requests for services in politics package

## [0.72.0] 2024-05-1

- Added HTTP requests for all services except for those in politics package

## [0.71.0] 2024-04-30

- Added mapper and dto for everything except certain entities in politics package

## [0.70.0] 2024-04-28

- Added mapper and dto for Religion, Demon and Fae entities

## [0.69.1] 2024-04-24

- Added handler for NoSuchElementException in backend portion

## [0.69.0] 2024-04-24

- Added facade and mapper classes for Nation entity
- Added facade and mapper classes for Organization entity
- Added facade and mapper classes for Year entity
- Added dto class for Month entity
- Added nation and organization templates for frontend portion
- Added nation and organization services for frontend portion

## [0.68.0] 2024-04-22

- Added facade class for Person entity

## [0.67.0] 2024-04-17

- Added customizable familial tree

## [0.66.0] 2024-04-17

- Added organizational chart for frontend portion

## [0.65.0] 2024-04-17

- Added GoJS library
- Added RESTful web services for Event entity

## [0.64.0] 2024-04-17

- Added family tree on frontend portion
- Installed Balkan FamilyTreeJS library
- Refactored code in personMapper

## [0.63.0] 2024-04-14

- Edited PUT method for Person entity
- Added Event entity for timelines
- Added Timeline route for frontend

## [0.62.1] 2024-04-14

- Added modal elements to a module
- Changed css classes from modal to popup to avoid conflict with Bootstrap and Angular Material

## [0.62.0] 2024-04-14

- Updated PUT method for Person entity in backend
- Corrected exception handling methods to use the correct status error
- Added custom modal to avoid conflict between Angular Material modal and Bootstrap modal libraries

## [0.61.0] 2024-04-12

- Added dto pattern and mapper for Person entity
- Added Model Mapper dependency in pom.xml
- Updated version of Swagger to 2.3.0

## [0.60.0] 2024-04-08

- Added service class for Race entity in frontend portion
- Added component for Race entity in frontend portion
- Added ClosedAnswer enum in frontend portion

## [0.59.1] 2024-04-08

- Added PUT functionality for Race entity

## [0.59.0] 2024-03-29

- Created person HTML template
- Added CRUD operations for Person entity

## [0.58.0] 2024-03-29

- Finished adding all properties for all interfaces in frontend
- Deleted extra folder

## [0.57.0] 2024-03-29

- Added properties for interfaces in frontend

## [0.56.0] 2024-03-28

- Refactored enums in backend
- Created interfaces for all entities in frotend
- Created services for most entities in frontend
- Created all necessary enums in frontend

## [0.55.0] 2024-03-27

- Created filterByGender method in person.component.ts to use the PersonService observable
- Created modifyDay method that utilizes PUT HTTP request observable for the Day entity

## [0.54.0] 2024-03-27

- Edited name of package for religion to dogma
- Added all interfaces on frontend for society package
- Added service class on frontend for person entity
- Added filterByGender observable on frontend for person service

## [0.53.0] 2024-03-26

- Added folder for maps
- Loaded map on home screen
- Created PUT HTTP request method for Day entity
- Created GET by ID HTTP request method for Day entity

## [0.52.1] 2024-03-26

- Added heightmaps

## [0.52.0] 2024-03-26

- Added modal for creating and editing Day entity

## [0.51.0] 2024-03-25

- Added Swagger dependency

## [0.50.1] 2024-03-25

- Removed redundant modal

## [0.50.0] 2024-03-25

- Updated Spring Boot backend to 3.2.4
- Added Bootstrap library and modal

## [0.49.0] 2024-03-25

- Added angular to project
- Created frontend portion again

## [0.48.0] 2024-03-24

- Removed frontend portion to resolve strange errors

## [0.47.0] 2024-03-24

- Created modal for editing days
- Created PUT request method for Day entity
- Created observable to edit days on frontend

## [0.46.0] 2023-12-19

- Created modal for adding days
- Created save, delete and get methods for HTTP requests regarding the Day entity
- Added Bootstrap library for modal and styling
- Created a new Angular project to avoid incompatibilities between Bootstrap and Angular Material

## [0.45.1] 2023-12-19

- Added code for generic modal template

## [0.45.0] 2023-12-19

- Adjusted mapping for data between frontend and backend

## [0.44.0] 2023-12-19

- Edited entities in backend
- Added post method in frontend

## [0.43.1] 2023-12-19

- Added HTTP request methods for `TitleController.java`

## [0.43.0] 2023-12-19

- Enabling Cross Origin Requests for RESTful Web Services

## [0.42.0] 2023-12-19

- Created observables for Day entity on frontend
- Adjusted custom response messages on frontend and backend

## [0.41.0] 2023-12-19

- Created sidenav
- Created routes
- Created home page

## [0.40.1] 2023-12-19

- Upgraded Angular to version 17
- Added dependency for text editor Ngx Editor
- Added dependency for text editor Quill Editor

## [0.40.0] 2023-12-19

- Added Angular frontend project
- Added dependency for text editor

## [0.39.0] 2023-12-19

- Added Spring Data JPA queries for politics package
- Corrected GET request for all images

## [0.38.0] 2023-12-19

- Added image folders for different packages
- Added GET request for all images

## [0.37.0] 2023-12-19

- Added validation for entities in places packages
- Updated PATCH method for controllers in places packages

## [0.36.5] 2023-12-19

- Added validation for `Location` entity

## [0.36.4] 2023-12-19

- Refactored code for society package

## [0.36.3] 2023-12-19

- Refactored code for `Constellation` and `Tarot` entity

## [0.36.2] 2023-12-19

- Refactored code for `Year` and `Epoch` entity

## [0.36.1] 2023-12-19

- Refactored code for `Month` entity

## [0.36.0] 2023-12-19

- Corrected controllers for `Day.java`
- Refactored packages for project
- Corrected file path for images related to `Angel.java`

## [0.35.0] 2023-12-19

- Finalized controllers in politics package

## [0.34.0] 2023-12-19

- Finalized services in politics package

## [0.33.0] 2023-12-19

- Added abstract methods for services in politics package

## [0.32.0] 2023-12-19

- Added `@Repository` annotation to politics package

## [0.31.0] 2023-12-19

- Added RESTful web services for classes society package

## [0.30.0] 2023-12-19

- Added RESTful web services for classes except politics and society packages

## [0.29.0] 2023-12-19

- Added RESTful web services for classes in history package

## [0.28.0] 2023-12-19

- Added RESTful web services for classes in religion package

## [0.27.0] 2023-12-19

- Added RESTful web services for `Prophet.java` and `Religion.java`

## [0.26.2] 2023-12-19

- Corrected missing cascades for certain relationships

## [0.26.1] 2023-12-19

- Added fetch types and cascades for all entities

## [0.26.0] 2023-12-19

- Resolved issues related to cardinality and cascades
- Added CRUD operations for `Letter.java`, `Language.java`, `Day.java`
- Proper CRUD operations and cascades for other classes will be added later

## [0.25.0] 2023-12-19

- Added API endpoints for `Day.java`
- Added log files
- Added convenient methods for `DayServiceImpl.java`

## [0.24.0] 2023-12-19

- Created service class for spring boot data JPA

## [0.23.0] 2023-12-19

- Created custom repositories for practicing named jdbc template

## [0.22.0] 2023-12-19

- Finalized Spring Data JPA repositories for all entity classes

## [0.21.0] 2023-12-19

- Added some repositories for certain entity classes

## [0.20.1] 2023-12-19

- Moved classes to proper packages

## [0.20.0] 2023-12-19

- Added controllers for REST API endpoints

## [0.19.0] 2023-12-19

- Finalized service classes for all DAO classes

## [0.18.0] 2023-12-19

- Added service classes for certain DAO classes

## [0.17.0] 2023-12-19

- Added service class for certain DAO classes

## [0.16.0] 2023-12-19

- Added CRUD functionalities for DAO classes

## [0.15.0] 2023-12-19

- Added `Prophet.java` entity class

## [0.14.0] 2023-12-19

- Added `@Repository` annotation for DAO classes

## [0.13.0] 2023-12-19

- Added Data Access Objects for all entities

## [0.12.1] 2023-12-19

- Resolved JPA mapping with database

## [0.12.0] 2023-12-19

- Added `House.java` and `Lineage.java`

## [0.11.0] 2023-12-19

- Updated world maps

## [0.10.0] 2023-12-19

- Added `Gender.java`

## [0.9.1] 2023-12-19

- Corrected update date

## [0.9.0] 2023-12-04

- Added additional entities and edited existing entities

## [0.8.0] 2023-12-04

- Added additional entities and edited existing entities

## [0.7.0] 2023-12-04

- Added additional entities and edited existing entities

## [0.6.0] 2023-12-04

- Added entities and instance variables for those entities

## [0.3.0] 2023-12-04

- Added frontend portion

## [0.2.0] 2023-12-04

- Added more entity classes

## [0.1.0] 2023-12-03

- Added entity classes and configuration in properties file

## [0.0.0] 2023-12-03

- Added `version.yaml` and `CHANGELOG.md` files
