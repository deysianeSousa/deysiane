Sequencia
Cadastrar User
POST -> http://localhost:8080/user
{ "name" : "Deysiane", "email" : "deysiane.sousa06@hotmail.com", "password" : "teste1234", "phones" : [ { "number" : "98181-3949", "ddd" : "13" } ] }

GET -> http://localhost:8080/user/1
Ira retornar usuario criar

Login
POST -> http://localhost:8080/login
{"email" : "admin", "password" : "password"}
ou
{"email" : "deysiane.sousa06@hotmail.com", "password" : "teste1234"}

