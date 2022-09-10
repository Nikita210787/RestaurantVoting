
Curls:

RestaurantForUserController

GET http://localhost:8080/api/restaurants/
Authorization: Basic 1 1
###
GET http://localhost:8080/api/restaurants/today
Authorization: Basic 1 1


AccountController

DELETE http://localhost:8080/api/account
Authorization: Basic user userPassword
###
GET http://localhost:8080/api/account
Authorization: Basic 1 1
###
POST http://localhost:8080/api/account/register
Content-Type: application/json
{
"LOGIN": "Test11",
"NAME": "Testd",
"PASSWORD": "test1111"
}
###
PUT http://localhost:8080/api/account
Content-Type: application/json
Authorization: Basic 1 1
{
"LOGIN": "Test11",
"NAME": "Testd",
"PASSWORD": "test1111"
}

VoteController

GET http://localhost:8080/api/votes
Authorization: Basic 1 1
###
GET http://localhost:8080/api/votes/current
Authorization: Basic 1 1
###
POST
    