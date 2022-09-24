
Curls:

RestaurantForUserController

GET http://localhost:8080/api/user/restaurants/today
Authorization: Basic user userPassword
###
GET http://localhost:8080/api/user/restaurants/today
Authorization: Basic 2 2

    #####################
    ##AccountController##
    #####################


POST http://localhost:8080/api/user/account/register
Content-Type: application/json

{
"login": "Tes31132",
"name": "Te141423std",
"password": "te4st1111"
}
###
PUT http://localhost:8080/api/user/account/
Content-Type: application/json
Authorization: Basic 1 1

{
"login": "111111111111",
"name": "1111111111",
"password": "111111111111"
}
###
GET http://localhost:8080/api/user/account
Authorization: Basic 1 1
###
###
DELETE http://localhost:8080/api/user/account/delete
Authorization: Basic 1 1


    ##################
    ##VoteController##
    ##################


GET http://localhost:8080/api/user/votes/current
Authorization: Basic user userPassword
###
POST

    ######################
    ##UserMenuController##
    ######################

GET http://localhost:8080/api/user/menu/today
Authorization: Basic 1 1
###
POST http://localhost:8080/api/user/votes/1
Authorization: Basic 2 2
Content-Type: application/json
    
    ##########################
    ##AdminAccountController##
    ##########################
GET http://localhost:8080/api/admin/account
Authorization: Basic PetrLogin userPassword
###
GET http://localhost:8080/api/admin/account/3
Authorization: Basic 2 2
###
DELETE http://localhost:8080/api/admin/account/delete/3
Authorization: Basic VasiaLogin userPassword
###
POST http://localhost:8080/api/admin/account/register
Authorization: Basic 2 2
Content-Type: application/json

{
"login": "Tes3111322",
"name": "Te1414123std",
"password": "t1e4st1111"
}
###
PUT http://localhost:8080/api/admin/account/update/1
Authorization: Basic admin adminPassword
Content-Type: application/json

{
"name": "123",
"login": "34",
"password":"1"
}

    #############################
    ##AdminRestaurantController##
    #############################
GET http://localhost:8080/api/admin/restaurants
Authorization: Basic 2 2
##
DELETE http://localhost:8080/api/admin/restaurants/delete/3
Authorization:Basic 2 2
##
PUT http://localhost:8080/api/admin/restaurants/update/4
Authorization: Basic 2 2
Content-Type: application/json

{
"title" : "testte12st"
}
##
POST http://localhost:8080/api/admin/restaurants/add
Authorization: Basic 2 2
Content-Type: application/json

{
"title" : "teesf23412st"
}
##
