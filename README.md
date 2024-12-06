

Green Shadow Farm Management

Green Shadow is a mid-scale farm.This project manages the fields,crops,staff,equipments,vehicles and monitor the logs.


View API documentation :- https://documenter.getpostman.com/view/36189390/2sAYBbdU1k

    

Features 

 1) Field management - Add , update , delete and retrieve field data 

 2) Crop management -  Add , update , delete crop and retrieve crop related data.

 3) Staff management - Manage the staff by adding updating and deleting.

 4) Vehicle management - Manage the vehicles adding , updating and deleting records.

 5) Equipment management - Manage the equipments assigned for agricultural purposes.
 
 6) Logs management - Manage the logs regarding to crops,field and staff.

 7) Role based access - Acess permissions for some features is limited based on roles.

 In addition this project is secured using JWT authentication and authorization.

Tech stack

    (1) Frontend :-
            HTML5 , CSS , JS 
            AJAX , Bootstrap

    (2) Backend :-
            Springboot
            JWT
            Java
            MYSQL

    (3) Postman is used for API testing purposes.

 
More about API 

    Base URL :- http://localhost:8080/greenshadow/api/v1

    Some endpoints :- 
            Signup :- POST - api/v1/auth
            Add field :- POST - api/v1/field
            Get crop :- PUT - api/v1/crop
            Delete staff :- DELETE - api/v1/staff/{staffId}


Special point 

    Role based access limitation :- 
        There are three roles.MANAGER , ADMINISTRATIVE and SCIENTIST. The MANAGER can access all the crud operations.ADMINISTRATIVE can not edit crops or fields.SCIENTIST can not access vehicles,equipment and staff. 











