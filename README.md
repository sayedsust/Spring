# Spring

# spring-boot-jpa

# spring-shop

# 01 Spring-boot + Spring-data-jpa + MySql / H2 inmemory
CRUD with REST 
(1) Get all tickets with rest GET request ~ http://localhost:8080/ticket/getTickets
(2) Create multiple tickets POST request ~ http://localhost:8080/ticket/
[
    {"name":"shopping","price":45.1254},
    {"name":"concert","price":500.1254}
]
H2 database available: http://localhost:8080/h2-console

# 02 Spring-boot + Hibernate + MySql / H2 inmemory
not exist

# 03 Spring-boot + Spring-data-jpa + MySql + thymeleaf CRUD

create user / update / delete user with Thymeleaf


# 04 Spring-boot + Spring Security + Basic Authetication

http://localhost:8080/rest/adminpath/getMsg ~ accessible for ADMIN only

http://localhost:8080/rest/userpath/getMsg ~ accessible for autheticated user

# 05 Spring-boot + Spring Security + Authetication against JPA + Spring login page
SpringSecurity with Jpa and SpringLoginPage

# 06 Spring-boot + Spring Security + Authetication against JPA + Registration + Custom Login Page + Admin login Area

# 07 Spring boot + Spring Security + JWT ~ https://www.javainuse.com/spring/boot-jwt

GET JWT Token: http://localhost:8080/autheticate with POST request

{
    "username":"javainuse",
    "password":"password"
}

GET request : http://localhost:8080/hello

# 08 Spring boot + Angular CRUD
Empleyee Create / Delete / show
	# 08_01 Spring boot + Angular CRUD with Angular Login

# 09 Spring boot + Spring JWT Authetication ~ https://bezkoder.com/angular-spring-boot-jwt-auth/
Insert role into database:
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

POST request create user: http://localhost:8080/api/auth/signup
{
    "username":"mod",
    "email":"sa@gma.com",
    "password":"password",
    "role" : ["mod","user"]
}

POST signin request: http://localhost:8080/api/auth/signin
{
    "username":"mod",
    "password":"password"
}

GET request with Bearer token: http://localhost:8080/api/test/user

#10 Spring boot + 








